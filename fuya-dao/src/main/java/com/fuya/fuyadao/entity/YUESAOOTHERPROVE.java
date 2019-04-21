package com.fuya.fuyadao.entity;

import org.apache.solr.client.solrj.beans.Field;

import javax.persistence.*;

@Entity
@Table(name = "YUESAOOTHERPROVE")
public class YUESAOOTHERPROVE {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Field
    private int YUESAOOTHERPROVEID;
    @Field
    private String FILEAREA;
    @Field
    private String TITLE;
    @Field
    private int USERID;
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getYUESAOOTHERPROVEID() {
        return YUESAOOTHERPROVEID;
    }

    public void setYUESAOOTHERPROVEID(int YUESAOOTHERPROVEID) {
        this.YUESAOOTHERPROVEID = YUESAOOTHERPROVEID;
    }

    public String getFILEAREA() {
        return FILEAREA;
    }

    public void setFILEAREA(String FILEAREA) {
        this.FILEAREA = FILEAREA;
    }

    public String getTITLE() {
        return TITLE;
    }

    public void setTITLE(String TITLE) {
        this.TITLE = TITLE;
    }

    public int getUSERID() {
        return USERID;
    }

    public void setUSERID(int USERID) {
        this.USERID = USERID;
    }
}
