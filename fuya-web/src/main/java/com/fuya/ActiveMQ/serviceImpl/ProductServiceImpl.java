package com.fuya.ActiveMQ.serviceImpl;

import com.fuya.ActiveMQ.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.print.attribute.standard.Destination;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;
    @Override
    public void sendMessage(Destination destination, String Msg) {
        jmsMessagingTemplate.convertAndSend(String.valueOf(destination),Msg);

    }
    @JmsListener(destination = "return-queue")
    public void Message(String Msg){

    }
}
