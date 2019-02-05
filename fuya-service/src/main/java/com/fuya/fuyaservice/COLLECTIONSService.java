package com.fuya.fuyaservice;

import com.fuya.fuyadao.entity.COLLECTIONS;

import javax.transaction.Transactional;

@Transactional
public interface COLLECTIONSService {
    COLLECTIONS findByID(int id);
    void deleteByFROMIDAndTOID(int fromid,int toid);
    void save(COLLECTIONS collections);
}
