package com.fuya.fuyadao.entity;

import org.apache.solr.client.solrj.beans.Field;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "PERMISSION")
public class PERMISSION {
    @Id
    @Field
    @Column(name = "PERMISSIONID")
    private int PERMISSIONID;
    @Field
    private String  URL;
    @Field
    private String  TYPE;
    @Field
    private String  NAME;
    @Field
    private String  PERMISSIONS;

    public String getPERMISSIONS() {
        return PERMISSIONS;
    }

    public void setPERMISSIONS(String PERMISSIONS) {
        this.PERMISSIONS = PERMISSIONS;
    }

    public int getPERMISSIONID() {
        return PERMISSIONID;
    }

    public void setPERMISSIONID(int PERMISSIONID) {
        this.PERMISSIONID = PERMISSIONID;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public String getTYPE() {
        return TYPE;
    }

    public void setTYPE(String TYPE) {
        this.TYPE = TYPE;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }
}
