package com.fuya.fuyaservice.impl;

import com.fuya.fuyadao.dao.YUESAOOTHERPROVERepository;
import com.fuya.fuyadao.dao.YUESOBASICINFORepository;
import com.fuya.fuyadao.entity.YUESAOOTHERPROVE;
import com.fuya.fuyadao.entity.YUESOBASICINFO;
import com.fuya.fuyaservice.YUESAOOTHERPROVEService;
import org.springframework.beans.factory.annotation.Autowired;
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
}
