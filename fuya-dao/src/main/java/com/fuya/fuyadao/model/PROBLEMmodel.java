package com.fuya.fuyadao.model;

public class PROBLEMmodel {
    private int CHOOSEID;
    private String ACHOOSE;
    private String BCHOOSE;
    private String CCHOOSE;
    private String DCHOOSE;
    private int PROBLEMID;
    private String TITLE;

    public PROBLEMmodel() {

    }

    public PROBLEMmodel(int CHOOSEID, String ACHOOSE, String BCHOOSE, String CCHOOSE, String DCHOOSE, int PROBLEMID, String TITLE) {
        this.CHOOSEID = CHOOSEID;
        this.ACHOOSE = ACHOOSE;
        this.BCHOOSE = BCHOOSE;
        this.CCHOOSE = CCHOOSE;
        this.DCHOOSE = DCHOOSE;
        this.PROBLEMID = PROBLEMID;
        this.TITLE = TITLE;
    }

    public int getCHOOSEID() {
        return CHOOSEID;
    }

    public void setCHOOSEID(int CHOOSEID) {
        this.CHOOSEID = CHOOSEID;
    }

    public String getACHOOSE() {
        return ACHOOSE;
    }

    public void setACHOOSE(String ACHOOSE) {
        this.ACHOOSE = ACHOOSE;
    }

    public String getBCHOOSE() {
        return BCHOOSE;
    }

    public void setBCHOOSE(String BCHOOSE) {
        this.BCHOOSE = BCHOOSE;
    }

    public String getCCHOOSE() {
        return CCHOOSE;
    }

    public void setCCHOOSE(String CCHOOSE) {
        this.CCHOOSE = CCHOOSE;
    }

    public String getDCHOOSE() {
        return DCHOOSE;
    }

    public void setDCHOOSE(String DCHOOSE) {
        this.DCHOOSE = DCHOOSE;
    }

    public int getPROBLEMID() {
        return PROBLEMID;
    }

    public void setPROBLEMID(int PROBLEMID) {
        this.PROBLEMID = PROBLEMID;
    }

    public String getTITLE() {
        return TITLE;
    }

    public void setTITLE(String TITLE) {
        this.TITLE = TITLE;
    }
}
