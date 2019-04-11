package com.fuya.fuyadao.entity;

import org.apache.solr.client.solrj.beans.Field;

import javax.persistence.*;

@Table(name="USERS")
@Entity
@NamedStoredProcedureQuery(name = "USER_PROCEDURE",procedureName = "USER_PROCEDURE",parameters = {@StoredProcedureParameter(mode = ParameterMode.IN,name = "ID",type = Integer.class )})
public class USERS {
    @Id

    @Column(name="USERSID")
    @Field
    private int USERSID;
    @Field
    private String IMAGE;
    @Field
    private String NAME;
    @Field
    private String PHONE;
    @Field
    private String PASSWORD;
    @Field
    private int TYPE;


    public int getUSERSID() {
        return USERSID;
    }

    public void setUSERSID(int USERSID) {
        this.USERSID = USERSID;
    }

    public String getIMAGE() {
        return IMAGE;
    }

    public void setIMAGE(String IMAGE) {
        this.IMAGE = IMAGE;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public String getPHONE() {
        return PHONE;
    }

    public void setPHONE(String PHONE) {
        this.PHONE = PHONE;
    }

    public String getPASSWORD() {
        return PASSWORD;
    }

    public void setPASSWORD(String PASSWORD) {
        this.PASSWORD = PASSWORD;
    }

    public int getTYPE() {
        return TYPE;
    }

    public void setTYPE(int TYPE) {
        this.TYPE = TYPE;
    }
}
