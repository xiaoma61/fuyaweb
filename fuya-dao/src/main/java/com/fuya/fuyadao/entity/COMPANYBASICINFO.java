package com.fuya.fuyadao.entity;



import javax.persistence.*;

@Entity
@Table(name = "COMPANYBASICINFO")
public class COMPANYBASICINFO {

    @Id
    @SequenceGenerator(name = "COMPANYBASICINFOSEQUENCEid",sequenceName = "COMPANYBASICINFOSEQUENCE",initialValue=1,allocationSize=1 )
    @GeneratedValue(strategy= GenerationType.SEQUENCE,generator="COMPANYBASICINFOSEQUENCEid")

    private int ID;
    private int USERID;
    private String INTRODUCE;
    private String ADDRESS;
    private int LEVELS;
    private String CORPORATENAME;
    private int NUMS;

    public int getNUMS() {
        return NUMS;
    }

    public void setNUMS(int NUMS) {
        this.NUMS = NUMS;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
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
