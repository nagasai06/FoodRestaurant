package com.RestaurantFood.FoodRestaurant.Controller;

import com.google.api.core.ApiFuture;
import com.google.cloud.pubsub.v1.Publisher;
import com.google.protobuf.ByteString;
import com.google.pubsub.v1.ProjectTopicName;
import com.google.pubsub.v1.PubsubMessage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DebugController {
    @GetMapping("/debug/publish")
    public String publishTestMessage() {
        String projectId = "positive-water-468219-g2";
        String topicId = "FoodRestaurant-logs-topic";
        ProjectTopicName topicName = ProjectTopicName.of(projectId, topicId);
        Publisher publisher = null;

        try {
            publisher = Publisher.newBuilder(topicName).build();
            String message = "Test message from GKE app";
            ByteString data = ByteString.copyFromUtf8(message);
            PubsubMessage pubsubMessage = PubsubMessage.newBuilder().setData(data).build();

            ApiFuture<String> messageIdFuture = publisher.publish(pubsubMessage);
            String messageId = messageIdFuture.get();

            return "Published message ID: " + messageId;
        } catch (Exception e) {
            return "Error publishing message: " + e.getMessage();
        } finally {
            if (publisher != null) {
                try {
                    publisher.shutdown();
                    publisher.awaitTermination(1, java.util.concurrent.TimeUnit.MINUTES);
                } catch (Exception ignored) {}
            }
        }
    }
}
