package pl.mka92.springbootkafka.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import pl.mka92.springbootkafka.config.KafkaTopic;
import pl.mka92.springbootkafka.kafka.model.User;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Service
public class JsonKafkaConsumer {

    private static final String CONSUMER_GROUP_ID = "myGroup";

    private static final Map<Integer, Set<Long>> THREAD_IDS_BY_PARTITION_NUMBER = new ConcurrentHashMap<>();


    @KafkaListener(topics = KafkaTopic.KAFKA_TOPIC_JSON, groupId = CONSUMER_GROUP_ID, concurrency = "3")
    public void consume(User user, ConsumerRecord<?, ?> consumerRecord) {
        determineThreadNumbersToPartitionNumber(consumerRecord);
        log.info("Message: \"{}\" from topic: {}. Thread id: {}", user, consumerRecord.topic(), Thread.currentThread().getId());
    }

    private void determineThreadNumbersToPartitionNumber(ConsumerRecord<?, ?> consumerRecord) {
        Set<Long> threadIdsByPartitionNumber = THREAD_IDS_BY_PARTITION_NUMBER.getOrDefault(consumerRecord.partition(), ConcurrentHashMap.newKeySet());
        threadIdsByPartitionNumber.add(Thread.currentThread().getId());
        THREAD_IDS_BY_PARTITION_NUMBER.put(consumerRecord.partition(), threadIdsByPartitionNumber);
    }

    public Map<Integer, Set<Long>> getThreadIdsByPartitionNumber() {
        return THREAD_IDS_BY_PARTITION_NUMBER;
    }

}
