package com.fuya.fuyaservice;

import com.fuya.fuyadao.entity.ARTICLE;


import java.util.List;

public interface ARTICLEService {
    List<ARTICLE>findAlllimit(int nums ,int type);
    ARTICLE findByID(int id);
    void updatebyid(int nums,int id);
    void save(ARTICLE article);
    void delete(int id);
    void   updateARTICLEbyid(int type,String title,String content,int id);
}
