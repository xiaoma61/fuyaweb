package com.fuya.fuyaservice.impl;

import com.fuya.fuyadao.dao.USERSRepository;
import com.fuya.fuyadao.entity.USERS;
import com.fuya.fuyaservice.USERService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class USERServiceImpl implements USERService {
    @Autowired
    private USERSRepository usersRepository;



    @Override
    public USERS findUSERSByNAME(String NAME) {
        USERS users=usersRepository.findUSERSByNAME(NAME);
        return users;
    }

    @Override
    public void save(USERS users) {

        USERS user=usersRepository.save(users);
    }

    @Override
    public USERS findByID(int ID) {
        USERS users =usersRepository.findByID(ID);
        return users;
    }

    @Override
    public void delete(int ID) {
        usersRepository.deleteByUSERSID(ID);
    }
}
