package pl.mka92.springbootkafka.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import pl.mka92.springbootkafka.config.KafkaTopic;
import pl.mka92.springbootkafka.kafka.model.User;

@Slf4j
@RequiredArgsConstructor
@Service
public class JsonKafkaProducer {

    private final KafkaTemplate<String, User> kafkaTemplate;

    public void sendMessage(User user) {
        log.info("Sent message \"{}\"", user);
        Message<User> message = MessageBuilder
                .withPayload(user)
                .setHeader(KafkaHeaders.TOPIC, KafkaTopic.KAFKA_TOPIC_JSON)
                .build();
        kafkaTemplate.send(message);
    }
}
