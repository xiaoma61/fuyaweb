package com.fuya.fuyadao.model;

import com.fuya.fuyadao.entity.PROVEINFO;
import com.fuya.fuyadao.entity.YUESOBASICINFO;

public class PROVEINFOANDBAISINFO {
    YUESOBASICINFO yuesobasicinfo;
    PROVEINFO proveinfo;

    public   PROVEINFOANDBAISINFO(YUESOBASICINFO yuesobasicinfo, PROVEINFO proveinfo){
        this.yuesobasicinfo=yuesobasicinfo;
        this.proveinfo=proveinfo;
    }


    public YUESOBASICINFO getYuesobasicinfo() {
        return yuesobasicinfo;
    }

    public void setYuesobasicinfo(YUESOBASICINFO yuesobasicinfo) {
        this.yuesobasicinfo = yuesobasicinfo;
    }

    public PROVEINFO getProveinfo() {
        return proveinfo;
    }

    public void setProveinfo(PROVEINFO proveinfo) {
        this.proveinfo = proveinfo;
    }
}
