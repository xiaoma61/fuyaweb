package com.fuya.fuyadao.dao;

import com.fuya.fuyadao.entity.RECRUIT;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.List;

public interface RECRUITRepository extends JpaRepository<RECRUIT,Integer> {
    @Query("select r from RECRUIT r where r.RECRUITID=?1")
    RECRUIT findByID(int id);
    @Query("delete from RECRUIT r where r.RECRUITID=?1")
    void deleteByRECRUITID(int id);
    @Transactional//注解，使用默认配置，抛出异常之后，事务会自动回滚，数据不会插入到数据库
    @Modifying
    @Query("update RECRUIT r set r.NUMS=?1 ,r.DESCRIBE=?2,r.EDUCATION=?3,r.ENDTIME=?4,r.WORKBACKGROUND=?5,r.HIGHLIGHT=?6,r.LINKMAN=?7,r.PHONE=?8,r.POSITION=?9,r.SALARY=?10,r.STARTTIME=?11," +
            "r.TIME=?12,r.WORKAREA=?13 where r.RECRUITID=?14")
    void   updateRECRUITbyid(String NUMS, String DESCRIBE, String EDUCATION, Date ENDTIME, String WORKBACKGROUND, String HIGHLIGHT, String LINKMAN,
                             String PHONE, String POSITION, String SALARY, Date STARTTIME, Date TIME, String WORKAREA, int id);


    @Query("select c.CORPORATENAME ,r.POSITION, r.TIME,r.SALARY,r.EDUCATION,r.WORKAREA,r.NUMS ,r.RECRUITID from RECRUIT r,COMPANYBASICINFO c  where c.USERID=r.USERSID   order by r.TIME")
    List<Object> find();



}
