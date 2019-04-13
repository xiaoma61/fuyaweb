package com.fuya.fuyadao.entity;

import org.apache.solr.client.solrj.beans.Field;

import javax.persistence.*;

@Entity
@Table(name = "COMPANYINFO")
public class COMPANYINFO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Field
    private int COMPANYINFOID;
    @Field
    private int USERSID;
    @Field
    private String CONTACTNAME;
    @Field
    private String CONTACTPHONE;
    @Field
    private String LICENCENO;
    @Field
    private String IDCARD;
    @Field
    private String IDCARDFILE;
    @Field
    private String ADDRESS;
    @Field
    private String LICENCE;
    @Field
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

    public int getCOMPANYINFOID() {
        return COMPANYINFOID;
    }

    public void setCOMPANYINFOID(int COMPANYINFOID) {
        this.COMPANYINFOID = COMPANYINFOID;
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
