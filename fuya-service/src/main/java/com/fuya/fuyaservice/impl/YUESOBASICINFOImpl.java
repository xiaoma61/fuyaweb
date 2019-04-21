package com.fuya.fuyaservice.impl;

import com.fuya.fuyadao.dao.YUESOBASICINFORepository;
import com.fuya.fuyadao.entity.*;
import com.fuya.fuyaservice.YUESOBASICINFOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class YUESOBASICINFOImpl implements YUESOBASICINFOService {
    @Autowired
    YUESOBASICINFORepository yuesobasicinfoRepository;
//    @Autowired
//    JpaSpecificationExecutorRepository jpaSpecificationExecutorRepository;


    @Override
    public List<YUESOBASICINFO> findAlllimit() {
        List<YUESOBASICINFO>yuesobasicinfoList=yuesobasicinfoRepository.findAlllimit();

        return yuesobasicinfoList;
    }

    @Override
    public List<YUESOBASICINFO> findAll() {
//        List<YUESOBASICINFO>yuesobasicinfoList=yuesobasicinfoRepository.findAll();
        return yuesobasicinfoRepository.findAll();
    }

    @Override
    public void save(YUESOBASICINFO yuesobasicinfo) {
        yuesobasicinfoRepository.save(yuesobasicinfo);
    }

    @Override
    public YUESOBASICINFO findbyid(int id) {
        YUESOBASICINFO yuesobasicinfo=yuesobasicinfoRepository.findByID(id);
        return yuesobasicinfo;

    }

    @Override
    public YUESOBASICINFO findByUSERSID(int userid) {
        YUESOBASICINFO yuesobasicinfo=yuesobasicinfoRepository.findByUSERSID(userid);
        return yuesobasicinfo;
    }

    @Override
    public void deleteByUSERSID(int id){
        yuesobasicinfoRepository.deleteByUSERSID( id);
    }

    @Override
    public void update(String IDCARD, String EMAIL, String WORKAREA, String PHONE, String EDUCATION, String NAME, String WEIGHT, String AGE, String HEIGHT, String NATIVEPLACE, String PHOTO, String SENIORITY, String WAGES, int YUESOBASICINFOID) {
        yuesobasicinfoRepository.update(IDCARD, EMAIL, WORKAREA, PHONE, EDUCATION, NAME, WEIGHT, AGE, HEIGHT, NATIVEPLACE, PHOTO, SENIORITY, WAGES, YUESOBASICINFOID);
    }

    @Override
    public List<Object> findByTYPE(int type) {
        return yuesobasicinfoRepository.findByTYPE(type);
    }

    @Override
    public List<String> findByNAMELike(int id, String name) {
        return yuesobasicinfoRepository.findByNAMELike(id, name);
    }

    @Override
    public String findEMILbyUERSID(int id) {
        return yuesobasicinfoRepository.findEMILbyUERSID(id);
    }

    @Override
    public Object findObjectByUSERSID(int userid) {
        return yuesobasicinfoRepository.findObjectByUSERSID(userid);
    }



    @Transactional
    @Override
    public Page<YUESOBASICINFO> query(String name, String workarea, String minwages, String maxwages, int type, String nativeplace, String minage, String maxage, int start, int rows,int usertype) {


      return   yuesobasicinfoRepository.findAll(new Specification<YUESOBASICINFO>(){
            @Override
            public Predicate toPredicate(Root<YUESOBASICINFO> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {


                Path<String> namepath=root.get("NAME");
                Path<String> workareapath=root.get("WORKAREA");
                Path<String> typepath=root.get("TYPE");
//                Path<String> agepath=root.get("AGE");
//                Path<String>wagespath=root.get("WAGES");
                Path<String>nativeplacepath=root.get("NATIVEPLACE");
                List<Predicate> list = new ArrayList<Predicate>();
                if (!name.equals("null")){

                    list.add(criteriaBuilder.like(namepath,"%"+name+"%"));
                }


                    Join<YUESOBASICINFO, USERS>map=root.join("yu", JoinType.LEFT);
                    list.add(criteriaBuilder.equal(map.get("TYPE"),usertype));
                /*    list.add(criteriaBuilder.equal(map.get("USERSID"),root.get("USERSID")));*/


                if (!workarea.equals("null")){
                    list.add(criteriaBuilder.equal(workareapath,workarea));

                }
                if (!(minwages.equals("null")||maxwages.equals("null"))){
//                  //在某个区间中
                    int min= Integer.parseInt(minwages);
                    int max= Integer.parseInt(maxwages);
                    list.add(criteriaBuilder.between(root.get("WAGES"),min,max));
                }
                if (maxwages.equals("null")){

                    if (!minwages.equals("null")){
                        //大于最大的
                        int min= Integer.parseInt(minwages);
                        list.add( criteriaBuilder.ge(root.get("WAGES"), min));
                    }

                }
                if (minwages.equals("null")){
                    if (!maxwages.equals("null")){
                        int max= Integer.parseInt(maxwages);
                        list.add( criteriaBuilder.lt(root.get("WAGES"), max));
                    }

                }
                if (!nativeplace.equals("null")){

                    list.add(criteriaBuilder.equal(nativeplacepath,nativeplace));
                }
                if (!(minage.equals("null")||maxage.equals("null"))){
                    //在某个区间中
                    int max= Integer.parseInt(maxage);
                    int min= Integer.parseInt(minage);
                    list.add(criteriaBuilder.between(root.get("AGE"),min,max));
                }
                if (minage.equals("null")){
                    //大于最大的
                    if (!maxage.equals("null")){
                        int max= Integer.parseInt(maxage);
                        list.add( criteriaBuilder.lt(root.get("AGE"), max));
                    }
                }
                if (maxage.equals("null")){
                    //小于最小的
                    if (!minage.equals("null")){
                        int min= Integer.parseInt(minage);
                        list.add( criteriaBuilder.ge(root.get("AGE"), min));
                    }
                }

                if (type>=0){
                    list.add(criteriaBuilder.equal(typepath,type));
                }


                Predicate[]p=new Predicate[list.size()];
                return criteriaBuilder.and(list.toArray(p));
            }
        },new PageRequest(start,rows));

    }


}
