package com.fuya.fuyadao.dao;

import com.fuya.fuyadao.entity.YUESAOOTHERPROVE;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface YUESAOOTHERPROVERepository extends JpaRepository<YUESAOOTHERPROVE,Integer> {
    @Query("select y from  YUESAOOTHERPROVE  y where  y.YUESAOOTHERPROVEID=?1")
    YUESAOOTHERPROVE findByID(int id);
    @Query("select y from  YUESAOOTHERPROVE y where y.USERID=?1")
    List<YUESAOOTHERPROVE>findByUSERID(int userid);
    @Transactional//注解，使用默认配置，抛出异常之后，事务会自动回滚，数据不会插入到数据库
    @Modifying
    @Query("delete from YUESAOOTHERPROVE  y where y.USERID=?1")
    void deleteByUSERID(int id);
    @Transactional//注解，使用默认配置，抛出异常之后，事务会自动回滚，数据不会插入到数据库
    @Modifying
    @Query("delete from YUESAOOTHERPROVE  y where y.YUESAOOTHERPROVEID=?1")
    void deleteByYUESAOOTHERPROVEID(int id);

    @Query(value = "SELECT * FROM YUESAOOTHERPROVE WHERE USERID = ?1",
            countQuery = "SELECT count(*) FROM YUESAOOTHERPROVE WHERE USERID = ?1",
            nativeQuery = true)
    Page<YUESAOOTHERPROVE> findByUSERID(int USERID, Pageable pageable);


}
