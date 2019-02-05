package com.fuya.fuyadao.entity;

import javax.persistence.*;


@Entity
@Table(name = "COLLECTIONS")
public class COLLECTIONS {
    @Id
    @SequenceGenerator(name = "COLLECTIONS_SEQUENCEid",initialValue = 1,allocationSize=1,sequenceName = "COLLECTIONS_SEQUENCE")
    @GeneratedValue(generator = "COLLECTIONS_SEQUENCEid" ,strategy = GenerationType.SEQUENCE)
    private int ID;
    private int FROMID;
    private  int TOID;

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
}
