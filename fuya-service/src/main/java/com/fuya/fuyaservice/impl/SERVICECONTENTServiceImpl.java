package com.fuya.fuyaservice.impl;

import com.fuya.fuyadao.dao.SERVICECONTENTRepository;
import com.fuya.fuyadao.entity.SERVICECONTENT;
import com.fuya.fuyaservice.SERVICECONTENTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SERVICECONTENTServiceImpl implements SERVICECONTENTService {
    @Autowired
    SERVICECONTENTRepository servicecontentRepository;

    @Override
    public void save(SERVICECONTENT servicecontent) {
        servicecontentRepository.save(servicecontent);
    }

    @Override
    public void updateHANDSELSTATUSbySERVICECONTENTID(int handselstatus, int id) {
        servicecontentRepository.updateHANDSELSTATUSbySERVICECONTENTID(handselstatus,id);
    }

    @Override
    public void updatebySERVICECONTENTID(int sumstatus, int id) {

        servicecontentRepository.updatebySERVICECONTENTID(sumstatus,id);
    }
}
