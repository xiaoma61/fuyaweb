package com.fuya.fuyadao.entity;

import org.apache.solr.client.solrj.beans.Field;

import javax.persistence.*;

@Entity
@Table(name = "ORDERS")
public class ORDERS {

    @Id
    @Field
    private int ORDERSID;
    @Field
    private int FROMID;
    @Field
    private String CONTRACTNUMBER;//合同号码
    @Field
    private int TOID;

    public int getORDERSID() {
        return ORDERSID;
    }

    public void setORDERSID(int ORDERSID) {
        this.ORDERSID = ORDERSID;
    }

    public int getFROMID() {
        return FROMID;
    }

    public void setFROMID(int FROMID) {
        this.FROMID = FROMID;
    }

    public String getCONTRACTNUMBER() {
        return CONTRACTNUMBER;
    }

    public void setCONTRACTNUMBER(String CONTRACTNUMBER) {
        this.CONTRACTNUMBER = CONTRACTNUMBER;
    }

    public int getTOID() {
        return TOID;
    }

    public void setTOID(int TOID) {
        this.TOID = TOID;
    }
}
