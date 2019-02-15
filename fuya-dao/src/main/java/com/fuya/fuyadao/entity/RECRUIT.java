package com.fuya.fuyadao.entity;

import org.apache.solr.client.solrj.beans.Field;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "RECRUIT")
public class RECRUIT {
    @Id
    @SequenceGenerator(name = "RECRUIT_SEQUENCEid",initialValue = 1,allocationSize=1,sequenceName = "RECRUIT_SEQUENCE")
    @GeneratedValue(generator = "RECRUIT_SEQUENCEid" ,strategy = GenerationType.SEQUENCE)
    @Field
    private int RECRUITID;
    @Field
    private int USERSID;
    @Field
    private String POSITION;
    @Field
    private String SALARY;
    @Field
    private Date TIME;
    @Field
    private String EDUCATION;
    @Field
    private String WORKAREA;
    @Field
    private String NUMS;
    @Field
    private String HIGHLIGHT;
    @Field
    private String LINKMAN;
    @Field
    private String PHONE;
    @Field
    private String DESCRIBE;
    @Field
    private String WORKBACKGROUND;
    @Field
    private Date STARTTIME;
    @Field
    private Date ENDTIME;

    public int getRECRUITID() {
        return RECRUITID;
    }

    public void setRECRUITID(int RECRUITID) {
        this.RECRUITID = RECRUITID;
    }

    public int getUSERSID() {
        return USERSID;
    }

    public void setUSERSID(int USERSID) {
        this.USERSID = USERSID;
    }

    public String getPOSITION() {
        return POSITION;
    }

    public void setPOSITION(String POSITION) {
        this.POSITION = POSITION;
    }

    public String getSALARY() {
        return SALARY;
    }

    public void setSALARY(String SALARY) {
        this.SALARY = SALARY;
    }

    public Date getTIME() {
        return TIME;
    }

    public void setTIME(Date TIME) {
        this.TIME = TIME;
    }

    public String getEDUCATION() {
        return EDUCATION;
    }

    public void setEDUCATION(String EDUCATION) {
        this.EDUCATION = EDUCATION;
    }

    public String getWORKAREA() {
        return WORKAREA;
    }

    public void setWORKAREA(String WORKAREA) {
        this.WORKAREA = WORKAREA;
    }

    public String getNUMS() {
        return NUMS;
    }

    public void setNUMS(String NUMS) {
        this.NUMS = NUMS;
    }

    public String getHIGHLIGHT() {
        return HIGHLIGHT;
    }

    public void setHIGHLIGHT(String HIGHLIGHT) {
        this.HIGHLIGHT = HIGHLIGHT;
    }

    public String getLINKMAN() {
        return LINKMAN;
    }

    public void setLINKMAN(String LINKMAN) {
        this.LINKMAN = LINKMAN;
    }

    public String getPHONE() {
        return PHONE;
    }

    public void setPHONE(String PHONE) {
        this.PHONE = PHONE;
    }

    public String getDESCRIBE() {
        return DESCRIBE;
    }

    public void setDESCRIBE(String DESCRIBE) {
        this.DESCRIBE = DESCRIBE;
    }

    public String getWORKBACKGROUND() {
        return WORKBACKGROUND;
    }

    public void setWORKBACKGROUND(String WORKBACKGROUND) {
        this.WORKBACKGROUND = WORKBACKGROUND;
    }

    public Date getSTARTTIME() {
        return STARTTIME;
    }

    public void setSTARTTIME(Date STARTTIME) {
        this.STARTTIME = STARTTIME;
    }

    public Date getENDTIME() {
        return ENDTIME;
    }

    public void setENDTIME(Date ENDTIME) {
        this.ENDTIME = ENDTIME;
    }
}
