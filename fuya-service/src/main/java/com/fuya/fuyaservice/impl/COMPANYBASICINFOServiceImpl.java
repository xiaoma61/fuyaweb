package com.fuya.fuyaservice.impl;

import com.fuya.fuyadao.dao.COMPANYBASICINFORepository;
import com.fuya.fuyadao.entity.COMPANYBASICINFO;
import com.fuya.fuyaservice.COMPANYBASICINFOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class COMPANYBASICINFOServiceImpl implements COMPANYBASICINFOService {

    @Autowired
    COMPANYBASICINFORepository companybasicinfoRepository;
    @Override
    public List<COMPANYBASICINFO> findAlllimit() {
        List<COMPANYBASICINFO> companybasicinfoList=companybasicinfoRepository.findAlllimit();
        return companybasicinfoList;
    }

    @Override
    public COMPANYBASICINFO findByID(int ID) {

        COMPANYBASICINFO companybasicinfo=companybasicinfoRepository.findByID(ID);
        return companybasicinfo;
    }

    @Override
    public void save(COMPANYBASICINFO companybasicinfo) {
        companybasicinfoRepository.save(companybasicinfo);
    }

    @Override
    public void updateCOMPANYBASICINFObyid(String ADDRESS, String CORPORATENAME, int id) {
        companybasicinfoRepository.updateCOMPANYBASICINFObyid(ADDRESS, CORPORATENAME, id);
    }


}
