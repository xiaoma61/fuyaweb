package com.fuya.fuyaservice.impl;

import com.fuya.fuyadao.dao.PROVEINFORepository;
import com.fuya.fuyadao.entity.PROVEINFO;
import com.fuya.fuyadao.model.PROVEINFOANDBAISINFO;
import com.fuya.fuyaservice.PROVEINFOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PROVEINFOImpl  implements PROVEINFOService {
    @Autowired
    PROVEINFORepository proveinfoRepository;
    @Override
    public void save(PROVEINFO proveinfo) {
        proveinfoRepository.save(proveinfo);

    }

    @Override
    public List<PROVEINFO> findByUSERSID(int userid) {

        return proveinfoRepository.findByUSERSID(userid);
    }

    @Override
    public PROVEINFO findByID(int id) {
        return proveinfoRepository.findByID(id);
    }

    @Override
    public void deleteByUSERSID(int id) {
        proveinfoRepository.deleteByUSERSID(id);
    }

    @Override
    public List<PROVEINFOANDBAISINFO> findPROVEINFOByAndYUESAOBASICINFOByUSERSID(int id) {
        return proveinfoRepository.findPROVEINFOByAndYUESAOBASICINFOByUSERSID(id);
    }

    @Override
    public void update(String YUESAOSYNDROME, String HEALTHCERTIFICATES, String REPORT, String SERVICEPICTURE, int PROVEINFOID) {
        proveinfoRepository.update(YUESAOSYNDROME, HEALTHCERTIFICATES, REPORT, SERVICEPICTURE, PROVEINFOID);
    }


}
