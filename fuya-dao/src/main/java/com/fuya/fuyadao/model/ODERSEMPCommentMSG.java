package com.fuya.fuyadao.model;

import com.fuya.fuyadao.entity.COMMENTS;
import com.fuya.fuyadao.entity.EMPLOYERINFORMATION;
import com.fuya.fuyadao.entity.ORDERS;
import com.fuya.fuyadao.entity.SERVICECONTENT;

public class ODERSEMPCommentMSG {
    ORDERS orders;
    EMPLOYERINFORMATION employerinformation;
    SERVICECONTENT servicecontent;
    COMMENTS comments;
    public  ODERSEMPCommentMSG(EMPLOYERINFORMATION employerinformation, ORDERS orders, SERVICECONTENT servicecontent, COMMENTS comments){
        this.employerinformation=employerinformation;
        this.orders=orders;
        this.servicecontent=servicecontent;
        this.comments=comments;


    }



    public ORDERS getOrders() {
        return orders;
    }

    public void setOrders(ORDERS orders) {
        this.orders = orders;
    }

    public EMPLOYERINFORMATION getEmployerinformation() {
        return employerinformation;
    }

    public void setEmployerinformation(EMPLOYERINFORMATION employerinformation) {
        this.employerinformation = employerinformation;
    }

    public SERVICECONTENT getServicecontent() {
        return servicecontent;
    }

    public void setServicecontent(SERVICECONTENT servicecontent) {
        this.servicecontent = servicecontent;
    }

    public COMMENTS getComments() {
        return comments;
    }

    public void setComments(COMMENTS comments) {
        this.comments = comments;
    }
}
