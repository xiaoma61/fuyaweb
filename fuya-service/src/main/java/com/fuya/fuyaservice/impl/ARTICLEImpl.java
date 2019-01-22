package com.fuya.fuyaservice.impl;

import com.fuya.fuyadao.dao.ARTICLERepository;
import com.fuya.fuyadao.entity.ARTICLE;
import com.fuya.fuyadao.entity.YUESOBASICINFO;
import com.fuya.fuyaservice.ARTICLEService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ARTICLEImpl implements ARTICLEService {

    @Autowired
    ARTICLERepository articleRepository;
    @Override
    public List<ARTICLE> findAlllimit(int nums ,int type) {
     List<ARTICLE> articleList=articleRepository.findAlllimit( nums ,type);

        return articleList;
    }
}
