package com.fuya.fuyadao.model;

public class ARTICLEmodel {

    private int ARTICLEID;

    private String  TITLE;

    private int TYPE;

    private String TIME;

    private String CONTENT;

    private int NUMS;

    public int getARTICLEID() {
        return ARTICLEID;
    }

    public void setARTICLEID(int ARTICLEID) {
        this.ARTICLEID = ARTICLEID;
    }

    public String getTITLE() {
        return TITLE;
    }

    public void setTITLE(String TITLE) {
        this.TITLE = TITLE;
    }

    public int getTYPE() {
        return TYPE;
    }

    public void setTYPE(int TYPE) {
        this.TYPE = TYPE;
    }

    public String getTIME() {
        return TIME;
    }

    public void setTIME(String TIME) {
        this.TIME = TIME;
    }

    public String getCONTENT() {
        return CONTENT;
    }

    public void setCONTENT(String CONTENT) {
        this.CONTENT = CONTENT;
    }

    public int getNUMS() {
        return NUMS;
    }

    public void setNUMS(int NUMS) {
        this.NUMS = NUMS;
    }
}
