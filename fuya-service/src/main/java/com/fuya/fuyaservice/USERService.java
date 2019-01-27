package com.fuya.fuyaservice;


import com.fuya.fuyadao.entity.USERS;
import org.springframework.stereotype.Service;



public interface USERService {
    USERS findUSERSByNAME(String NAME);
    void save(USERS users);
    USERS findByID(int ID);
}
