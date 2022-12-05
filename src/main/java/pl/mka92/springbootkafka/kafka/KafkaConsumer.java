package pl.mka92.springbootkafka.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import pl.mka92.springbootkafka.config.KafkaTopic;

@Slf4j
@Service
public class KafkaConsumer {

    private static final String CONSUMER_GROUP_ID = "myGroup";

    @KafkaListener(topics = KafkaTopic.KAFKA_TOPIC,
            groupId = CONSUMER_GROUP_ID)
    public void consume(String message) {
        log.info("Message: \"{}\" from topic: {} for groupId: {}", message, KafkaTopic.KAFKA_TOPIC, CONSUMER_GROUP_ID);
    }

}
