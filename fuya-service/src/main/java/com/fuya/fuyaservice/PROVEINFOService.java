package com.fuya.fuyaservice;

import com.fuya.fuyadao.entity.PROVEINFO;

import java.util.List;

public interface PROVEINFOService {
    public void save(PROVEINFO proveinfo);
    List<PROVEINFO> findByUSERSID(int userid);
    PROVEINFO findByID(int id);

}
