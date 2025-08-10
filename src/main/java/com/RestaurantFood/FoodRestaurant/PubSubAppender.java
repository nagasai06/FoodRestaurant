package com.RestaurantFood.FoodRestaurant;

import ch.qos.logback.core.AppenderBase;
import ch.qos.logback.classic.spi.ILoggingEvent;
import com.google.cloud.pubsub.v1.Publisher;
import com.google.protobuf.ByteString;
import com.google.pubsub.v1.ProjectTopicName;
import com.google.pubsub.v1.PubsubMessage;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class PubSubAppender extends AppenderBase<ILoggingEvent> {

    private static volatile Publisher publisher; // one shared instance
    private String projectId;
    private String topicId;

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public void setTopicId(String topicId) {
        this.topicId = topicId;
    }

    @Override
    public void start() {
        if (this.projectId == null || this.topicId == null) {
            addError("Pub/Sub projectId or topicId is not set.");
            return;
        }

        try {
            if (publisher == null) {
                synchronized (PubSubAppender.class) {
                    if (publisher == null) {
                        ProjectTopicName topicName = ProjectTopicName.of(projectId, topicId);
                        publisher = Publisher.newBuilder(topicName).build();
                        addInfo("Pub/Sub publisher initialized for topic: " + topicId);
                    }
                }
            }
            super.start();
        } catch (IOException e) {
            addError("Failed to initialize Pub/Sub publisher", e);
        }
    }

    @Override
    protected void append(ILoggingEvent eventObject) {
        if (publisher == null) {
            return;
        }
        try {
            String logMsg = eventObject.getFormattedMessage();
            ByteString data = ByteString.copyFromUtf8(logMsg);

            PubsubMessage message = PubsubMessage.newBuilder()
                    .setData(data)
                    .putAttributes("level", eventObject.getLevel().toString())
                    .putAttributes("logger", eventObject.getLoggerName())
                    .putAttributes("timestamp", String.valueOf(eventObject.getTimeStamp()))
                    .build();

            publisher.publish(message); // async publish

        } catch (Exception e) {
            addError("Failed to publish log message to Pub/Sub", e);
        }
    }

    @Override
    public void stop() {
        try {
            if (publisher != null) {
                publisher.shutdown();
                publisher.awaitTermination(1, TimeUnit.MINUTES);
                publisher = null;
                addInfo("Pub/Sub publisher shut down.");
            }
        } catch (Exception e) {
            addError("Error shutting down Pub/Sub publisher", e);
        }
        super.stop();
    }
}
