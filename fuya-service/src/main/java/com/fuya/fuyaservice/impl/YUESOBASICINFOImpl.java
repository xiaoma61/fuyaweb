package com.fuya.fuyaservice.impl;

import com.fuya.fuyadao.dao.YUESOBASICINFORepository;
import com.fuya.fuyadao.entity.YUESOBASICINFO;
import com.fuya.fuyaservice.YUESOBASICINFOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class YUESOBASICINFOImpl implements YUESOBASICINFOService {
    @Autowired
    YUESOBASICINFORepository yuesobasicinfoRepository;


    @Override
    public List<YUESOBASICINFO> findAlllimit() {
        List<YUESOBASICINFO>yuesobasicinfoList=yuesobasicinfoRepository.findAlllimit();

        return yuesobasicinfoList;
    }

    @Override
    public List<YUESOBASICINFO> findAll() {
        List<YUESOBASICINFO>yuesobasicinfoList=yuesobasicinfoRepository.findAll();
        return yuesobasicinfoList;
    }

    @Override
    public void save(YUESOBASICINFO yuesobasicinfo) {
        yuesobasicinfoRepository.save(yuesobasicinfo);
    }

    @Override
    public YUESOBASICINFO findbyid(int id) {
        YUESOBASICINFO yuesobasicinfo=yuesobasicinfoRepository.findByID(id);
        return yuesobasicinfo;

    }

    @Override
    public YUESOBASICINFO findByUSERSID(int userid) {
        YUESOBASICINFO yuesobasicinfo=yuesobasicinfoRepository.findByUSERSID(userid);
        return yuesobasicinfo;
    }

    @Override
    public void deleteByYUESOBASICINFOID(int id) {
        yuesobasicinfoRepository.deleteByYUESOBASICINFOID(id);
    }

    @Override
    public void update(String IDCARD, String EMAIL, String WORKAREA, String PHONE, String EDUCATION, String NAME, String WEIGHT, String AGE, String HEIGHT, String NATIVEPLACE, String PHOTO, String SENIORITY, String WAGES, int YUESOBASICINFOID) {
        yuesobasicinfoRepository.update(IDCARD, EMAIL, WORKAREA, PHONE, EDUCATION, NAME, WEIGHT, AGE, HEIGHT, NATIVEPLACE, PHOTO, SENIORITY, WAGES, YUESOBASICINFOID);
    }


}
