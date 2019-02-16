package com.fuya.fuyaservice;

import com.fuya.fuyadao.entity.RECRUIT;

import java.sql.Date;

public interface RECRUITService {
    RECRUIT findByID(int id);
    void save(RECRUIT recruit);
    void delete(int id);
    void   updateRECRUITbyid(String NUMS, String DESCRIBE, String EDUCATION, Date ENDTIME, String WORKBACKGROUND, String HIGHLIGHT, String LINKMAN,
                             String PHONE, String POSITION, String SALARY, Date STARTTIME, Date TIME, String  WORKAREA, int id);
}
