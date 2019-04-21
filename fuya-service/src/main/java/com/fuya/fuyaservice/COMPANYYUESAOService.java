package com.fuya.fuyaservice;

import com.fuya.fuyadao.entity.COMPANYYUESAO;
import com.fuya.fuyadao.model.Yuesaolistyuesaomodel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface COMPANYYUESAOService {
    void save(COMPANYYUESAO companyyuesao);
    COMPANYYUESAO findByCOMPANYID(int id);
    void deleteByCOMPANYYUESAOID(int id);
    List<COMPANYYUESAO>findByRealCOMPANYID(int id);
 /*   Page<Object[]> findByYUESAOCOMPANYID(int id, Pageable pageable);*/
 Page<String> findByYUESAOCOMPANYID(int id, Pageable pageable);
}
