package com.fuya.fuyaservice.impl;

import com.fuya.fuyadao.dao.COMPANYYUESAORepository;
import com.fuya.fuyadao.entity.COMPANYYUESAO;
import com.fuya.fuyaservice.COMPANYYUESAOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;

import java.util.List;

@Service
public class COMPANYYUESAOServiceImpl implements COMPANYYUESAOService {
    @Autowired
    COMPANYYUESAORepository companyyuesaoRepository;

    @Override
    public void save(COMPANYYUESAO companyyuesao) {

        companyyuesaoRepository.save(companyyuesao);
    }

    @Override
    public COMPANYYUESAO findByCOMPANYID(int id) {

        return  companyyuesaoRepository.findByCOMPANYID(id);
    }

    @Override
    public void deleteByCOMPANYYUESAOID(int id) {
        companyyuesaoRepository.deleteByCOMPANYYUESAOID(id);
    }

    @Override
    public List<COMPANYYUESAO> findByRealCOMPANYID(int id) {
        return companyyuesaoRepository.findByRealCOMPANYID(id);
    }

   /* @Override
    public Page<Object[]> findByYUESAOCOMPANYID(int id, Pageable pageable) {
        return companyyuesaoRepository.findByYUESAOCOMPANYID(id,pageable);
    }*/

    @Override
    public Page<String> findByYUESAOCOMPANYID(int id, Pageable pageable) {
        return companyyuesaoRepository.findByYUESAOCOMPANYID(id,pageable);
    }


}
