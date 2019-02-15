package com.fuya.fuyaservice;

import com.fuya.fuyadao.entity.RECRUIT;

public interface RECRUITService {
    RECRUIT findByID(int id);
    void save(RECRUIT recruit);
}
