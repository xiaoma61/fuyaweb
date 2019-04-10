package com.fuya.fuyadao.model;

import com.fuya.fuyadao.entity.PROVEINFO;
import com.fuya.fuyadao.entity.YUESOBASICINFO;

import java.util.List;

public class YUESAOINFO {
    YUESOBASICINFO yuesobasicinfo;
    List<PROVEINFO> proveinfo;
    public  YUESAOINFO(){
    }
    public   YUESAOINFO(YUESOBASICINFO yuesobasicinfo, List<PROVEINFO> proveinfo){
        this.yuesobasicinfo=yuesobasicinfo;
        this.proveinfo=proveinfo;
    }


    public YUESOBASICINFO getYuesobasicinfo() {
        return yuesobasicinfo;
    }

    public void setYuesobasicinfo(YUESOBASICINFO yuesobasicinfo) {
        this.yuesobasicinfo = yuesobasicinfo;
    }

    public List<PROVEINFO> getProveinfo() {
        return proveinfo;
    }

    public void setProveinfo(List<PROVEINFO> proveinfo) {
        this.proveinfo = proveinfo;
    }




}
