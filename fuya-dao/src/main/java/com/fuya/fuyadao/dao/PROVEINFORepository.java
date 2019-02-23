package com.fuya.fuyadao.dao;

import com.fuya.fuyadao.entity.PROVEINFO;
import com.fuya.fuyadao.entity.YUESOBASICINFO;
import com.fuya.fuyadao.model.PROVEINFOANDBAISINFO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface PROVEINFORepository extends JpaRepository<PROVEINFO,Long> {
    @Query("select p from PROVEINFO  p where p.USERSID=?1")
    List<PROVEINFO>findByUSERSID(int userid);
    @Query("select p from  PROVEINFO  p where p.PROVEINFOID=?1")
    PROVEINFO findByID(int id);
    @Transactional
    @Modifying
    @Query("delete from PROVEINFO  p where p.USERSID=?1")
    void deleteByUSERSID(int id);
    @Query(value = "select new com.fuya.fuyadao.model.PROVEINFOANDBAISINFO(y,p) from PROVEINFO  p ,YUESOBASICINFO y  where p.USERSID=y.USERSID and p.USERSID=?1 ")
    List<PROVEINFOANDBAISINFO> findPROVEINFOByAndYUESAOBASICINFOByUSERSID(int id);
    @Transactional
    @Modifying
    @Query("update  PROVEINFO p set p.YUESAOSYNDROME=?1 ,p.HEALTHCERTIFICATES=?2,p.REPORT=?3,p.SERVICEPICTURE=?4 where p.USERSID=?5")
    void update(String YUESAOSYNDROME,String HEALTHCERTIFICATES,String  REPORT,String SERVICEPICTURE,int PROVEINFOID );

}
