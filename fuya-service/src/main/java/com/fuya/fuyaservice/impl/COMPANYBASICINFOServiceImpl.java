package com.fuya.fuyaservice.impl;

import com.fuya.fuyadao.dao.COMPANYBASICINFORepository;
import com.fuya.fuyadao.entity.COMPANYBASICINFO;
import com.fuya.fuyadao.model.CompanysInfosModle;
import com.fuya.fuyaservice.COMPANYBASICINFOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    @Override
    public void deleteByUSERID(int id) {
        companybasicinfoRepository.deleteByUSERID(id);
    }

    @Override
    public List<String> findByCORPORATENAMELike(String name) {
        return companybasicinfoRepository.findByCORPORATENAMELike(name);
    }

    @Override
    public List<CompanysInfosModle> findCOMPANYBASICINFOByCORPORATENAMELike(String name) {
        return companybasicinfoRepository.findCOMPANYBASICINFOByCORPORATENAMELike(name);
    }

    @Override
    public Page<COMPANYBASICINFO> findALL(int start, int rows) {
        Pageable pageable =new PageRequest(start,rows);
        return companybasicinfoRepository.findAll(pageable);
    }

    @Override
    public List<Object> find() {
        return companybasicinfoRepository.find();
    }

    @Override
    public Object findidMsg(int id) {
        return companybasicinfoRepository.findidMsg(id);
    }

    @Override
    public CompanysInfosModle findCompanysInfosModleByUSERID(int id) {
        return companybasicinfoRepository.findCompanysInfosModleByUSERID(id);
    }


}
