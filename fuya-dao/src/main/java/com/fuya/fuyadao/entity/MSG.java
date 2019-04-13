package com.fuya.fuyadao.entity;

import org.apache.solr.client.solrj.beans.Field;

import javax.persistence.*;
import java.sql.Date;

@Table(name = "MSG")
@Entity
public class MSG {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Field
    private  int MSGID;
    @Field
    private  int FROMID;
    @Field
    private  int TOID;
    @Field
    private  String MSG;
    @Field
    private  Date TIME;
    private int TYPE;

    public int getTYPE() {
        return TYPE;
    }

    public void setTYPE(int TYPE) {
        this.TYPE = TYPE;
    }

    public int getMSGID() {
        return MSGID;
    }

    public void setMSGID(int MSGID) {
        this.MSGID = MSGID;
    }

    public int getFROMID() {
        return FROMID;
    }

    public void setFROMID(int FROMID) {
        this.FROMID = FROMID;
    }

    public int getTOID() {
        return TOID;
    }

    public void setTOID(int TOID) {
        this.TOID = TOID;
    }

    public String getMSG() {
        return MSG;
    }

    public void setMSG(String MSG) {
        this.MSG = MSG;
    }

    public Date getTIME() {
        return TIME;
    }

    public void setTIME(Date TIME) {
        this.TIME = TIME;
    }
}
