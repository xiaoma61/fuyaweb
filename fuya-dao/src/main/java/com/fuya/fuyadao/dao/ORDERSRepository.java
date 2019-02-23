package com.fuya.fuyadao.dao;

import com.fuya.fuyadao.entity.ORDERS;
import com.fuya.fuyadao.model.ODERSEMPCommentMSG;
import com.fuya.fuyadao.model.ODERSEMPMSG;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ORDERSRepository extends JpaRepository<ORDERS,Integer> {
    //添加
    @Query("select o from  ORDERS o where  o.ORDERSID=?1")
    ORDERS findByID(int id);
    //根据月嫂id查找信息
    @Query("select o from  ORDERS  o  where  o.TOID=?1")
    List<ORDERS>findByTOID(int toid);
    @Query("select new com.fuya.fuyadao.model.ODERSEMPMSG(e,o,s) from EMPLOYERINFORMATION e, ORDERS o,SERVICECONTENT s where e.ODERID=o.ORDERSID and o.ORDERSID=s.ORDERID and o.TOID=?1 and s.SUMSTATUS=?2")
    List<ODERSEMPMSG>findODERSEMPMSGByTOID(int toid,int SUMSTATUS);
    @Query("select new com.fuya.fuyadao.model.ODERSEMPCommentMSG(e,o,s,c) from EMPLOYERINFORMATION e, ORDERS o,SERVICECONTENT s ,COMMENTS c where e.ODERID=o.ORDERSID and o.ORDERSID=s.ORDERID and c.ORDERID=o.ORDERSID and o.TOID=?1 and s.SUMSTATUS=?2")
    List<ODERSEMPCommentMSG>findODERSEMPCommentMSGByTOID(int toid, int SUMSTATUS);



}
