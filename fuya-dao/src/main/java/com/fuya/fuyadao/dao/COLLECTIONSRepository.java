package com.fuya.fuyadao.dao;

import com.fuya.fuyadao.entity.COLLECTIONS;
import com.fuya.fuyadao.entity.YUESOBASICINFO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface COLLECTIONSRepository extends JpaRepository<COLLECTIONS,Integer> {
    @Query("select  c from  COLLECTIONS c where c.COLLECTIONSID=?1")
    COLLECTIONS findByID(int id);
    //增加
    @Transactional//注解，使用默认配置，抛出异常之后，事务会自动回滚，数据不会插入到数据库
    @Modifying
    @Query("delete  from COLLECTIONS c where c.FROMID=?1 and c.TOID=?2")
    void deleteByFROMIDAndTOID(int fromid, int toid);
    @Query("select y from COLLECTIONS  c,YUESOBASICINFO y where c.FROMID=?1 and y.USERSID=c.TOID ")
    List<YUESOBASICINFO> findByFROMID(int id);

}
