package com.fuya.fuyaservice;

import com.fuya.fuyadao.entity.COMPANYBASICINFO;
import com.fuya.fuyadao.model.CompanysInfosModle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface COMPANYBASICINFOService {
    List<COMPANYBASICINFO> findAlllimit();
    COMPANYBASICINFO findByID(int ID);
    void save(COMPANYBASICINFO companybasicinfo);
    void   updateCOMPANYBASICINFObyid(String ADDRESS, String CORPORATENAME, int id);
    void  deleteByUSERID(int id);
    List<String>findByCORPORATENAMELike(@Param("name") String name);
    List<CompanysInfosModle>findCOMPANYBASICINFOByCORPORATENAMELike(@Param("name") String name);
    Page<COMPANYBASICINFO> findALL(int start, int rows);
    List<Object>find();
    Object findidMsg(int id);
    CompanysInfosModle findCompanysInfosModleByUSERID(int id);


}
