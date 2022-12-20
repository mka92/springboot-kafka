package pl.mka92.springbootkafka.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import pl.mka92.springbootkafka.config.threadpoll.DefaultThreadPool;
import pl.mka92.springbootkafka.kafka.JsonKafkaConsumer;
import pl.mka92.springbootkafka.kafka.JsonKafkaProducer;
import pl.mka92.springbootkafka.kafka.model.User;

import java.util.concurrent.TimeUnit;

@Slf4j
@RequiredArgsConstructor
@Service
public class JsonMessageService {

    private final JsonKafkaProducer jsonKafkaProducer;

    private final JsonKafkaConsumer jsonKafkaConsumer;

    @DefaultThreadPool
    private final ThreadPoolTaskExecutor defaultThreadPoolProperties;

    public void produceNumberOfMessagesAndPutThemToConsume(int numberOfMessages) throws InterruptedException {
        for (int i = 0; i < numberOfMessages; i++) {
            User user = new User(i, "Name" + i, "lastName" + i);
            defaultThreadPoolProperties.submit(() -> jsonKafkaProducer.sendMessage(user));
        }

        TimeUnit.SECONDS.sleep(5); // wait for consume messages
        log.info("Thread ids by partition number: {}", jsonKafkaConsumer.getThreadIdsByPartitionNumber());
    }

}
