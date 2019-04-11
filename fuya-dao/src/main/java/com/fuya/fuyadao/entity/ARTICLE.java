package com.fuya.fuyadao.entity;

import org.apache.solr.client.solrj.beans.Field;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "ARTICLE")
public class ARTICLE {


    @Id
    @Field
    private int ARTICLEID;
    @Field
    private String  TITLE;
    @Field
    private int TYPE;
    @Field
    private Date TIME;
    @Field
    private String CONTENT;
    @Field
    private int NUMS;

    public int getARTICLEID() {
        return ARTICLEID;
    }

    public void setARTICLEID(int ARTICLEID) {
        this.ARTICLEID = ARTICLEID;
    }

    public String getTITLE() {
        return TITLE;
    }

    public void setTITLE(String TITLE) {
        this.TITLE = TITLE;
    }

    public int getTYPE() {
        return TYPE;
    }

    public void setTYPE(int TYPE) {
        this.TYPE = TYPE;
    }

    public Date getTIME() {
        return TIME;
    }

    public void setTIME(Date TIME) {
        this.TIME = TIME;
    }

    public String getCONTENT() {
        return CONTENT;
    }

    public void setCONTENT(String CONTENT) {
        this.CONTENT = CONTENT;
    }

    public int getNUMS() {
        return NUMS;
    }

    public void setNUMS(int NUMS) {
        this.NUMS = NUMS;
    }
}
