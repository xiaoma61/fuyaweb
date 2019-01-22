package com.fuya.fuyadao.dao;

import com.fuya.fuyadao.entity.COMPANYBASICINFO;
import com.fuya.fuyadao.entity.YUESOBASICINFO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface YUESOBASICINFORepository extends JpaRepository<COMPANYBASICINFO,Integer> {

    //SELECT new com.johnfnash.learn.domain.ViewInfo(u, a) FROM UserInfo u, Address a WHERE u.addressId = a.addressId
    // @Query("select new com.fuya.fuyadao.entity.ProveAndYuesaobasic(y.c) from  YUESOBASICINFO y , COMMENTS c where y.USERSID=c.USERID")
    // List<ProveAndYuesaobasic> findAlllimit();

    //首页
    @Query(nativeQuery = true,value = "select * from (select * from  YUESOBASICINFO order by LEVELS desc ) where rownum<=6")
    List<YUESOBASICINFO> findAlllimit();


}