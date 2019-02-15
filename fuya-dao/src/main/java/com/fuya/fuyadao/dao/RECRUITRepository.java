package com.fuya.fuyadao.dao;

import com.fuya.fuyadao.entity.RECRUIT;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RECRUITRepository extends JpaRepository<RECRUIT,Integer> {
    @Query("select r from RECRUIT r where r.RECRUITID=?1")
    RECRUIT findByID(int id);


}
