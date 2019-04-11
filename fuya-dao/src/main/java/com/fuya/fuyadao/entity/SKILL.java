package com.fuya.fuyadao.entity;

import org.apache.solr.client.solrj.beans.Field;

import javax.persistence.*;

@Entity
@Table(name = "SKILL")
public class SKILL {
    @Id

    @Field
    private int SKILLID;
    @Field
    private int USERID;
    @Field
    private int TYPE;
    @Field
    private String SKILL;


    public int getSKILLID() {
        return SKILLID;
    }

    public void setSKILLID(int SKILLID) {
        this.SKILLID = SKILLID;
    }

    public int getUSERID() {
        return USERID;
    }

    public void setUSERID(int USERID) {
        this.USERID = USERID;
    }

    public int getTYPE() {
        return TYPE;
    }

    public void setTYPE(int TYPE) {
        this.TYPE = TYPE;
    }

    public String getSKILL() {
        return SKILL;
    }

    public void setSKILL(String SKILL) {
        this.SKILL = SKILL;
    }
}
