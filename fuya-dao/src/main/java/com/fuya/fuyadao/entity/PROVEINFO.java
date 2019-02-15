package com.fuya.fuyadao.entity;


import org.apache.solr.client.solrj.beans.Field;

import javax.persistence.*;

@Entity
@Table(name = "PROVEINFO")
public class PROVEINFO {
    @Id
    @SequenceGenerator(name = "PROVEINFO_SEQUENCEid",initialValue = 1,allocationSize=1,sequenceName = "PROVEINFO_SEQUENCE")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "PROVEINFO_SEQUENCEid")
    @Field
    private  int PROVEINFOID;
    @Field
    private  int USERSID;
    @Field
    private String YUESAOSYNDROME;
    @Field
    private String HEALTHCERTIFICATES;
    @Field
    private String SERVICEPICTURE;
    @Field
    private String REPORT;
    @Field
    private String SCORE;


    public int getPROVEINFOID() {
        return PROVEINFOID;
    }

    public void setPROVEINFOID(int PROVEINFOID) {
        this.PROVEINFOID = PROVEINFOID;
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
