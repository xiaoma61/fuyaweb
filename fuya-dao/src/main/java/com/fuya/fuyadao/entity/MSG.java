package com.fuya.fuyadao.entity;

import javax.persistence.*;
import java.sql.Date;

@Table(name = "MSG")
@Entity
public class MSG {
    @Id
    @SequenceGenerator(name = "MSG_SEQUENCEid",initialValue = 1,allocationSize=1,sequenceName = "MSG_SEQUENCE")
    @GeneratedValue(generator = "MSG_SEQUENCEid" ,strategy = GenerationType.SEQUENCE)
    private  int ID;
    private  int FROMID;
    private  int TOID;
    private  String MSG;
    private  Date TIME;

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

    public int getTOID() {
        return TOID;
    }

    public void setTOID(int TOID) {
        this.TOID = TOID;
    }

    public String getMSG() {
        return MSG;
    }

    public void setMSG(String MSG) {
        this.MSG = MSG;
    }

    public Date getTIME() {
        return TIME;
    }

    public void setTIME(Date TIME) {
        this.TIME = TIME;
    }
}
