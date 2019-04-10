package com.fuya.fuyadao.model;

import java.util.List;

public class YUESAOORDERSERIVECONTENT {
    private List<PROVEINFOANDBAISINFO> objectList;
    private String name;
    private int userid;
    private String type;

    public List<PROVEINFOANDBAISINFO> getObjectList() {
        return objectList;
    }

    public void setObjectList(List<PROVEINFOANDBAISINFO> objectList) {
        this.objectList = objectList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
