package com.fuya.fuyadao.entity;

import javax.persistence.*;

@Entity
@Table(name = "SKILL")
public class SKILL {
    private int ID;
    private int USERID;
    private int TYPE;
    private String SKILL;
    @Id
    @SequenceGenerator(name = "SKILL_SEQUENCEid",initialValue = 1,sequenceName = "SKILL_SEQUENCE")
    @GeneratedValue(generator = "SKILL_SEQUENCEid" ,strategy = GenerationType.SEQUENCE)

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getUSERID() {
        return USERID;
    }

    public void setUSERID(int USERID) {
        this.USERID = USERID;
    }

    public int getTYPE() {
        return TYPE;
    }

    public void setTYPE(int TYPE) {
        this.TYPE = TYPE;
    }

    public String getSKILL() {
        return SKILL;
    }

    public void setSKILL(String SKILL) {
        this.SKILL = SKILL;
    }
}
