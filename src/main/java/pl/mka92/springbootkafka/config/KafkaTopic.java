package pl.mka92.springbootkafka.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopic {

    public static final String KAFKA_TOPIC = "java-guides";

    @Bean
    public NewTopic javaGuidesTopic() {
        return TopicBuilder.name(KAFKA_TOPIC).build();
    }
}
