package com.fuya.fuyadao.dao;

import com.fuya.fuyadao.entity.MSG;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MSGRepository extends JpaRepository<MSG,Integer> {
    @Query("select  m from MSG m where m.MSGID=?1")
    MSG findByID(int id);

}
