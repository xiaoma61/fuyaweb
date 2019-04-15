package com.fuya.fuyaservice.impl;

import com.fuya.fuyadao.dao.ARTICLERepository;
import com.fuya.fuyadao.entity.ARTICLE;
import com.fuya.fuyaservice.ARTICLEService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ARTICLEImpl implements ARTICLEService {

    @Autowired
    ARTICLERepository articleRepository;
    @Override
    public List<ARTICLE> findAlllimit( int type) {
     List<ARTICLE> articleList=articleRepository.findAlllimit( type);

        return articleList;
    }

    @Override
    public ARTICLE findByID(int id) {
        return articleRepository.findByID(id);
    }

    @Override
    public void updatebyid(int nums, int id) {
        articleRepository.updatebyid(nums,id);
    }

    @Override
    public void save(ARTICLE article) {
        articleRepository.save(article);
    }

    @Override
    public void delete(int id) {
        articleRepository.deleteByid(id);
    }

    @Override
    public void updateARTICLEbyid(int type, String title, String content, int id) {
        articleRepository.updateARTICLEbyid(type,title,content,id);
    }
}
