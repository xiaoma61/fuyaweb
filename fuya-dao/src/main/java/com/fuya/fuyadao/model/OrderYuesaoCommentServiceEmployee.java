package com.fuya.fuyadao.model;

import com.fuya.fuyadao.entity.*;

public class OrderYuesaoCommentServiceEmployee {
    private ORDERS orders;
    private EMPLOYERINFORMATION employerinformation;
    private SERVICECONTENT servicecontent;
    private COMMENTS comments;
    private YUESOBASICINFO yuesobasicinfo;
    public   OrderYuesaoCommentServiceEmployee(ORDERS orders, YUESOBASICINFO yuesobasicinfo, COMMENTS comments, SERVICECONTENT servicecontent, EMPLOYERINFORMATION employerinformation){
        this.orders=orders;
        this.yuesobasicinfo=yuesobasicinfo;
        this.comments=comments;
        this.servicecontent=servicecontent;
        this.employerinformation=employerinformation;

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

    public YUESOBASICINFO getYuesobasicinfo() {
        return yuesobasicinfo;
    }

    public void setYuesobasicinfo(YUESOBASICINFO yuesobasicinfo) {
        this.yuesobasicinfo = yuesobasicinfo;
    }
}
