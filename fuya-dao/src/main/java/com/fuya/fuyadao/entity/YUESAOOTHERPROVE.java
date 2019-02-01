package com.fuya.fuyadao.entity;

import javax.persistence.*;

@Entity
@Table(name = "YUESAOOTHERPROVE")
public class YUESAOOTHERPROVE {
    @Id
    @SequenceGenerator(name = "YUESAOOTHERPROVE_SEQUENCEid",initialValue = 1,sequenceName = "YUESAOOTHERPROVE_SEQUENCE")
    @GeneratedValue(generator = "YUESAOOTHERPROVE_SEQUENCEid" ,strategy = GenerationType.SEQUENCE)

    private int ID;
    private String FILEAREA;
    private String TITLE;
    private int USERID;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
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
