package pl.mka92.springbootkafka.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.mka92.springbootkafka.kafka.JsonKafkaProducer;
import pl.mka92.springbootkafka.kafka.model.User;
import pl.mka92.springbootkafka.service.JsonMessageService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/kafka")
public class JsonMessageController {

    private final JsonKafkaProducer jsonKafkaProducer;
    private final JsonMessageService jsonMessageService;

    @PostMapping("/publish")
    public ResponseEntity<String> publish(@RequestBody User user) {
        jsonKafkaProducer.sendMessage(user);
        return ResponseEntity.ok("Json message sent to kafaka topic");
    }

    @GetMapping("/publish/executor")
    public ResponseEntity<String> publishExecutor() throws InterruptedException {
        jsonMessageService.produceNumberOfMessagesAndPutThemToConsume(10_000);
        return ResponseEntity.ok("Run executor");
    }
}
