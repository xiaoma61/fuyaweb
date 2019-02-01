package com.fuya.fuyaservice.impl;

import com.fuya.fuyadao.dao.SKILLRepository;
import com.fuya.fuyadao.entity.SKILL;
import com.fuya.fuyaservice.SKILLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SKILLServiceImpl implements SKILLService {
    @Autowired
    SKILLRepository skillRepository;
    @Override
    public List<SKILL> findByUSERID(int ID, int type) {
        List<SKILL>skillList=skillRepository.findByUSERIDAAndTYPE(ID,type);
        return skillList;
    }

    @Override
    public SKILL findByID(int id) {
        SKILL skill=skillRepository.findByID(id);

        return skill;
    }
}
