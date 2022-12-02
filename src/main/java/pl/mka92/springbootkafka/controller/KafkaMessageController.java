package pl.mka92.springbootkafka.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.mka92.springbootkafka.kafka.KafkaProducer;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/kafka")
public class KafkaMessageController {

    private final KafkaProducer kafkaProducer;

    // docker exec --interactive --tty broker kafka-console-consumer --bootstrap-server broker:9092 --topic java-guides --from-beginning
    // http://localhost:8080/api/v1/kafka/publish?message=Hello world
    @GetMapping("/publish")
    public ResponseEntity<String> publish(@RequestParam("message") String message) {
        kafkaProducer.sendMessage(message);
        return ResponseEntity.ok(String.format("Message: \"%s\" was send to topic.", message));
    }
}
