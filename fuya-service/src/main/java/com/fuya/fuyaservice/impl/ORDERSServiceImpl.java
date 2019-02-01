package com.fuya.fuyaservice.impl;

import com.fuya.fuyadao.dao.ORDERSRepository;
import com.fuya.fuyadao.entity.ORDERS;
import com.fuya.fuyaservice.ORDERSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ORDERSServiceImpl implements ORDERSService {
    @Autowired
    ORDERSRepository ordersRepository;
    @Override
    public List<ORDERS> findByTOID(int toid) {
        List<ORDERS>ordersList=ordersRepository.findByTOID(toid);
        return ordersList;
    }

    @Override
    public ORDERS findByID(int id) {
        ORDERS orders=ordersRepository.findByID(id);

        return orders;
    }
}
