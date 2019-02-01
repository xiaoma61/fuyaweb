package com.fuya.fuyaservice;

import com.fuya.fuyadao.entity.SKILL;

import java.util.List;

public interface SKILLService {
    List<SKILL> findByUSERID(int ID, int type);
    SKILL findByID(int id);
}
