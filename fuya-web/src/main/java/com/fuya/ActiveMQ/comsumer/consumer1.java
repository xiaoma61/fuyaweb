package com.fuya.ActiveMQ.comsumer;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Service
public class consumer1 {
    @JmsListener(destination = "zh-topic")
    public void receiveQueue(String id) {
        //实现redis插入

        System.out.println("con:"+id);
    }
}
