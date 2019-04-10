package com.fuya.fuyadao.model;

import com.fuya.fuyadao.entity.COMMENTS;
import com.fuya.fuyadao.entity.YUESOBASICINFO;

public class ProveAndYuesaobasic {

    private COMMENTS comments;
    private YUESOBASICINFO yuesobasicinfo;




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
    public ProveAndYuesaobasic(COMMENTS comments, YUESOBASICINFO yuesobasicinfo){
        this.yuesobasicinfo = yuesobasicinfo;
        this.comments = comments;

    }
}
