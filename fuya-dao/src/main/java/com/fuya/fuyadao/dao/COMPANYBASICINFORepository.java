package com.fuya.fuyadao.dao;

import com.fuya.fuyadao.entity.COMPANYBASICINFO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

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
    void   updateCOMPANYBASICINFObyid(String ADDRESS,String CORPORATENAME,int id);

}
