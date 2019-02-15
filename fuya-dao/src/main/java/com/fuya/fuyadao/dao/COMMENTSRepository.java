package com.fuya.fuyadao.dao;

import com.fuya.fuyadao.entity.COMMENTS;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface COMMENTSRepository extends JpaRepository<COMMENTS,Integer> {
    //评论
    @Query("select  c from  COMMENTS  c where c.USERID=?1 order by c.LEVELS desc ")
    List<COMMENTS>  findByUSERID(int ID);
    @Query("select c from  COMMENTS  c where c.COMMENTSID=?1")
    COMMENTS findByID(int id);
}
