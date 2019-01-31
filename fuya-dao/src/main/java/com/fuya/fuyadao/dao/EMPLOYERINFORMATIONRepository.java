package com.fuya.fuyadao.dao;

import com.fuya.fuyadao.entity.EMPLOYERINFORMATION;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EMPLOYERINFORMATIONRepository extends JpaRepository<EMPLOYERINFORMATION,Integer> {
    @Query("select e from  EMPLOYERINFORMATION e where e.ODERID=?1")
    EMPLOYERINFORMATION findByODERID(int oderid);
    @Query("select e from  EMPLOYERINFORMATION  e  where  e.ID=?1")
    EMPLOYERINFORMATION findByID(int id);
}
