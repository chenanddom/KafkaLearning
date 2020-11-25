package com.itdom.component;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class KafkaProducer {
    @Resource
   public KafkaTemplate<String, String> kafkaTemplate;
}
