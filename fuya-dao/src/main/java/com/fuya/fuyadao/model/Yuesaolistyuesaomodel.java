package com.fuya.fuyadao.model;

import javax.persistence.Entity;
import javax.persistence.Table;

public class Yuesaolistyuesaomodel {

    private int YUESOBASICINFOID;
    private int USERSID;
    private String NAME;
    private int LEVELS;
    private String AGE;
    private String TYPE;
    private String NATIVEPLACE;
    private int WAGES;
    private String PHOTO;

    public Yuesaolistyuesaomodel() {
    }

    public Yuesaolistyuesaomodel(int YUESOBASICINFOID, int USERSID, String NAME, int LEVELS, String AGE, String TYPE, String NATIVEPLACE, int WAGES, String PHOTO) {
        this.YUESOBASICINFOID = YUESOBASICINFOID;
        this.USERSID = USERSID;
        this.NAME = NAME;
        this.LEVELS = LEVELS;
        this.AGE = AGE;
        this.TYPE = TYPE;
        this.NATIVEPLACE = NATIVEPLACE;
        this.WAGES = WAGES;
        this.PHOTO = PHOTO;
    }

    public String getPHOTO() {
        return PHOTO;
    }

    public void setPHOTO(String PHOTO) {
        this.PHOTO = PHOTO;
    }

    public int getYUESOBASICINFOID() {
        return YUESOBASICINFOID;
    }

    public void setYUESOBASICINFOID(int YUESOBASICINFOID) {
        this.YUESOBASICINFOID = YUESOBASICINFOID;
    }

    public int getUSERSID() {
        return USERSID;
    }

    public void setUSERSID(int USERSID) {
        this.USERSID = USERSID;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public int getLEVELS() {
        return LEVELS;
    }

    public void setLEVELS(int LEVELS) {
        this.LEVELS = LEVELS;
    }

    public String getAGE() {
        return AGE;
    }

    public void setAGE(String AGE) {
        this.AGE = AGE;
    }

    public String getTYPE() {
        return TYPE;
    }

    public void setTYPE(String TYPE) {
        this.TYPE = TYPE;
    }

    public String getNATIVEPLACE() {
        return NATIVEPLACE;
    }

    public void setNATIVEPLACE(String NATIVEPLACE) {
        this.NATIVEPLACE = NATIVEPLACE;
    }

    public int getWAGES() {
        return WAGES;
    }

    public void setWAGES(int WAGES) {
        this.WAGES = WAGES;
    }
}
