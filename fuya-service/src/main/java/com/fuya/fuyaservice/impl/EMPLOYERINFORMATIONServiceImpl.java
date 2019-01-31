package com.fuya.fuyaservice.impl;

import com.fuya.fuyadao.dao.EMPLOYERINFORMATIONRepository;
import com.fuya.fuyadao.entity.EMPLOYERINFORMATION;
import com.fuya.fuyaservice.EMPLOYERINFORMATIONService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EMPLOYERINFORMATIONServiceImpl implements EMPLOYERINFORMATIONService {
    @Autowired
    EMPLOYERINFORMATIONRepository employerinformationRepository;
    @Override
    public EMPLOYERINFORMATION findByODERID(int oderid) {
        EMPLOYERINFORMATION employerinformation=employerinformationRepository.findByODERID(oderid);
        return employerinformation;
    }

    @Override
    public EMPLOYERINFORMATION findByID(int id) {
        EMPLOYERINFORMATION employerinformation=employerinformationRepository.findByID(id);
        return employerinformation;
    }
}
