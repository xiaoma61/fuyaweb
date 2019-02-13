package com.fuya.fuyaservice;

import com.fuya.fuyadao.entity.ARTICLE;


import java.util.List;

public interface ARTICLEService {
    List<ARTICLE>findAlllimit(int nums ,int type);
    ARTICLE findByID(int id);
    void updatebyid(int nums,int id);
}
