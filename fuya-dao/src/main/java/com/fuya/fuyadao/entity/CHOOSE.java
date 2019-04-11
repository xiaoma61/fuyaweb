package com.fuya.fuyadao.entity;

import javax.persistence.*;

@Entity
@Table(name = "CHOOSE")
public class CHOOSE {
    @Id
    private int CHOOSEID;
    private String ACHOOSE;
    private String BCHOOSE;
    private String CCHOOSE;
    private String DCHOOSE;
    private String ANSWER;


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


    public String getANSWER() {
        return ANSWER;
    }

    public void setANSWER(String ANSWER) {
        this.ANSWER = ANSWER;
    }
}
