package com.fuya.fuyaservice;

import com.fuya.fuyadao.entity.PROVEINFO;
import com.fuya.fuyadao.model.PROVEINFOANDBAISINFO;

import java.util.List;

public interface PROVEINFOService {
    public void save(PROVEINFO proveinfo);
    List<PROVEINFO> findByUSERSID(int userid);
    PROVEINFO findByID(int id);
    void deleteByUSERSID(int id);
    List<PROVEINFOANDBAISINFO> findPROVEINFOByAndYUESAOBASICINFOByUSERSID(int id);
    void update(String YUESAOSYNDROME, String HEALTHCERTIFICATES, String REPORT, String SERVICEPICTURE, int PROVEINFOID);




}
