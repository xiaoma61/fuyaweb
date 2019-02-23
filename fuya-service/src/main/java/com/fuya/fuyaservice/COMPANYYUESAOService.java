package com.fuya.fuyaservice;

import com.fuya.fuyadao.entity.COMPANYYUESAO;

import java.util.List;

public interface COMPANYYUESAOService {
    void save(COMPANYYUESAO companyyuesao);
    COMPANYYUESAO findByCOMPANYID(int id);
    void deleteByCOMPANYYUESAOID(int id);
    List<COMPANYYUESAO> findByRealCOMPANYID(int id);
}
