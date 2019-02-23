package com.fuya.fuyadao.model;

import com.fuya.fuyadao.entity.EMPLOYERINFORMATION;
import com.fuya.fuyadao.entity.ORDERS;
import com.fuya.fuyadao.entity.SERVICECONTENT;

public class ODERSEMPMSG {
    ORDERS orders;
    EMPLOYERINFORMATION employerinformation;
    SERVICECONTENT servicecontent;

    public  ODERSEMPMSG(EMPLOYERINFORMATION employerinformation,ORDERS orders,SERVICECONTENT servicecontent){

        this.orders=orders;
        this.employerinformation=employerinformation;
        this.servicecontent=servicecontent;
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
}
