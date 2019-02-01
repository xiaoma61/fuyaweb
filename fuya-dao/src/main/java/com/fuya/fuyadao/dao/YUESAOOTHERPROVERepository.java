package com.fuya.fuyadao.dao;

import com.fuya.fuyadao.entity.YUESAOOTHERPROVE;
import com.fuya.fuyadao.entity.YUESOBASICINFO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface YUESAOOTHERPROVERepository extends JpaRepository<YUESAOOTHERPROVE,Integer> {
    @Query("select y from  YUESAOOTHERPROVE  y where  y.ID?=1")
    YUESAOOTHERPROVE findByID(int id);
    @Query("select y from  YUESAOOTHERPROVE y where y.USERID?=1")
    List<YUESAOOTHERPROVE>findByUSERID(int userid);
}
