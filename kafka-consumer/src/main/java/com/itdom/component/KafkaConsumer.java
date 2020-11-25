package com.itdom.component;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {
    @KafkaListener(topics = {"topic-test"})
    public void receiveMessage(String message) {
        System.out.println("consumer="+message);
    }
}
