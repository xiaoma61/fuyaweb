package com.fuya.fuyadao.dao;

import com.fuya.fuyadao.entity.ORDERS;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ORDERSRepository extends JpaRepository<ORDERS,Integer> {
    //添加
    @Query("select o from  ORDERS o where  o.ID=?1")
    ORDERS findByID(int id);
    //根据月嫂id查找信息
    @Query("select o from  ORDERS  o  where  o.TOID=?1")
    List<ORDERS>findByTOID(int toid);



}
