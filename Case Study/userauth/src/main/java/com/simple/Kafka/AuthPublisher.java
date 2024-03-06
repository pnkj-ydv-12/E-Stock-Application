//package com.simple.Kafka;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.stereotype.Service;
//
//@Service
//public class AuthPublisher
//{
//    public static final String topic ="kafka12";
//
//    @Autowired
//    private KafkaTemplate<String, String> kObj;
//
//    public KafkaTemplate<String, String> getkObj() {
//        return kObj;
//    }
//
//    public void setTempObj(String message) {
//        this.kObj.send(topic,message);
//    }
//
//
//
//    public static String getTopic() {
//        return topic;
//    }
//
//
//
//}