package com.fuya.fuyadao.dao;

import com.fuya.fuyadao.entity.PROBLEM;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PROBLEMRepository extends JpaRepository<PROBLEM,Integer> {
    @Query("select p from PROBLEM  p  where p.TITLE like concat('%',:title,'%')")
    List<PROBLEM>findByTITLELike(@Param("title") String title);

    @Query("select p from PROBLEM  p")
    List<PROBLEM>findALL();
    @Procedure(name = "CHOOSE_PROCEDURE")
    void deleteAllByPROMBLEIDandCHOOSEID(@Param("MCHOOSEID") int CHOOSEID, @Param("PROMBLEID") int PROMBLEID);
    @Transactional
    @Modifying
    @Query("update PROBLEM  p set p.TITLE=?1, p.CHOOSETYPE=?2,p.SUBJECTMATTER=?3 where p.PROBLEMID=?4")
    void update(String title, String choosetype, String subjectmatier, int id);
    @Query(nativeQuery = true ,value = "select * from (select * from PROBLEM  p  order by dbms_random.value) where rownum<=?1 ")
//    List<AdminProblemAnswer>findByNum(int nums);
    List<PROBLEM>findByNum(int nums);


}
