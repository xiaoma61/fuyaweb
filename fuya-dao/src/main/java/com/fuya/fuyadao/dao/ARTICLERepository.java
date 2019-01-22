package com.fuya.fuyadao.dao;

import com.fuya.fuyadao.entity.ARTICLE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ARTICLERepository  extends JpaRepository<ARTICLE,Integer> {
    //查找前6条--首页功能
  @Query(nativeQuery = true ,value = "select * from  ARTICLE  where rownum<=?1 and TYPE=?2 ")
    List<ARTICLE>findAlllimit(int nums ,int type);

}
