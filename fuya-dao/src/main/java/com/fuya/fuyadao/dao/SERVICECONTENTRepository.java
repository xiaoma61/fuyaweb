package com.fuya.fuyadao.dao;

import com.fuya.fuyadao.entity.SERVICECONTENT;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface SERVICECONTENTRepository extends JpaRepository<SERVICECONTENT,Integer> {
    @Transactional//注解，使用默认配置，抛出异常之后，事务会自动回滚，数据不会插入到数据库
    @Modifying
    @Query("update SERVICECONTENT  s set s.SUMSTATUS=?1 where s.SERVICECONTENTID=?2")
    void updatebySERVICECONTENTID(int sumstatus, int id);
    @Transactional//注解，使用默认配置，抛出异常之后，事务会自动回滚，数据不会插入到数据库
    @Modifying
    @Query("update SERVICECONTENT  s set s.HANDSELSTATUS=?1 where s.SERVICECONTENTID=?2")
    void updateHANDSELSTATUSbySERVICECONTENTID(int handselstatus, int id);
}
