package com.fuya.fuyadao.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "PROBLEM")
@NamedStoredProcedureQuery(name = "CHOOSE_PROCEDURE",procedureName = "CHOOSE_PROCEDURE",parameters = {
        @StoredProcedureParameter(mode = ParameterMode.IN,name = "MCHOOSEID" ,type = Integer.class ),
       @StoredProcedureParameter(mode = ParameterMode.IN,name = "PROMBLEID" ,type = Integer.class )

})
public class PROBLEM {
    @Id

    private int PROBLEMID;
    private int CHOOSEID;
    private String TITLE;
    private Date TIME;
    private String CHOOSETYPE;
    private String SUBJECTMATTER;

    public int getPROBLEMID() {
        return PROBLEMID;
    }

    public void setPROBLEMID(int PROBLEMID) {
        this.PROBLEMID = PROBLEMID;
    }

    public int getCHOOSEID() {
        return CHOOSEID;
    }

    public void setCHOOSEID(int CHOOSEID) {
        this.CHOOSEID = CHOOSEID;
    }

    public String getTITLE() {
        return TITLE;
    }

    public void setTITLE(String TITLE) {
        this.TITLE = TITLE;
    }

    public Date getTIME() {
        return TIME;
    }

    public void setTIME(Date TIME) {
        this.TIME = TIME;
    }

    public void setCHOOSETYPE(String CHOOSETYPE) {
        this.CHOOSETYPE = CHOOSETYPE;
    }

    public String getCHOOSETYPE() {
        return CHOOSETYPE;
    }

    public String getSUBJECTMATTER() {
        return SUBJECTMATTER;
    }

    public void setSUBJECTMATTER(String SUBJECTMATTER) {
        this.SUBJECTMATTER = SUBJECTMATTER;
    }
}
