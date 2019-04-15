package com.fuya.fuyadao.dao;

import com.fuya.fuyadao.entity.CHOOSE;
import com.fuya.fuyadao.model.AdminProblemAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CHOOSERepository extends JpaRepository<CHOOSE,Integer> {

    @Query("select new com.fuya.fuyadao.model.AdminProblemAnswer(p,c) from CHOOSE c, PROBLEM p where c.CHOOSEID=p.CHOOSEID and p.PROBLEMID=?1")
    AdminProblemAnswer findByCHOOSEID(int id);
    @Transactional
    @Modifying
    @Query("update CHOOSE  c set c.ACHOOSE=?1 ,c.BCHOOSE=?2, c.CCHOOSE=?3, c.DCHOOSE=?4, c.ANSWER=?5 where c.CHOOSEID=?6")
    void update(String achoose, String bchoose, String cchoose, String dchoose, String answer, int id);
    @Query("select c.ACHOOSE,c.BCHOOSE,c.BCHOOSE,c.DCHOOSE ,p.TITLE,p.CHOOSETYPE  from CHOOSE c, PROBLEM p where c.CHOOSEID=p.CHOOSEID")
    List<Object[]>find();
    @Query("select c.ANSWER,c.BCHOOSE,c.CCHOOSE,c.DCHOOSE from  CHOOSE  c where c.CHOOSEID=?1")
    List<Object[]>findObjectByCHOOSEID(int id);
    @Query("select c.ANSWER,c.CHOOSEID from  CHOOSE  c ")
    List<Object[]>findAnswer();




}
