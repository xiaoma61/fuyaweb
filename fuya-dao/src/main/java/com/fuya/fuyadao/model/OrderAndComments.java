package com.fuya.fuyadao.model;

import com.fuya.fuyadao.entity.COMMENTS;
import com.fuya.fuyadao.entity.ORDERS;

public class OrderAndComments {
    private  ORDERS orders;
    private COMMENTS comments;

    public OrderAndComments(ORDERS orders, COMMENTS comments) {
        this.orders = orders;
        this.comments = comments;
    }

    public ORDERS getOrders() {
        return orders;
    }

    public void setOrders(ORDERS orders) {
        this.orders = orders;
    }

    public COMMENTS getComments() {
        return comments;
    }

    public void setComments(COMMENTS comments) {
        this.comments = comments;
    }
}
