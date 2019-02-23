package com.fuya.fuyadao.entity;

import org.apache.solr.client.solrj.beans.Field;

import javax.persistence.*;

@Entity
@Table(name = "YUESAOOTHERPROVE")
public class YUESAOOTHERPROVE {


    @Id
    @SequenceGenerator(name = "YUESAOOTHERPROVE_SEQUENCEid",initialValue = 1,allocationSize=1,sequenceName = "YUESAOOTHERPROVE_SEQUENCE")
    @GeneratedValue(generator = "YUESAOOTHERPROVE_SEQUENCEid" ,strategy = GenerationType.SEQUENCE)
    @Field
    private int YUESAOOTHERPROVEID;
    @Field
    private String FILEAREA;
    @Field
    private String TITLE;
    @Field
    private int USERID;

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
