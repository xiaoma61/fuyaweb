package com.fuya.fuyaservice.impl;

import com.fuya.fuyadao.dao.YUESAOOTHERPROVERepository;
import com.fuya.fuyadao.entity.YUESAOOTHERPROVE;
import com.fuya.fuyaservice.YUESAOOTHERPROVEService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class YUESAOOTHERPROVEServiceImpl implements YUESAOOTHERPROVEService {
    @Autowired
    YUESAOOTHERPROVERepository yuesaootherproveRepository;
    @Override
    public YUESAOOTHERPROVE findByID(int id) {
        YUESAOOTHERPROVE yuesobasicinfo=yuesaootherproveRepository.findByID(id);
        return yuesobasicinfo;
    }

    @Override
    public List<YUESAOOTHERPROVE> findByUSERID(int userid) {
        List<YUESAOOTHERPROVE>yuesaootherproves=yuesaootherproveRepository.findByUSERID(userid);

        return yuesaootherproves;
    }

    @Override
    public void save(YUESAOOTHERPROVE yuesaootherprove) {
        yuesaootherproveRepository.save(yuesaootherprove);
    }

    @Override
    public void deleteByUSERID(int id) {
        yuesaootherproveRepository.deleteByUSERID(id);
    }

    @Override
    public Page<YUESAOOTHERPROVE> findAll(Pageable pageable) {
        return yuesaootherproveRepository.findAll(pageable);
    }

    @Override
    public void deleteByYUESAOOTHERPROVEID(int id) {
        yuesaootherproveRepository.deleteByYUESAOOTHERPROVEID(id);
    }

    @Override
    public Page<YUESAOOTHERPROVE> findByUSERID(int USERID, Pageable pageable) {
        return yuesaootherproveRepository.findByUSERID(USERID, pageable);
    }


}
