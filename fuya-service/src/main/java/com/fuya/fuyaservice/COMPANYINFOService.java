package com.fuya.fuyaservice;

import com.fuya.fuyadao.entity.COMPANYINFO;

public interface COMPANYINFOService {
    public  void  save(COMPANYINFO companyinfo);
    public COMPANYINFO findbyid(int id);
    void   updateCOMPANYINFObyid(String ADDRESS,String CONTACTNAME,String CONTACTPHONE, String EMAIL,String IDCARD,String IDCARDFILE,String LICENCE,String LICENCENO,int USERSID);
}
