package com.itdom.controller;

import com.itdom.component.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/demo")
public class DemoController {

@Autowired
private KafkaProducer kafkaProducer;
@PostMapping("/send/{message}")
public void sendMessage(@PathVariable("message")String message){
if (StringUtils.isEmpty(message)){
    return;
}
kafkaProducer.kafkaTemplate.send("topic-test",message);
}
}
