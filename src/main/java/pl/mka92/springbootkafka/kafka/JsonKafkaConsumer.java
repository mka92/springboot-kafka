package pl.mka92.springbootkafka.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import pl.mka92.springbootkafka.config.KafkaTopic;
import pl.mka92.springbootkafka.kafka.model.User;

@Slf4j
@Service
public class JsonKafkaConsumer {

    private static final String CONSUMER_GROUP_ID = "myGroup";

    @KafkaListener(topics = KafkaTopic.KAFKA_TOPIC_JSON, groupId = CONSUMER_GROUP_ID)
    public void consume(User user) {
        log.info("Message: \"{}\" from topic: {} for groupId: {}. Thread id: {}",
                user, KafkaTopic.KAFKA_TOPIC_JSON, CONSUMER_GROUP_ID,
                Thread.currentThread().getId());
    }

}
