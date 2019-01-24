package com.fuya.fuyadao.entity;

import com.sun.javafx.beans.IDProperty;

import javax.persistence.*;

@Entity
@Table(name = "COMPANYINFO")
public class COMPANYINFO {

    @Id

    @SequenceGenerator(name = "COMPANYINFO_SEQUENCEid",sequenceName = "COMPANYINFO_SEQUENCE",initialValue =1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator ="COMPANYINFO_SEQUENCEid")
    private int ID;
    private int USERSID;
    private String CONTACTNAME;
    private String CONTACTPHONE;
    private String LICENCENO;
    private String IDCARD;
    private String IDCARDFILE;
    private String ADDRESS;
    private String LICENCE;
    private String EMAIL;

    public String getLICENCE() {
        return LICENCE;
    }

    public void setLICENCE(String LICENCE) {
        this.LICENCE = LICENCE;
    }

    public String getEMAIL() {
        return EMAIL;
    }

    public void setEMAIL(String EMAIL) {
        this.EMAIL = EMAIL;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getUSERSID() {
        return USERSID;
    }

    public void setUSERSID(int USERSID) {
        this.USERSID = USERSID;
    }

    public String getCONTACTNAME() {
        return CONTACTNAME;
    }

    public void setCONTACTNAME(String CONTACTNAME) {
        this.CONTACTNAME = CONTACTNAME;
    }

    public String getCONTACTPHONE() {
        return CONTACTPHONE;
    }

    public void setCONTACTPHONE(String CONTACTPHONE) {
        this.CONTACTPHONE = CONTACTPHONE;
    }

    public String getLICENCENO() {
        return LICENCENO;
    }

    public void setLICENCENO(String LICENCENO) {
        this.LICENCENO = LICENCENO;
    }

    public String getIDCARD() {
        return IDCARD;
    }

    public void setIDCARD(String IDCARD) {
        this.IDCARD = IDCARD;
    }

    public String getIDCARDFILE() {
        return IDCARDFILE;
    }

    public void setIDCARDFILE(String IDCARDFILE) {
        this.IDCARDFILE = IDCARDFILE;
    }

    public String getADDRESS() {
        return ADDRESS;
    }

    public void setADDRESS(String ADDRESS) {
        this.ADDRESS = ADDRESS;
    }
}
