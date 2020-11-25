package com.itdom.client;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Arrays;
import java.util.Properties;

public class KafkaConsumerClient implements Runnable {
    private KafkaConsumer kafkaConsumer;

    public KafkaConsumerClient() {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "192.168.253.3:9092");
        properties.put("group.id", "test");
        properties.put("enable.auto.commit", "true");
        properties.put("auto.commit.interval.ms", "1000");
        // key的反序列化类型
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        // 设置value的反序列化类型
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        this.kafkaConsumer = new KafkaConsumer(properties);
    }

    @Override
    public void run() {
        //这个message代表了topics
        kafkaConsumer.subscribe(Arrays.asList("message"));
        while (true) {
            ConsumerRecords<String, String> records = kafkaConsumer.poll(100);
            for (ConsumerRecord<String, String> record : records) {
                System.out.printf("topic = %s,offset = %d, key = %s, value = %s%n", record.topic(), record.offset(), record.key(), record.value());

            }
        }
    }

 public static void main(String[] args) {
  new Thread(new KafkaConsumerClient()).start();
 }
}
