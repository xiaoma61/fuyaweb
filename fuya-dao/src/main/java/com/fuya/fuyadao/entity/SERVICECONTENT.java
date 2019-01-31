package com.fuya.fuyadao.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "SERVICECONTENT")
public class SERVICECONTENT {

    @Id
    @SequenceGenerator(name = "SERVICECONTENT_SEQUENCEid",sequenceName = "SERVICECONTENT_SEQUENCE",initialValue=1,allocationSize=1 )
    @GeneratedValue(strategy= GenerationType.SEQUENCE,generator="SERVICECONTENT_SEQUENCEid")
    private int ID;
    private int ORDERID;
    private String CHILDBIRTH;//预产期
    private Date STARTTIME;//服务开始日期
    private int FATE;
    private String PRICE;//价格
    private String OTHERS;//其他
    private int STATUS;//状态
    private int HANDSELSTATUS;//定金支付状态
    private String SUM;//全额
    private String HANDSEL;//定金
    private int SUMSTATUS;//全额支付状态

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getORDERID() {
        return ORDERID;
    }

    public void setORDERID(int ORDERID) {
        this.ORDERID = ORDERID;
    }

    public String getCHILDBIRTH() {
        return CHILDBIRTH;
    }

    public void setCHILDBIRTH(String CHILDBIRTH) {
        this.CHILDBIRTH = CHILDBIRTH;
    }

    public Date getSTARTTIME() {
        return STARTTIME;
    }

    public void setSTARTTIME(Date STARTTIME) {
        this.STARTTIME = STARTTIME;
    }

    public int getFATE() {
        return FATE;
    }

    public void setFATE(int FATE) {
        this.FATE = FATE;
    }

    public String getPRICE() {
        return PRICE;
    }

    public void setPRICE(String PRICE) {
        this.PRICE = PRICE;
    }

    public String getOTHERS() {
        return OTHERS;
    }

    public void setOTHERS(String OTHERS) {
        this.OTHERS = OTHERS;
    }

    public int getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(int STATUS) {
        this.STATUS = STATUS;
    }

    public int getHANDSELSTATUS() {
        return HANDSELSTATUS;
    }

    public void setHANDSELSTATUS(int HANDSELSTATUS) {
        this.HANDSELSTATUS = HANDSELSTATUS;
    }

    public String getSUM() {
        return SUM;
    }

    public void setSUM(String SUM) {
        this.SUM = SUM;
    }

    public String getHANDSEL() {
        return HANDSEL;
    }

    public void setHANDSEL(String HANDSEL) {
        this.HANDSEL = HANDSEL;
    }

    public int getSUMSTATUS() {
        return SUMSTATUS;
    }

    public void setSUMSTATUS(int SUMSTATUS) {
        this.SUMSTATUS = SUMSTATUS;
    }
}
