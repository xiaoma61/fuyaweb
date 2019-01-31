package com.fuya.fuyadao.entity;

import javax.persistence.*;

@Entity
@Table(name = "EMPLOYERINFORMATION")
public class EMPLOYERINFORMATION {
    @Id
    @SequenceGenerator(name = "EMPLOYERINFORMATION_SEQUENCEid",initialValue = 1,sequenceName = "EMPLOYERINFORMATION_SEQUENCE")
    @GeneratedValue(generator = "EMPLOYERINFORMATION_SEQUENCEid" ,strategy = GenerationType.SEQUENCE)
    private int ID;
    private int ODERID;
    private String NAME;
    private String AREA;
    private String ADDRESS;
    private String PHONE;
    private int TYPE;
    private String IDCARD;


    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getODERID() {
        return ODERID;
    }

    public void setODERID(int ODERID) {
        this.ODERID = ODERID;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public String getAREA() {
        return AREA;
    }

    public void setAREA(String AREA) {
        this.AREA = AREA;
    }

    public String getADDRESS() {
        return ADDRESS;
    }

    public void setADDRESS(String ADDRESS) {
        this.ADDRESS = ADDRESS;
    }

    public String getPHONE() {
        return PHONE;
    }

    public void setPHONE(String PHONE) {
        this.PHONE = PHONE;
    }

    public int getTYPE() {
        return TYPE;
    }

    public void setTYPE(int TYPE) {
        this.TYPE = TYPE;
    }

    public String getIDCARD() {
        return IDCARD;
    }

    public void setIDCARD(String IDCARD) {
        this.IDCARD = IDCARD;
    }
}
