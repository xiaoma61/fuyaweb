package com.fuya.fuyadao.entity;


import javax.persistence.*;

@Entity
@Table(name = "COMMENTS")
public class COMMENTS {

    @Id
    @SequenceGenerator(name = "COMMENTS_SEQUENCEid" ,initialValue = 1,allocationSize=1,sequenceName = "COMMENTS_SEQUENCE")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "COMMENTS_SEQUENCEid")
    private int ID;
    private int ORDERID;
    private String CONTENT;
    private int LEVELS;
    private int USERID;

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
