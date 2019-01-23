package com.fuya.ActiveMQ.comsumer;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;

@Service
public class comsumer {
    @JmsListener(destination = "zh-topic")
    @SendTo("return-queue")
    public String receiveQueue(String text) {
        System.out.println("Consumer2收到:"+text);
        return "Consumer2收到!";
    }

}
