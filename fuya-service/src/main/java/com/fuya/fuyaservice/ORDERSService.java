package com.fuya.fuyaservice;

import com.fuya.fuyadao.entity.ORDERS;
import com.fuya.fuyadao.model.ODERSEMPCommentMSG;
import com.fuya.fuyadao.model.ODERSEMPMSG;

import java.util.List;

public interface ORDERSService {
    List<ORDERS> findByTOID(int toid);
    ORDERS findByID(int id);
    List<ODERSEMPMSG>findODERSEMPMSGByTOID(int toid,int SUMSTATUS);
    List<ODERSEMPCommentMSG>findODERSEMPCommentMSGByTOID(int toid, int SUMSTATUS);
}
