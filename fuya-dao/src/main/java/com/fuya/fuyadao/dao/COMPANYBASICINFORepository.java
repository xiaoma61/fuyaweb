package com.fuya.fuyadao.dao;

import com.fuya.fuyadao.entity.COMPANYBASICINFO;
import com.fuya.fuyadao.model.CompanysInfosModle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface COMPANYBASICINFORepository extends JpaRepository<COMPANYBASICINFO,Integer> {
//原生的sql，首页
    @Query(nativeQuery = true,value = "select * from (select * from  COMPANYBASICINFO order by LEVELS desc ) where rownum<=6")
    List<COMPANYBASICINFO>findAlllimit();
    @Query("select  c from COMPANYBASICINFO c where  c.COMPANYBASICINFOID=?1")
    COMPANYBASICINFO findByID(int ID);
    @Transactional//注解，使用默认配置，抛出异常之后，事务会自动回滚，数据不会插入到数据库
    @Modifying
    @Query("update COMPANYBASICINFO c set c.ADDRESS=?1,c.CORPORATENAME=?2  where c.USERID=?3")
    void   updateCOMPANYBASICINFObyid(String ADDRESS, String CORPORATENAME, int id);
    @Query("delete from  COMPANYBASICINFO  c where c.USERID=?1")
    void  deleteByUSERID(int id);
    @Query("select c.CORPORATENAME from COMPANYBASICINFO c where c.CORPORATENAME like concat('%',:name,'%') ")
    List<String>findByCORPORATENAMELike(@Param("name") String name);
    @Query("select new com.fuya.fuyadao.model.CompanysInfosModle(p,c) from COMPANYBASICINFO p , COMPANYINFO c where p.CORPORATENAME like concat('%',:name,'%') and  p.USERID=c.USERSID ")
    List<CompanysInfosModle>findCOMPANYBASICINFOByCORPORATENAMELike(@Param("name") String name);

    @Query("select c.COMPANYBASICINFOID, c.CORPORATENAME,c.ADDRESS,c.NUMS,c.LEVELS,c.INTRODUCE,c.USERID,p.LICENCE from COMPANYBASICINFO c ,COMPANYINFO p where c.USERID=p.USERSID")
    List<Object>find();
    @Query("select p.CONTACTNAME, p.CONTACTPHONE,p.ADDRESS,c.NUMS,c.CORPORATENAME,c.LEVELS, p.LICENCE,c.COMPANYBASICINFOID,c.USERID from COMPANYBASICINFO c,COMPANYINFO p where c.USERID=p.USERSID and  c.COMPANYBASICINFOID=?1")
    Object findidMsg(int id);
}
