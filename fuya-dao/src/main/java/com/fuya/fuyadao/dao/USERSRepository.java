package com.fuya.fuyadao.dao;

import com.fuya.fuyadao.entity.USERS;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;



public interface USERSRepository extends JpaRepository<USERS,Integer> {
    //登录
    @Query("select u from USERS u where u.NAME=?1 ")
    USERS findUSERSByNAME(String NAME);
    @Query("select u from USERS u where u.ID=?1 ")
    USERS findByID(int ID);


}
