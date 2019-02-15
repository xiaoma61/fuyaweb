package com.fuya.fuyautil;


import com.fuya.fuyadao.entity.ARTICLE;
import com.fuya.fuyadao.model.ARTICLEmodel;
import com.fuya.fuyasolr.SearchResult.SearchResult;

import java.util.ArrayList;
import java.util.List;

public class SearchKeyword {
    public static List<String > searchkeyword(SearchResult searchResult){
        List<ARTICLEmodel> articleList=searchResult.getObjects();
        List<String>stringList=new ArrayList<>();
        for (ARTICLEmodel article:articleList){
            stringList.add(article.getTITLE());

        }
        return stringList;
    }
}
