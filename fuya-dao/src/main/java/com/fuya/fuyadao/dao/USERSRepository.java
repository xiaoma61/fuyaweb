package com.fuya.fuyadao.dao;

import com.fuya.fuyadao.entity.USERS;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;


public interface USERSRepository extends JpaRepository<USERS,Integer> {
    //登录
    @Query("select u from USERS u where u.NAME=?1 ")
    USERS findUSERSByNAME(String NAME);
    @Query("select u from USERS u where u.USERSID=?1 ")
    USERS findByID(int ID);
    @Transactional//注解，使用默认配置，抛出异常之后，事务会自动回滚，数据不会插入到数据库
    @Modifying
    @Query("delete from  USERS u where u.USERSID=?1")
    void deleteByUSERSID(int id);
    //更新
    @Transactional//注解，使用默认配置，抛出异常之后，事务会自动回滚，数据不会插入到数据库
    @Modifying
    @Query("update USERS u set u.TYPE=?1 where u.USERSID=?2 ")
    void updatebyuserid(int type, int userid);
    @Procedure(name = "USER_PROCEDURE")
    void deleteAllByUSERSID(@Param("ID") int id);




}
