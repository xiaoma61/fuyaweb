package com.fuya.fuyadao.entity;

import javax.persistence.*;

@Entity
@Table(name="YUESOBASICINFO")
public class YUESOBASICINFO {

    @Id
    @SequenceGenerator(name = "YUESOBASICINFO_SEQUENCEid",sequenceName ="YUESOBASICINFO_SEQUENCE", initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "YUESOBASICINFO_SEQUENCEid")
    private int ID;
    private int USERSID;
    private String NAME;
    private String PHONE;
    private String IDCARD;
    private String AGE;
    private String EDUCATION;
    private String NATIVEPLACE;
    private String EMAIL;
    private String PHOTO;
    private String HEIGHT;
    private String WEIGHT;
    private String SENIORITY;
    private String COMPANYID;
    private int LEVELS;
    private String WAGES;
    private String TYPE;
    private String WORKAREA;

    public String getWORKAREA() {
        return WORKAREA;
    }

    public void setWORKAREA(String WORKAREA) {
        this.WORKAREA = WORKAREA;
    }

    public String getTYPE() {
        return TYPE;
    }

    public void setTYPE(String TYPE) {
        this.TYPE = TYPE;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
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

    public String getAGE() {
        return AGE;
    }

    public void setAGE(String AGE) {
        this.AGE = AGE;
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

    public String getWAGES() {
        return WAGES;
    }

    public void setWAGES(String WAGES) {
        this.WAGES = WAGES;
    }
}
