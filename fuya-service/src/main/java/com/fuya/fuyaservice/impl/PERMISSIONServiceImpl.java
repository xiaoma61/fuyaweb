package com.fuya.fuyaservice.impl;

import com.fuya.fuyadao.dao.PERMISSIONRepository;
import com.fuya.fuyadao.entity.PERMISSION;
import com.fuya.fuyaservice.PERMISSIONService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PERMISSIONServiceImpl implements PERMISSIONService {
    @Autowired
    PERMISSIONRepository permissionRepository;
    @Override
    public List<PERMISSION> findall() {

        return   permissionRepository.findAll();
    }
}
