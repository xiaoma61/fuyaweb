package com.fuya.fuyadao.dao;

import com.fuya.fuyadao.entity.ARTICLE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface ARTICLERepository  extends JpaRepository<ARTICLE,Integer> {
    //查找前6条--首页功能
  @Query(nativeQuery = true ,value = "select * from  ARTICLE  where  TYPE=?1 limit 0,6 ")
    List<ARTICLE>findAlllimit(int type);
    @Query("select a from  ARTICLE  a where a.ARTICLEID=?1")
    ARTICLE findByID(int id);
    @Transactional//注解，使用默认配置，抛出异常之后，事务会自动回滚，数据不会插入到数据库
    @Modifying
    @Query("update  ARTICLE a set a.NUMS=?1 where a.ARTICLEID=?2")
    void updatebyid(int nums, int id);
  @Transactional//注解，使用默认配置，抛出异常之后，事务会自动回滚，数据不会插入到数据库
  @Modifying
  @Query("delete from ARTICLE a where a.ARTICLEID=?1")
  void   deleteByid(int id);
  @Transactional//注解，使用默认配置，抛出异常之后，事务会自动回滚，数据不会插入到数据库
  @Modifying
  @Query("update ARTICLE a set a.TYPE=?1, a.TITLE=?2,a.CONTENT=?3 where a.ARTICLEID=?4")
  void   updateARTICLEbyid(int type, String title, String content, int id);

}
