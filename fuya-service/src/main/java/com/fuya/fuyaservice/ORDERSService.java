package com.fuya.fuyaservice;

import com.fuya.fuyadao.entity.ORDERS;

import java.util.List;

public interface ORDERSService {
    List<ORDERS> findByTOID(int toid);
    ORDERS findByID(int id);
}
