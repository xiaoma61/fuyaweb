package com.fuya.fuyaservice;

import com.fuya.fuyadao.entity.SERVICECONTENT;

public interface SERVICECONTENTService {
    void save(SERVICECONTENT servicecontent);
    void updateHANDSELSTATUSbySERVICECONTENTID(int handselstatus, int id);
    void updatebySERVICECONTENTID(int sumstatus, int id);
}
