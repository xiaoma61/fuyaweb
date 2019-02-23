package com.fuya.ActiveMQ.service;


import javax.jms.Destination;

public interface ProductService {
    void sendMessage(Destination destination, String Msg);
}
