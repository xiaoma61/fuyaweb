package com.fuya.fuyadao.dao;
import com.fuya.fuyadao.entity.SKILL;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SKILLRepository extends JpaRepository<SKILL,Integer> {
    @Query("select  s from  SKILL s where s.USERID=?1 and s.TYPE=?2")
    List<SKILL> findByUSERIDAAndTYPE(int ID,int type);
    @Query("select  s from  SKILL s where s.SKILLID=?1")
    SKILL findByID(int id);
}
