package com.fuya.ActiveMQ.service;

import javax.print.attribute.standard.Destination;

public interface ProductService {
    void sendMessage(Destination destination,String Msg);
}
