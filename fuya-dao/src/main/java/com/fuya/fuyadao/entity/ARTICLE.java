package com.fuya.fuyadao.entity;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "ARTICLE")
public class ARTICLE {
    @Id
    @SequenceGenerator(name = "ARTICLE_SEQUENCEid",initialValue = 1,sequenceName = "ARTICLE_SEQUENCE")
    @GeneratedValue(generator = "ARTICLE_SEQUENCEid" ,strategy = GenerationType.SEQUENCE)
    private int ID;
    private String  TITLE;
    private int TYPE;
    private Date TIME;
    private String CONTENT;
    private int NUMS;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
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
