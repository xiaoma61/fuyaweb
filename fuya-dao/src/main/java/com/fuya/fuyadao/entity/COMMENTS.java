package com.fuya.fuyadao.entity;


import org.apache.solr.client.solrj.beans.Field;

import javax.persistence.*;

@Entity
@Table(name = "COMMENTS")
public class COMMENTS {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Field
    private int COMMENTSID;
    @Field
    private int ORDERID;
    @Field
    private String CONTENT;
    @Field
    private int LEVELS;
    @Field
    private int USERID;

    public int getCOMMENTSID() {
        return COMMENTSID;
    }

    public void setCOMMENTSID(int COMMENTSID) {
        this.COMMENTSID = COMMENTSID;
    }

    public int getORDERID() {
        return ORDERID;
    }

    public void setORDERID(int ORDERID) {
        this.ORDERID = ORDERID;
    }

    public String getCONTENT() {
        return CONTENT;
    }

    public void setCONTENT(String CONTENT) {
        this.CONTENT = CONTENT;
    }

    public int getLEVELS() {
        return LEVELS;
    }

    public void setLEVELS(int LEVELS) {
        this.LEVELS = LEVELS;
    }

    public int getUSERID() {
        return USERID;
    }

    public void setUSERID(int USERID) {
        this.USERID = USERID;
    }
}
