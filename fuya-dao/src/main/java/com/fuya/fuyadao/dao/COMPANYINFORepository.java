package com.fuya.fuyadao.dao;

import com.fuya.fuyadao.entity.COMPANYINFO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface COMPANYINFORepository extends JpaRepository<COMPANYINFO,Integer> {

    @Query("select c from COMPANYINFO c where c.COMPANYINFOID=?1")
    COMPANYINFO findByID(int id);
    @Transactional//注解，使用默认配置，抛出异常之后，事务会自动回滚，数据不会插入到数据库
    @Modifying
    @Query("update COMPANYINFO c set c.ADDRESS=?1,c.CONTACTNAME=?2,c.CONTACTPHONE=?3,c.EMAIL=?4,c.IDCARD=?5,c.IDCARDFILE=?6,c.LICENCE=?7,c.LICENCENO=?8,c.USERSID=?9")
    void   updateCOMPANYINFObyid(String ADDRESS,String CONTACTNAME,String CONTACTPHONE, String EMAIL,String IDCARD,String IDCARDFILE,String LICENCE,String LICENCENO,int USERSID);
}
