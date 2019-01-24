package com.fuya.fuyaservice.impl;

import com.fuya.fuyadao.dao.PROVEINFORepository;
import com.fuya.fuyadao.entity.PROVEINFO;
import com.fuya.fuyaservice.PROVEINFOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PROVEINFOImpl  implements PROVEINFOService {
    @Autowired
    PROVEINFORepository proveinfoRepository;
    @Override
    public void save(PROVEINFO proveinfo) {
        proveinfoRepository.save(proveinfo);

    }
}