package com.fuya.fuyadao.dao;

import com.fuya.fuyadao.entity.COMPANYYUESAO;
import com.fuya.fuyadao.entity.YUESOBASICINFO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface COMPANYYUESAORepository extends JpaRepository<COMPANYYUESAO,Integer> {
    @Query("select c from COMPANYYUESAO c where c.COMPANYYUESAOID=?1")
    COMPANYYUESAO findByCOMPANYID(int id);
    @Transactional//注解，使用默认配置，抛出异常之后，事务会自动回滚，数据不会插入到数据库
    @Modifying
    @Query("delete from COMPANYYUESAO c where c.YUESAOID=?1")
    void deleteByCOMPANYYUESAOID(int id);
    @Query(nativeQuery = true ,value = "select y.AGE ,y.TYPE ,y.NATIVEPLACE,y.WAGES,y.PHOTO,y.NAME,y.LEVELS,y.USERSID from COMPANYYUESAO  c ,yuesobasicinfo y where c.COMPANYID=?1 and y.USERSID=c.YUESAOID"
        ,countQuery = "select count(*) from COMPANYYUESAO  c ,yuesobasicinfo y where c.COMPANYID=?1 and y.USERSID=c.YUESAOID"
    )
   /* Page<Object[]> findByYUESAOCOMPANYID(int id, Pageable pageable);*/
    Page<String> findByYUESAOCOMPANYID(int id, Pageable pageable);
    @Query("select c from COMPANYYUESAO  c where c.COMPANYID=?1")
    List<COMPANYYUESAO> findByRealCOMPANYID(int id);
    //得到名字
    @Query("select y from COMPANYYUESAO  c, YUESOBASICINFO y where c.COMPANYID=?1 and y.USERSID=c.YUESAOID and y.NAME like concat('%',?2,'%') ")
    YUESOBASICINFO findYUESOBASICINFObynameandcompanyid(int id, String name);



}
