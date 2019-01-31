package com.fuya.fuyaservice;

import com.fuya.fuyadao.entity.EMPLOYERINFORMATION;

public interface EMPLOYERINFORMATIONService {
    EMPLOYERINFORMATION findByODERID(int oderid);
    EMPLOYERINFORMATION findByID(int id);
}
