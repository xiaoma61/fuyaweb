package com.fuya.fuyadao.entity;



import org.apache.solr.client.solrj.beans.Field;

import javax.persistence.*;

@Entity
@Table(name = "COMPANYBASICINFO")
public class COMPANYBASICINFO {


    @Id
    @Field
    private int COMPANYBASICINFOID;
    @Field
    private int USERID;
    @Field
    private String INTRODUCE;
    @Field
    private String ADDRESS;
    @Field
    private int LEVELS;
    @Field
    private String CORPORATENAME;
    @Field
    private int NUMS;

    public int getNUMS() {
        return NUMS;
    }

    public void setNUMS(int NUMS) {
        this.NUMS = NUMS;
    }

    public int getCOMPANYBASICINFOID() {
        return COMPANYBASICINFOID;
    }

    public void setCOMPANYBASICINFOID(int COMPANYBASICINFOID) {
        this.COMPANYBASICINFOID = COMPANYBASICINFOID;
    }

    public int getUSERID() {
        return USERID;
    }

    public void setUSERID(int USERID) {
        this.USERID = USERID;
    }

    public String getINTRODUCE() {
        return INTRODUCE;
    }

    public void setINTRODUCE(String INTRODUCE) {
        this.INTRODUCE = INTRODUCE;
    }

    public String getADDRESS() {
        return ADDRESS;
    }

    public void setADDRESS(String ADDRESS) {
        this.ADDRESS = ADDRESS;
    }

    public int getLEVELS() {
        return LEVELS;
    }

    public void setLEVELS(int LEVELS) {
        this.LEVELS = LEVELS;
    }

    public String getCORPORATENAME() {
        return CORPORATENAME;
    }

    public void setCORPORATENAME(String CORPORATENAME) {
        this.CORPORATENAME = CORPORATENAME;
    }
}
