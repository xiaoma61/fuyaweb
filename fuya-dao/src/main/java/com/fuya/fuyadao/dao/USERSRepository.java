package com.fuya.fuyadao.dao;

import com.fuya.fuyadao.entity.USERS;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;



public interface USERSRepository extends JpaRepository<USERS,Integer> {
    @Query("select u from USERS u where u.NAME=?1 ")
    USERS findUSERSByNAME(String NAME);


}
