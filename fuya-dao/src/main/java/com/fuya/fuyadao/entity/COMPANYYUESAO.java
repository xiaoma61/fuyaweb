package com.fuya.fuyadao.entity;

import org.apache.solr.client.solrj.beans.Field;

import javax.persistence.*;

@Entity
@Table(name = "COMPANYYUESAO")
public class COMPANYYUESAO {
    @Id
    @SequenceGenerator(name = "COMPANYYUESAO_SEQUENCEid",initialValue = 1,allocationSize=1,sequenceName = "COMPANYYUESAO_SEQUENCE")
    @GeneratedValue(generator = "COMPANYYUESAO_SEQUENCEid" ,strategy = GenerationType.SEQUENCE)
    @Field
    private int COMPANYYUESAOID;
    @Field
    private int YUESAOID;
    @Field
    private int COMPANYID;

    public int getCOMPANYYUESAOID() {
        return COMPANYYUESAOID;
    }

    public void setCOMPANYYUESAOID(int COMPANYYUESAOID) {
        this.COMPANYYUESAOID = COMPANYYUESAOID;
    }

    public int getYUESAOID() {
        return YUESAOID;
    }

    public void setYUESAOID(int YUESAOID) {
        this.YUESAOID = YUESAOID;
    }

    public int getCOMPANYID() {
        return COMPANYID;
    }

    public void setCOMPANYID(int COMPANYID) {
        this.COMPANYID = COMPANYID;
    }
}
