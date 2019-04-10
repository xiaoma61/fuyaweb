package com.fuya.fuyadao.dao;

import com.fuya.fuyadao.entity.ORDERS;
import com.fuya.fuyadao.model.ODERSEMPCommentMSG;
import com.fuya.fuyadao.model.ODERSEMPMSG;
import com.fuya.fuyadao.model.OrderYuesaoCommentServiceEmployee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ORDERSRepository extends JpaRepository<ORDERS,Integer> {
    //添加
    @Query("select o from  ORDERS o where  o.ORDERSID=?1")
    ORDERS findByID(int id);
    //根据月嫂id查找信息
    @Query("select o from  ORDERS  o  where  o.TOID=?1")
    List<ORDERS>findByTOID(int toid);
    @Query("select new com.fuya.fuyadao.model.ODERSEMPMSG(e,o,s) from EMPLOYERINFORMATION e, ORDERS o,SERVICECONTENT s where e.ODERID=o.ORDERSID and o.ORDERSID=s.ORDERID and o.TOID=?1 and s.SUMSTATUS=?2")
    List<ODERSEMPMSG>findODERSEMPMSGByTOID(int toid, int SUMSTATUS);
    @Query("select new com.fuya.fuyadao.model.ODERSEMPCommentMSG(e,o,s,c) from EMPLOYERINFORMATION e, ORDERS o,SERVICECONTENT s ,COMMENTS c where e.ODERID=o.ORDERSID and o.ORDERSID=s.ORDERID and c.ORDERID=o.ORDERSID and o.TOID=?1 and s.SUMSTATUS=?2")
    List<ODERSEMPCommentMSG>findODERSEMPCommentMSGByTOID(int toid, int SUMSTATUS);
    //查找
    @Query(" select new com.fuya.fuyadao.model.OrderYuesaoCommentServiceEmployee(o,y,c,s,e) from ORDERS o,SERVICECONTENT s ,COMMENTS c,EMPLOYERINFORMATION e ,YUESOBASICINFO y where o.FROMID=?1 " +
            "and o.ORDERSID=s.ORDERID and o.ORDERSID=c.ORDERID and o.ORDERSID=e.ODERID and  y.USERSID=o.TOID and s.STATUS=?2")
    List<OrderYuesaoCommentServiceEmployee>findOrderYuesaoCommentServiceEmployeeByFROMID(int fromid, int status);

    @Query(" select new com.fuya.fuyadao.model.OrderYuesaoCommentServiceEmployee(o,y,c,s,e) from ORDERS o,SERVICECONTENT s ,COMMENTS c,EMPLOYERINFORMATION e ,YUESOBASICINFO y where o.ORDERSID=?1 " +
            "and o.ORDERSID=s.ORDERID and o.ORDERSID=c.ORDERID and o.ORDERSID=e.ODERID and  y.USERSID=o.TOID ")
    OrderYuesaoCommentServiceEmployee findOrderYuesaoCommentServiceEmployeeByOderID(int id);


    @Query("select count (o) from ORDERS o where o.TOID=?1")
    int findcountByTOID(int toid);
    //更新



}
