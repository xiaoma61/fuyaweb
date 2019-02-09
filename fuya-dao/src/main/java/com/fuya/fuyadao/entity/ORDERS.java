package com.fuya.fuyadao.entity;

import javax.persistence.*;

@Entity
@Table(name = "ORDERS")
public class ORDERS {

    @Id
    @SequenceGenerator(name = "ORDERS_SEQUENCEid",sequenceName = "ORDERS_SEQUENCE",initialValue=1,allocationSize=1 )
    @GeneratedValue(strategy= GenerationType.SEQUENCE,generator="ORDERS_SEQUENCEid")
    private int ID;
    private int FROMID;
    private String CONTRACTNUMBER;//合同号码
    private int TOID;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getFROMID() {
        return FROMID;
    }

    public void setFROMID(int FROMID) {
        this.FROMID = FROMID;
    }

    public String getCONTRACTNUMBER() {
        return CONTRACTNUMBER;
    }

    public void setCONTRACTNUMBER(String CONTRACTNUMBER) {
        this.CONTRACTNUMBER = CONTRACTNUMBER;
    }

    public int getTOID() {
        return TOID;
    }

    public void setTOID(int TOID) {
        this.TOID = TOID;
    }
}
