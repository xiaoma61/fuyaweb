package com.fuya.fuyadao.entity;

import org.apache.solr.client.solrj.beans.Field;

import javax.persistence.*;


@Entity
@Table(name = "COLLECTIONS")
public class COLLECTIONS {
    @Id
    @SequenceGenerator(name = "COLLECTIONS_SEQUENCEid",initialValue = 1,allocationSize=1,sequenceName = "COLLECTIONS_SEQUENCE")
    @GeneratedValue(generator = "COLLECTIONS_SEQUENCEid" ,strategy = GenerationType.SEQUENCE)
    @Field
    private int COLLECTIONSID;
    @Field
    private int FROMID;
    @Field
    private  int TOID;

    public int getCOLLECTIONSID() {
        return COLLECTIONSID;
    }

    public void setCOLLECTIONSID(int COLLECTIONSID) {
        this.COLLECTIONSID = COLLECTIONSID;
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
