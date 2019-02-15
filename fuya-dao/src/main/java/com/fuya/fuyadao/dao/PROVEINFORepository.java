package com.fuya.fuyadao.dao;

import com.fuya.fuyadao.entity.PROVEINFO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PROVEINFORepository extends JpaRepository<PROVEINFO,Integer> {
    @Query("select p from PROVEINFO  p where p.USERSID=?1")
    List<PROVEINFO>findByUSERSID(int userid);
    @Query("select p from  PROVEINFO  p where p.PROVEINFOID=?2")
    PROVEINFO findByID(int id);

}
