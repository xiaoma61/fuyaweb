package com.fuya.fuyaservice.impl;

import com.fuya.fuyadao.dao.RECRUITRepository;
import com.fuya.fuyadao.entity.RECRUIT;
import com.fuya.fuyaservice.RECRUITService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;

@Service
public class RECRUITServiceImpl implements RECRUITService {
    @Autowired
    RECRUITRepository recruitRepository;
    @Override
    public RECRUIT findByID(int id) {

        return recruitRepository.findByID(id);
    }

    @Override
    public void save(RECRUIT recruit) {
        recruitRepository.save(recruit);
    }

    @Override
    public void delete(int id) {
        recruitRepository.deleteByRECRUITID(id);

    }

    @Override
    public void updateRECRUITbyid(String NUMS, String DESCRIBE, String EDUCATION, Date ENDTIME, String WORKBACKGROUND, String HIGHLIGHT, String LINKMAN, String PHONE, String POSITION, String SALARY, Date STARTTIME, Date TIME, String WORKAREA, int id) {
        recruitRepository.updateRECRUITbyid(NUMS, DESCRIBE, EDUCATION, ENDTIME, WORKBACKGROUND, HIGHLIGHT, LINKMAN, PHONE, POSITION, SALARY, STARTTIME, TIME, WORKAREA, id);
    }
}
