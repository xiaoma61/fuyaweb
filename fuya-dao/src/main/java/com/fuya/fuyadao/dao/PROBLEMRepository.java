package com.fuya.fuyadao.dao;

import com.fuya.fuyadao.entity.PROBLEM;
import com.fuya.fuyadao.model.PROBLEMmodel;
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
    @Query(nativeQuery = true ,value = "SELECT  c.CHOOSEID, c.ACHOOSE,  c.BCHOOSE, c.CCHOOSE,c.DCHOOSE,p.PROBLEMID,p.TITLE FROM PROBLEM  p, choose c where p.CHOOSEID=c.CHOOSEID  ORDER BY RAND() LIMIT  ?1")
//    List<AdminProblemAnswer>findByNum(int nums);
    List<Object[]>findByNum(int nums);


}
