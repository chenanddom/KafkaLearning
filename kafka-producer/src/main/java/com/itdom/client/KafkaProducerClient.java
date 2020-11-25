package com.itdom.client;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class KafkaProducerClient implements Runnable {

    private KafkaProducer kafkaProducer;

    public KafkaProducerClient() {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "192.168.253.3:9092");
        properties.put("acks", "all");
        properties.put("retries", 0);
        properties.put("batch.size", 20480);
        properties.put("linger.ms", 1);
        properties.put("buffer.memory", 2000000);
        //设置key的序列化类型
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        //设置value的序列化类型
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        this.kafkaProducer = new KafkaProducer(properties);
    }


    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("message " + " key=" + i + " value=" + i);
            kafkaProducer.send(new ProducerRecord("message", "key=" + i, "value=" + i));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        kafkaProducer.flush();
        kafkaProducer.close();
    }

    public static void main(String[] args) {
        new Thread(new KafkaProducerClient()).start();
    }
}
