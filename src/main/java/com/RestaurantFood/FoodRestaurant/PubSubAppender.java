package com.RestaurantFood.FoodRestaurant;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.Appender;
import ch.qos.logback.core.AppenderBase;
import com.google.cloud.pubsub.v1.Publisher;
import com.google.protobuf.ByteString;
import com.google.pubsub.v1.ProjectTopicName;
import com.google.pubsub.v1.PubsubMessage;

import java.util.concurrent.TimeUnit;

public class PubSubAppender extends AppenderBase<ILoggingEvent> {

    private Publisher publisher;
    private String projectId;
    private String topicId;

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public void setTopicId(String topicId) {
        this.topicId = topicId;
    }

    @Override
    public void start(){

        try {
            ProjectTopicName topicName = ProjectTopicName.of(projectId,topicId);
            publisher = publisher.newBuilder(topicName).build();
            super.start();
        } catch (Exception e){
            addError("Failed to start Pub/Sub",e);
        }
    }

    @Override
    public void stop(){
        try {
            if(publisher!=null){
                publisher.shutdown();
                publisher.awaitTermination(1, TimeUnit.MINUTES);

            }
        } catch (Exception e){
            addError("Unable to shutdown",e);
        }
        super.stop();
    }

    @Override
    protected void append(ILoggingEvent eventObject) {
        try {
            String logMsg = eventObject.getFormattedMessage();
            ByteString byteString = ByteString.copyFromUtf8(logMsg);
            PubsubMessage message= PubsubMessage.newBuilder().setData(byteString).build();

            publisher.publish(message);
        } catch(Exception e){
            addError("Unable to publish",e);
        }
    }
}
