package com.fuya.fuyadao.dao;

import com.fuya.fuyadao.entity.COMPANYBASICINFO;
import com.fuya.fuyadao.entity.YUESOBASICINFO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface YUESOBASICINFORepository extends JpaRepository<YUESOBASICINFO,Integer> {

    //SELECT new com.johnfnash.learn.domain.ViewInfo(u, a) FROM UserInfo u, Address a WHERE u.addressId = a.addressId
    // @Query("select new com.fuya.fuyadao.model.ProveAndYuesaobasic(y.c) from  YUESOBASICINFO y , COMMENTS c where y.USERSID=c.USERID")
    // List<ProveAndYuesaobasic> findAlllimit();

    //首页
    @Query(nativeQuery = true,value = "select * from (select * from  YUESOBASICINFO order by LEVELS desc ) where rownum<=6")
    List<YUESOBASICINFO> findAlllimit();
    @Override
    List<YUESOBASICINFO> findAll();
    @Query("select  y from  YUESOBASICINFO y where  y.YUESOBASICINFOID=?1")
    YUESOBASICINFO findByID(int id);
    @Query("select y from YUESOBASICINFO  y where  y.USERSID=?1")
    YUESOBASICINFO findByUSERSID(int userid);


    @Transactional//注解，使用默认配置，抛出异常之后，事务会自动回滚，数据不会插入到数据库
    @Modifying
    @Query("delete from YUESOBASICINFO  y where y.USERSID=?1")
    void deleteByYUESOBASICINFOID(int id);
    @Transactional//注解，使用默认配置，抛出异常之后，事务会自动回滚，数据不会插入到数据库
    @Modifying
    @Query("update YUESOBASICINFO y set y.IDCARD=?1, y.EMAIL=?2,y.WORKAREA=?3,y.PHONE=?4,y.EDUCATION=?5,y.WEIGHT=?6,y.NAME=?7,y.AGE=?8,y.HEIGHT=?9,y.NATIVEPLACE=?10,y.PHOTO=?11,y.SENIORITY=?12,y.WAGES=?13 where y.USERSID=?14")
    void update(String IDCARD,  String EMAIL ,String WORKAREA ,String PHONE ,String EDUCATION ,String NAME ,String WEIGHT,String AGE ,String HEIGHT ,String NATIVEPLACE ,String PHOTO ,String SENIORITY ,String WAGES  , int YUESOBASICINFOID );



}
