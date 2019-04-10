package com.fuya.fuyaservice.impl;

import com.fuya.fuyadao.dao.COLLECTIONSRepository;
import com.fuya.fuyadao.entity.COLLECTIONS;
import com.fuya.fuyadao.entity.YUESOBASICINFO;
import com.fuya.fuyaservice.COLLECTIONSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class COLLECTIONSServiceImpl implements COLLECTIONSService {
    @Autowired
    COLLECTIONSRepository collectionsRepository;
    @Override
    public COLLECTIONS findByID(int id) {
        return collectionsRepository.findByID(id);
    }

    @Override
    public void deleteByFROMIDAndTOID(int fromid, int toid) {
        collectionsRepository.deleteByFROMIDAndTOID(fromid,toid);
    }

    @Override
    public void save(COLLECTIONS collections) {
        collectionsRepository.save(collections);

    }

    @Override
    public List<YUESOBASICINFO> findByFROMID(int id) {
        return collectionsRepository.findByFROMID(id);
    }


}
