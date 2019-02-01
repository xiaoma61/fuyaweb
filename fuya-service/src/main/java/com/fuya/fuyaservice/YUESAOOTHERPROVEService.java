package com.fuya.fuyaservice;

import com.fuya.fuyadao.entity.YUESAOOTHERPROVE;


import java.util.List;

public interface YUESAOOTHERPROVEService {
    YUESAOOTHERPROVE findByID(int id);
    List<YUESAOOTHERPROVE> findByUSERID(int userid);
}
