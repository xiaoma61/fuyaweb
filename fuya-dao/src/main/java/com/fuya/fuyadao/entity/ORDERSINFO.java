package com.fuya.fuyadao.entity;

import java.util.List;

public class ORDERSINFO {
    List<?> comments;
    EMPLOYERINFORMATION employerinformation;
    ORDERS orders;





    public List<?> getComments() {
        return comments;
    }

    public void setComments(List<?> comments) {
        this.comments = comments;
    }

    public EMPLOYERINFORMATION getEmployerinformation() {
        return employerinformation;
    }

    public void setEmployerinformation(EMPLOYERINFORMATION employerinformation) {
        this.employerinformation = employerinformation;
    }

    public ORDERS getOrders() {
        return orders;
    }

    public void setOrders(ORDERS orders) {
        this.orders = orders;
    }
}
