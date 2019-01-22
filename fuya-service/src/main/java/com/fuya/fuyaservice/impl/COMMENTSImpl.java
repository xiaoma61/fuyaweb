package com.fuya.fuyaservice.impl;

import com.fuya.fuyadao.dao.COMMENTSRepository;
import com.fuya.fuyadao.entity.COMMENTS;
import com.fuya.fuyaservice.COMMENTSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class COMMENTSImpl implements COMMENTSService {
    @Autowired
    COMMENTSRepository commentsRepository;

    @Override
    public COMMENTS findByUSERID(int ID) {
        return null;
    }
}
