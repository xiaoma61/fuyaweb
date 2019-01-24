package com.fuya.fuyasolr.SearchResult;

import java.util.List;

public class SearchResult<T> {
    //实现分页

    List<?>objects;
    Integer resultCount;//总记录数
    Integer totalPage;//总页数

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public List<?> getObjects() {
        return objects;
    }

    public void setObjects(List<?> objects) {
        this.objects = objects;
    }

    public Integer getResultCount() {
        return resultCount;
    }

    public void setResultCount(Integer resultCount) {
        this.resultCount = resultCount;
    }
}
