package com.fuya.fuyaservice;


import com.fuya.fuyadao.entity.USERS;



public interface USERService {
    USERS findUSERSByNAME(String NAME);
}
