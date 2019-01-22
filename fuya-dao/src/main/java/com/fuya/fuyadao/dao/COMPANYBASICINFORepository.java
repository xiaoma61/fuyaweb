package com.fuya.fuyadao.dao;

import com.fuya.fuyadao.entity.COMPANYBASICINFO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface COMPANYBASICINFORepository extends JpaRepository<COMPANYBASICINFO,Integer> {
//原生的sql
    @Query(nativeQuery = true,value = "select * from (select * from  COMPANYBASICINFO order by LEVELS desc ) where rownum<=6")
    List<COMPANYBASICINFO>findAlllimit();
}
