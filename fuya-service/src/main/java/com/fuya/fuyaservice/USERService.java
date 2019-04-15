package com.fuya.fuyaservice;


import com.fuya.fuyadao.entity.USERS;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface USERService {
    USERS findUSERSByNAME(String NAME);
    void save(USERS users);
    USERS findByID(int ID);
    void delete(int ID);
    void updatebyuserid(int type, int userid);
    void deleteAllByUSERSID(@Param("ID") int id);
    List<USERS> findall();
}
