package com.fuya.fuyadao.model;

public class CompanyRecruitinfo {
   // c.CORPORATENAME ,r.POSITION, r.TIME,r.SALARY,r.EDUCATION,r.WORKAREA,r.NUMS
    private String  CORPORATENAME;
    private String POSITION;
    private String TIME;
    private String SALARY;
    private String EDUCATION;
    private String WORKAREA;
    private String NUMS;
    private int RECRUITID;

    public String getCORPORATENAME() {
        return CORPORATENAME;
    }

    public void setCORPORATENAME(String CORPORATENAME) {
        this.CORPORATENAME = CORPORATENAME;
    }

    public String getPOSITION() {
        return POSITION;
    }

    public void setPOSITION(String POSITION) {
        this.POSITION = POSITION;
    }

    public String getTIME() {
        return TIME;
    }

    public void setTIME(String TIME) {
        this.TIME = TIME;
    }

    public String getSALARY() {
        return SALARY;
    }

    public void setSALARY(String SALARY) {
        this.SALARY = SALARY;
    }

    public String getEDUCATION() {
        return EDUCATION;
    }

    public void setEDUCATION(String EDUCATION) {
        this.EDUCATION = EDUCATION;
    }

    public String getWORKAREA() {
        return WORKAREA;
    }

    public void setWORKAREA(String WORKAREA) {
        this.WORKAREA = WORKAREA;
    }

    public String getNUMS() {
        return NUMS;
    }

    public void setNUMS(String NUMS) {
        this.NUMS = NUMS;
    }

    public int getRECRUITID() {
        return RECRUITID;
    }

    public void setRECRUITID(int RECRUITID) {
        this.RECRUITID = RECRUITID;
    }
}
