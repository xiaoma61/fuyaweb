package com.fuya.fuyadao.entity;

import org.apache.solr.client.solrj.beans.Field;

import javax.persistence.*;

@Entity
@Table(name="YUESOBASICINFO")
public class YUESOBASICINFO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Field
    private int YUESOBASICINFOID;
    @Field
    private int USERSID;
    @Field
    private String NAME;
    @Field
    private String PHONE;
    @Field("YUESOBASICINFOIDCARD")
    private String IDCARD;
    @Field
    private int AGE;
    @Field
    private String EDUCATION;
    @Field
    private String NATIVEPLACE;
    @Field
    private String EMAIL;
    @Field
    private String PHOTO;
    @Field
    private String HEIGHT;
    @Field
    private String WEIGHT;
    @Field
    private String SENIORITY;
    @Field
    private String COMPANYID;

    @Field("YUESOBASICINFOLEVELS")
    private int LEVELS;
    @Field
    private int WAGES;
    @Field
    private int TYPE;
    @Field
    private String WORKAREA;

    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWORKAREA() {
        return WORKAREA;
    }

    public void setWORKAREA(String WORKAREA) {
        this.WORKAREA = WORKAREA;
    }

    public int getTYPE() {
        return TYPE;
    }

    public void setTYPE(int TYPE) {
        this.TYPE = TYPE;
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

    public String getPHONE() {
        return PHONE;
    }

    public void setPHONE(String PHONE) {
        this.PHONE = PHONE;
    }

    public String getIDCARD() {
        return IDCARD;
    }

    public void setIDCARD(String IDCARD) {
        this.IDCARD = IDCARD;
    }



    public String getEDUCATION() {
        return EDUCATION;
    }

    public void setEDUCATION(String EDUCATION) {
        this.EDUCATION = EDUCATION;
    }

    public String getNATIVEPLACE() {
        return NATIVEPLACE;
    }

    public void setNATIVEPLACE(String NATIVEPLACE) {
        this.NATIVEPLACE = NATIVEPLACE;
    }

    public String getEMAIL() {
        return EMAIL;
    }

    public void setEMAIL(String EMAIL) {
        this.EMAIL = EMAIL;
    }

    public String getPHOTO() {
        return PHOTO;
    }

    public void setPHOTO(String PHOTO) {
        this.PHOTO = PHOTO;
    }

    public String getHEIGHT() {
        return HEIGHT;
    }

    public void setHEIGHT(String HEIGHT) {
        this.HEIGHT = HEIGHT;
    }

    public String getWEIGHT() {
        return WEIGHT;
    }

    public void setWEIGHT(String WEIGHT) {
        this.WEIGHT = WEIGHT;
    }

    public String getSENIORITY() {
        return SENIORITY;
    }

    public void setSENIORITY(String SENIORITY) {
        this.SENIORITY = SENIORITY;
    }

    public String getCOMPANYID() {
        return COMPANYID;
    }

    public void setCOMPANYID(String COMPANYID) {
        this.COMPANYID = COMPANYID;
    }

    public int getLEVELS() {
        return LEVELS;
    }

    public void setLEVELS(int LEVELS) {
        this.LEVELS = LEVELS;
    }

    public int getAGE() {
        return AGE;
    }

    public void setAGE(int AGE) {
        this.AGE = AGE;
    }

    public int getWAGES() {
        return WAGES;
    }

    public void setWAGES(int WAGES) {
        this.WAGES = WAGES;
    }
}
