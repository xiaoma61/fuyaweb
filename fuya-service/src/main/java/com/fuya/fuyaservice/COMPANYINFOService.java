package com.fuya.fuyaservice;

import com.fuya.fuyadao.entity.COMPANYINFO;
import com.fuya.fuyadao.model.CompanysInfosModle;

import java.util.List;

public interface COMPANYINFOService {
    public  void  save(COMPANYINFO companyinfo);
    public COMPANYINFO findbyid(int id);
    void   updateCOMPANYINFObyid(String ADDRESS, String CONTACTNAME, String CONTACTPHONE, String EMAIL, String IDCARD, String IDCARDFILE, String LICENCE, String LICENCENO, int USERSID);
    List<Object> find();
    void deleteByUSERSID(int id);
    CompanysInfosModle findByUSERSID(int id);
}
