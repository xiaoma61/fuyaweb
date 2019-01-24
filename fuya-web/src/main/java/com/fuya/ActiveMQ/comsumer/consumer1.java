package com.fuya.ActiveMQ.comsumer;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Service
public class consumer1 {
    @JmsListener(destination = "zh-topic")
    public void receiveQueue(String text) {
        System.out.println("Consumer收到:"+text);
    }
}