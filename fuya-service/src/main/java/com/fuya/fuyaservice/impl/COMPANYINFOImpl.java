package com.fuya.fuyaservice.impl;

import com.fuya.fuyadao.dao.COMMENTSRepository;
import com.fuya.fuyadao.dao.COMPANYINFORepository;
import com.fuya.fuyadao.entity.COMPANYINFO;
import com.fuya.fuyaservice.COMPANYINFOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class COMPANYINFOImpl implements COMPANYINFOService {
    @Autowired
    COMPANYINFORepository companyinfoRepository;
    @Override
    public void save(COMPANYINFO companyinfo) {
       companyinfoRepository.save(companyinfo);

    }

    @Override
    public COMPANYINFO findbyid(int id) {
        COMPANYINFO companyinfo=companyinfoRepository.findByID(id);
        return companyinfo;
    }

    @Override
    public void updateCOMPANYINFObyid(String ADDRESS, String CONTACTNAME, String CONTACTPHONE, String EMAIL, String IDCARD, String IDCARDFILE, String LICENCE, String LICENCENO, int USERSID) {
        companyinfoRepository.updateCOMPANYINFObyid(ADDRESS, CONTACTNAME, CONTACTPHONE, EMAIL, IDCARD, IDCARDFILE, LICENCE, LICENCENO, USERSID);
    }


}
