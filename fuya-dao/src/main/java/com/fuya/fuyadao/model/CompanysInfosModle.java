package com.fuya.fuyadao.model;

import com.fuya.fuyadao.entity.COMPANYBASICINFO;
import com.fuya.fuyadao.entity.COMPANYINFO;

public class CompanysInfosModle {
    COMPANYBASICINFO companybasicinfo;
    COMPANYINFO companyinfo;

    public COMPANYBASICINFO getCompanybasicinfo() {
        return companybasicinfo;
    }

    public void setCompanybasicinfo(COMPANYBASICINFO companybasicinfo) {
        this.companybasicinfo = companybasicinfo;
    }

    public COMPANYINFO getCompanyinfo() {
        return companyinfo;
    }

    public void setCompanyinfo(COMPANYINFO companyinfo) {
        this.companyinfo = companyinfo;
    }
}
