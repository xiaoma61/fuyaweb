package com.fuya.fuyadao.model;

import com.fuya.fuyadao.entity.ARTICLE;

import java.util.List;

public class ARTICLEidinfo {
    ARTICLEmodel article;
    List<Object> objects;

    public ARTICLEmodel getArticle() {
        return article;
    }

    public void setArticle(ARTICLEmodel article) {
        this.article = article;
    }

    public List<Object> getObjects() {
        return objects;
    }

    public void setObjects(List<Object> objects) {
        this.objects = objects;
    }
}
