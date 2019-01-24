package com.fuya.fuyadao.entity;


import javax.persistence.*;

@Entity
@Table(name = "PROVEINFO")
public class PROVEINFO {
    @Id
    @SequenceGenerator(name = "PROVEINFO_SEQUENCEid",initialValue = 1,sequenceName = "PROVEINFO_SEQUENCE")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "PROVEINFO_SEQUENCEid")
    private  int ID;
    private  int USERSID;
    private String YUESAOSYNDROME;
    private String HEALTHCERTIFICATES;
    private String SERVICEPICTURE;

    private String REPORT;
    private String SCORE;


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

    public String getYUESAOSYNDROME() {
        return YUESAOSYNDROME;
    }

    public void setYUESAOSYNDROME(String YUESAOSYNDROME) {
        this.YUESAOSYNDROME = YUESAOSYNDROME;
    }

    public String getHEALTHCERTIFICATES() {
        return HEALTHCERTIFICATES;
    }

    public void setHEALTHCERTIFICATES(String HEALTHCERTIFICATES) {
        this.HEALTHCERTIFICATES = HEALTHCERTIFICATES;
    }

    public String getSERVICEPICTURE() {
        return SERVICEPICTURE;
    }

    public void setSERVICEPICTURE(String SERVICEPICTURE) {
        this.SERVICEPICTURE = SERVICEPICTURE;
    }



    public String getREPORT() {
        return REPORT;
    }

    public void setREPORT(String REPORT) {
        this.REPORT = REPORT;
    }

    public String getSCORE() {
        return SCORE;
    }

    public void setSCORE(String SCORE) {
        this.SCORE = SCORE;
    }


}
