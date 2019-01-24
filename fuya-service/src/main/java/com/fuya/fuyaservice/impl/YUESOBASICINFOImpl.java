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

}
