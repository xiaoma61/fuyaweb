package com.fuya.fuyaservice.impl;

import com.fuya.fuyadao.dao.RECRUITRepository;
import com.fuya.fuyadao.entity.RECRUIT;
import com.fuya.fuyaservice.RECRUITService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RECRUITServiceImpl implements RECRUITService {
    @Autowired
    RECRUITRepository recruitRepository;
    @Override
    public RECRUIT findByID(int id) {

        return recruitRepository.findByID(id);
    }

    @Override
    public void save(RECRUIT recruit) {
        recruitRepository.save(recruit);
    }
}
