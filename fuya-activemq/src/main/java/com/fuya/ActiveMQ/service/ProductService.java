package com.fuya.ActiveMQ.service;


import javax.jms.Destination;

public interface ProductService {
    String sendMessage(Destination destination, String Msg);
}
