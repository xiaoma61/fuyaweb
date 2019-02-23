package com.fuya.fuyaservice;


import com.fuya.fuyadao.entity.YUESOBASICINFO;

import java.util.List;

public interface YUESOBASICINFOService {
   public  List<YUESOBASICINFO>findAlllimit();
   public  List<YUESOBASICINFO>findAll();
   public  void save(YUESOBASICINFO yuesobasicinfo);
   public YUESOBASICINFO findbyid(int id);
   YUESOBASICINFO findByUSERSID(int userid);
   void deleteByYUESOBASICINFOID(int id);
   void update(String IDCARD,  String EMAIL ,String WORKAREA ,String PHONE ,String EDUCATION ,String NAME ,String WEIGHT,String AGE ,String HEIGHT ,String NATIVEPLACE ,String PHOTO ,String SENIORITY ,String WAGES  , int YUESOBASICINFOID );

}
