package com.fuya.fuyaservice.impl;

import com.fuya.fuyadao.dao.COMMENTSRepository;
import com.fuya.fuyadao.entity.COMMENTS;
import com.fuya.fuyaservice.COMMENTSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class COMMENTSImpl implements COMMENTSService {
    @Autowired
    COMMENTSRepository commentsRepository;

    @Override
    public List<COMMENTS> findByUSERID(int ID) {
        List<COMMENTS>commentsList=commentsRepository.findByUSERID(ID);
        return commentsList;
    }

    @Override
    public COMMENTS findbyid(int id) {
        COMMENTS comments=commentsRepository.findByID(id);
        return comments;
    }

    @Override
    public void save(COMMENTS comments) {
        commentsRepository.save(comments);
    }
}
