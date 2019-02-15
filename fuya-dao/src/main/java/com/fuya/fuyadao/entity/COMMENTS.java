package com.fuya.fuyadao.entity;


import org.apache.solr.client.solrj.beans.Field;

import javax.persistence.*;

@Entity
@Table(name = "COMMENTS")
public class COMMENTS {

    @Id
    @SequenceGenerator(name = "COMMENTS_SEQUENCEid" ,initialValue = 1,allocationSize=1,sequenceName = "COMMENTS_SEQUENCE")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "COMMENTS_SEQUENCEid")
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
