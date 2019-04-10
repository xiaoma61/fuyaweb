package com.fuya.fuyadao.dao;

import com.fuya.fuyadao.entity.YUESOBASICINFO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface JpaSpecificationExecutorRepository extends JpaSpecificationExecutor<YUESOBASICINFO> {

    List<YUESOBASICINFO> findAll(Specification<YUESOBASICINFO> spec);
    Page<YUESOBASICINFO> findAll(Specification<YUESOBASICINFO> spec, Pageable pageable);
    List<YUESOBASICINFO> findAll(Specification<YUESOBASICINFO> spec, Sort sort);
    long count(Specification<YUESOBASICINFO> spec);


}
