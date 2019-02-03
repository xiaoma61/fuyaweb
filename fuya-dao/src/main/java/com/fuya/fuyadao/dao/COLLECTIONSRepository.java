package com.fuya.fuyadao.dao;

import com.fuya.fuyadao.entity.COLLECTIONS;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface COLLECTIONSRepository extends JpaRepository<COLLECTIONS,Integer> {
    @Query("select  c from  COLLECTIONS c where c.ID=?1")
    COLLECTIONS findByID(int id);
    //增加
    @Query("delete from COLLECTIONS c where c.FROMID=?1 and c.TOID=?2")
    void deleteByFROMIDAndTOID(int fromid,int toid);
//    @Query("select  c from  COLLECTIONS  c where c.FROMID=?1 and  c.ID=?2")
//    COLLECTIONS findByFROMIDAndTOID(int fromid,int toid);
}
