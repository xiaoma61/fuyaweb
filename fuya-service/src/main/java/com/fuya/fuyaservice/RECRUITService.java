package com.fuya.fuyaservice;

import com.fuya.fuyadao.entity.RECRUIT;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.sql.Date;
import java.util.List;

public interface RECRUITService {
    RECRUIT findByID(int id);
    void save(RECRUIT recruit);
    void delete(int id);
    void   updateRECRUITbyid(String NUMS, String DESCRIBE, String EDUCATION, Date ENDTIME, String WORKBACKGROUND, String HIGHLIGHT, String LINKMAN,
                             String PHONE, String POSITION, String SALARY, Date STARTTIME, Date TIME, String WORKAREA, int id);
    List<Object> find();
    Page<RECRUIT>findAll(Pageable pageable);
}
