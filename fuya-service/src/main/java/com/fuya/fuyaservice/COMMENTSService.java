package com.fuya.fuyaservice;

import com.fuya.fuyadao.entity.COMMENTS;

import java.util.List;

public interface COMMENTSService {
    List<COMMENTS> findByUSERID(int ID);
    COMMENTS findbyid(int id);
    void save(COMMENTS comments);

}
