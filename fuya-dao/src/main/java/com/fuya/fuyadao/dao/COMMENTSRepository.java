package com.fuya.fuyadao.dao;

import com.fuya.fuyadao.entity.COMMENTS;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface COMMENTSRepository extends JpaRepository<COMMENTS,Integer> {
    @Query("select  c from  COMMENTS  c where c.USERID=?1")
    COMMENTS  findByUSERID(int ID);
}
