package com.fuya.fuyaservice;

import com.fuya.fuyadao.entity.COMPANYBASICINFO;

import java.util.List;

public interface COMPANYBASICINFOService {
    List<COMPANYBASICINFO> findAlllimit();
    COMPANYBASICINFO findByID(int ID);
    void save(COMPANYBASICINFO companybasicinfo);

}
