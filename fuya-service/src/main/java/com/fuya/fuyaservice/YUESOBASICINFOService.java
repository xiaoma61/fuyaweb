package com.fuya.fuyaservice;


import com.fuya.fuyadao.entity.YUESOBASICINFO;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface YUESOBASICINFOService {
   public  List<YUESOBASICINFO>findAlllimit();
   public  List<YUESOBASICINFO>findAll();
   public  void save(YUESOBASICINFO yuesobasicinfo);
   public YUESOBASICINFO findbyid(int id);
   YUESOBASICINFO findByUSERSID(int userid);
   void deleteByUSERSID(int id);
   void update(String IDCARD, String EMAIL, String WORKAREA, String PHONE, String EDUCATION, String NAME, String WEIGHT, String AGE, String HEIGHT, String NATIVEPLACE, String PHOTO, String SENIORITY, String WAGES, int YUESOBASICINFOID);
   List<Object>findByTYPE(int type);
   List<String>findByNAMELike(int id, @Param("name") String name);
   String findEMILbyUERSID(int id);
   Object findObjectByUSERSID(int userid);
   public Page<YUESOBASICINFO>query(String name, String workarea, String minwages, String maxwages, int type, String nativeplace, String minage, String maxage, int start, int rows,int companyid);

}
