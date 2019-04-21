package com.fuya.fuyaservice;

import com.fuya.fuyadao.entity.ORDERS;
import com.fuya.fuyadao.model.ODERSEMPCommentMSG;
import com.fuya.fuyadao.model.ODERSEMPMSG;
import com.fuya.fuyadao.model.OrderYuesaoCommentServiceEmployee;

import java.util.List;

public interface ORDERSService {
    List<ORDERS> findByTOID(int toid);
    ORDERS findByID(int id);
    List<ODERSEMPMSG>findODERSEMPMSGByTOID(int toid, int SUMSTATUS);
    List<ODERSEMPCommentMSG>findODERSEMPCommentMSGByTOID(int toid, int SUMSTATUS);
    void save(ORDERS orders);
    List<OrderYuesaoCommentServiceEmployee>findOrderYuesaoCommentServiceEmployeeByFROMID(int fromid, int status);
   OrderYuesaoCommentServiceEmployee findOrderYuesaoCommentServiceEmployeeByOderID(int id);
    int findcountByTOID(int toid);
    List<Object> findCommentsByTOID(int toid);

}
