package com.fuya.fuyadao.dao;

import com.fuya.fuyadao.entity.COMPANYINFO;
import com.fuya.fuyadao.model.CompanysInfosModle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface COMPANYINFORepository extends JpaRepository<COMPANYINFO,Integer> {

    @Query("select c from COMPANYINFO c where c.COMPANYINFOID=?1")
    COMPANYINFO findByID(int id);
    @Transactional//注解，使用默认配置，抛出异常之后，事务会自动回滚，数据不会插入到数据库
    @Modifying
    @Query("update COMPANYINFO c set c.ADDRESS=?1,c.CONTACTNAME=?2,c.CONTACTPHONE=?3,c.EMAIL=?4,c.IDCARD=?5,c.IDCARDFILE=?6,c.LICENCE=?7,c.LICENCENO=?8,c.USERSID=?9")
    void   updateCOMPANYINFObyid(String ADDRESS, String CONTACTNAME, String CONTACTPHONE, String EMAIL, String IDCARD, String IDCARDFILE, String LICENCE, String LICENCENO, int USERSID);
    @Query("select c.IDCARD, c.LICENCENO,c.CONTACTNAME,c.USERSID ,p.CORPORATENAME from COMPANYINFO c,COMPANYBASICINFO p where p.USERID=c.USERSID")
    List<Object> find();
    @Query("delete from  COMPANYINFO c where c.USERSID=?1")
    void deleteByUSERSID(int id);
    @Query("select new com.fuya.fuyadao.model.CompanysInfosModle(p,c)  from COMPANYINFO c,COMPANYBASICINFO p where p.USERID=c.USERSID and c.USERSID=?1")
    CompanysInfosModle findByUSERSID(int id);


}
