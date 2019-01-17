package com.fuya.fuyadao.entity;

import javax.persistence.*;
@Table(name="USERS")
@Entity
public class USERS {
    @Id
    @SequenceGenerator(name = "USERS_SEQUENCEid",sequenceName = "USERS_SEQUENCE",initialValue=1,allocationSize=1 )
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="USERS_SEQUENCE")
    @Column(name="ID")
    private int ID;
    private String IMAGE;
    private String NAME;
    private String PHONE;
    private String PASSWORD;
    private int TYPE;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
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
