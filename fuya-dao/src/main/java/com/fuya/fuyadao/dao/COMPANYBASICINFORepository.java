package com.fuya.fuyadao.dao;

import com.fuya.fuyadao.entity.COMPANYBASICINFO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface COMPANYBASICINFORepository extends JpaRepository<COMPANYBASICINFO,Integer> {
//原生的sql，首页
    @Query(nativeQuery = true,value = "select * from (select * from  COMPANYBASICINFO order by LEVELS desc ) where rownum<=6")
    List<COMPANYBASICINFO>findAlllimit();
    @Query("select  c from COMPANYBASICINFO c where  c.ID=?1")
    COMPANYBASICINFO findByID(int ID);

}
