package com.fuya.fuyadao.model;

public class CompanyInfo {
    private int USERSID;
    private String IDCARD;
    private String LICENCENO;
    private String CONTACTNAME;
    private String CORPORATENAME;

    public int getUSERSID() {
        return USERSID;
    }

    public void setUSERSID(int USERSID) {
        this.USERSID = USERSID;
    }

    public String getIDCARD() {
        return IDCARD;
    }

    public void setIDCARD(String IDCARD) {
        this.IDCARD = IDCARD;
    }

    public String getLICENCENO() {
        return LICENCENO;
    }

    public void setLICENCENO(String LICENCENO) {
        this.LICENCENO = LICENCENO;
    }

    public String getCONTACTNAME() {
        return CONTACTNAME;
    }

    public void setCONTACTNAME(String CONTACTNAME) {
        this.CONTACTNAME = CONTACTNAME;
    }

    public String getCORPORATENAME() {
        return CORPORATENAME;
    }

    public void setCORPORATENAME(String CORPORATENAME) {
        this.CORPORATENAME = CORPORATENAME;
    }
}
