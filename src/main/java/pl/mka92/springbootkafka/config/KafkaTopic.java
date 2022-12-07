package pl.mka92.springbootkafka.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopic {

    public static final String KAFKA_TOPIC_STRING = "java-guides";
    public static final String KAFKA_TOPIC_JSON = "java-guides-json";

    @Bean
    public NewTopic javaGuidesTopic() {
        return TopicBuilder.name(KAFKA_TOPIC_STRING).build();
    }

    @Bean
    public NewTopic javaGuidesTopicJson() {
        return
                TopicBuilder.name(KAFKA_TOPIC_JSON)
                        .partitions(3)
                        .build();
    }
}
