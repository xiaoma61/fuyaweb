package com.fuya.fuyaservice;

import com.fuya.fuyadao.entity.COLLECTIONS;
import com.fuya.fuyadao.entity.YUESOBASICINFO;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface COLLECTIONSService {
    COLLECTIONS findByID(int id);
    void deleteByFROMIDAndTOID(int fromid, int toid);
    void save(COLLECTIONS collections);
    List<YUESOBASICINFO> findByFROMID(int id);
}
