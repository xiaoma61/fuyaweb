package com.fuya.fuyaservice;

import com.fuya.fuyadao.entity.PROBLEM;
import com.fuya.fuyadao.model.PROBLEMmodel;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;

import java.util.List;

//import com.github.pagehelper.PageInfo;

public interface PROBLEMService {
    void save(PROBLEM problem);
    List<PROBLEM> findByTITLELike(@Param("title") String title);
    Page<PROBLEM> findALL(int start, int rows);
    void deleteAllByPROMBLEIDandCHOOSEID(@Param("MCHOOSEID") int MCHOOSEID, @Param("PROMBLEID") int PROMBLEID);
    void update(String title, String choosetype, String subjectmatier, int id);
    List<Object[]>findByNum(int nums);
//    List<AdminProblemAnswer>findByNum(int nums);
}
