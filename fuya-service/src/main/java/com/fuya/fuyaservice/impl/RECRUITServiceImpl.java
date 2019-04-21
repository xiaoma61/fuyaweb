package com.fuya.fuyaservice.impl;

import com.fuya.fuyadao.dao.RECRUITRepository;
import com.fuya.fuyadao.entity.RECRUIT;
import com.fuya.fuyaservice.RECRUITService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class RECRUITServiceImpl implements RECRUITService {
    @Autowired
    RECRUITRepository recruitRepository;
    @Override
    public RECRUIT findByID(int id) {

        return recruitRepository.findByID(id);
    }

    @Override
    public void save(RECRUIT recruit) {
        recruitRepository.save(recruit);
    }

    @Override
    public void delete(int id) {

        recruitRepository.deleteByRECRUITID(id);

    }

    @Override
    public void updateRECRUITbyid(String NUMS, String DESCRIBE, String EDUCATION, Date ENDTIME, String WORKBACKGROUND, String HIGHLIGHT, String LINKMAN, String PHONE, String POSITION, String SALARY, Date STARTTIME, Date TIME, String WORKAREA, int id) {
        recruitRepository.updateRECRUITbyid(NUMS, DESCRIBE, EDUCATION, ENDTIME, WORKBACKGROUND, HIGHLIGHT, LINKMAN, PHONE, POSITION, SALARY, STARTTIME, TIME, WORKAREA, id);
    }

    @Override
    public    List<Object> find() {
        return recruitRepository.find();
    }

    @Override
    public Page<RECRUIT> findAll(Pageable pageable) {
        return recruitRepository.findAll(pageable);
    }

    @Override
    public Page<RECRUIT> findByUSERID(int USERID, Pageable pageable) {

        return recruitRepository.findAll(new Specification<RECRUIT>(){

            @Override
            public Predicate toPredicate(Root<RECRUIT> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<Predicate>();
                Path<String> namepath=root.get("USERSID");
                list.add(criteriaBuilder.equal(namepath,USERID));
                Predicate[] p = new Predicate[list.size()];
                return criteriaBuilder.and(list.toArray(p));
            }
        },pageable);

    }
}
