package com.fuya.fuyaservice.impl;

import com.fuya.fuyadao.dao.MSGRepository;
import com.fuya.fuyadao.entity.MSG;
import com.fuya.fuyaservice.MSGService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MSGServiceImpl implements MSGService {
    @Autowired
    MSGRepository msgRepository;

    @Override
    public void save(MSG msg) {
        msgRepository.save(msg);
    }

    @Override
    public MSG findByID(int id) {
        return msgRepository.findByID(id);
    }
}
