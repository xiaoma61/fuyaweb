package com.fuya.fuyadao.entity;

import org.apache.solr.client.solrj.beans.Field;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "SERVICECONTENT")
public class SERVICECONTENT {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Field
    private int SERVICECONTENTID;
    @Field
    private int ORDERID;
    @Field

    @Temporal(TemporalType.TIMESTAMP)

    private Date CHILDBIRTH;//预产期
    @Field
    @Temporal(TemporalType.TIMESTAMP)
    private Date STARTTIME;//服务开始日期
    @Field
    private int FATE;
    @Field
    private String PRICE;//价格
    @Field
    private String OTHERS;//其他
    @Field
    private int STATUS;//状态
    @Field
    private int HANDSELSTATUS;//定金支付状态
    @Field
    private String SUM;//全额
    @Field
    private String HANDSEL;//定金
    @Field
    private int SUMSTATUS;//全额支付状态

    public int getSERVICECONTENTID() {
        return SERVICECONTENTID;
    }

    public void setSERVICECONTENTID(int SERVICECONTENTID) {
        this.SERVICECONTENTID = SERVICECONTENTID;
    }

    public int getORDERID() {
        return ORDERID;
    }

    public void setORDERID(int ORDERID) {
        this.ORDERID = ORDERID;
    }


    public Date getCHILDBIRTH() {
        return CHILDBIRTH;
    }

    public void setCHILDBIRTH(Date CHILDBIRTH) {
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
