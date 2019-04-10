package com.fuya.fuyadao.model;


import com.fuya.fuyasolr.SearchResult.SearchResult;

import java.util.List;

public class ARTICLEinfo {
    SearchResult searchResult;
    List<Object>objects;

    public SearchResult getSearchResult() {
        return searchResult;
    }

    public void setSearchResult(SearchResult searchResult) {
        this.searchResult = searchResult;
    }

    public List<Object> getObjects() {
        return objects;
    }

    public void setObjects(List<Object> objects) {
        this.objects = objects;
    }
}
