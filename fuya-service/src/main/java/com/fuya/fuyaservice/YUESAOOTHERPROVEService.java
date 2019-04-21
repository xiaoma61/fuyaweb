package com.fuya.fuyaservice;

import com.fuya.fuyadao.entity.YUESAOOTHERPROVE;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface YUESAOOTHERPROVEService {
    YUESAOOTHERPROVE findByID(int id);
    List<YUESAOOTHERPROVE> findByUSERID(int userid);
    void save(YUESAOOTHERPROVE yuesaootherprove);
    void deleteByUSERID(int id);
    Page<YUESAOOTHERPROVE> findAll(Pageable pageable);
    void deleteByYUESAOOTHERPROVEID(int id);
    Page<YUESAOOTHERPROVE> findByUSERID(int USERID, Pageable pageable);
}
