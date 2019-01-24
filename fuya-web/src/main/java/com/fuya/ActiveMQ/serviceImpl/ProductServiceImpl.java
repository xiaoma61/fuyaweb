package com.fuya.ActiveMQ.serviceImpl;

import com.fuya.ActiveMQ.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.jms.Destination;


@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;
    @Override
    public void sendMessage(Destination destination, String Msg) {
        jmsMessagingTemplate.convertAndSend(destination,Msg);

    }
    @JmsListener(destination = "return-queue")
    public void Message(String Msg){
        System.out.println("Product收到:"+Msg);
    }
}
