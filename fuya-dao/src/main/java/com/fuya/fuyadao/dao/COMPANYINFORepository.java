package com.fuya.fuyadao.dao;

import com.fuya.fuyadao.entity.COMPANYINFO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface COMPANYINFORepository extends JpaRepository<COMPANYINFO,Integer> {

    @Query("select c from COMPANYINFO c where c.COMPANYINFOID=?1")
    COMPANYINFO findByID(int id);
}
