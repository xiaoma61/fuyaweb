package com.fuya.fuyautil;


import com.fuya.fuyadao.entity.ARTICLE;
import com.fuya.fuyasolr.SearchResult.SearchResult;

import java.util.ArrayList;
import java.util.List;

public class SearchKeyword {
    public static List<String > searchkeyword(SearchResult searchResult){
        List<ARTICLE> articleList=searchResult.getObjects();
        List<String>stringList=new ArrayList<>();
        for (ARTICLE article:articleList){
            stringList.add(article.getTITLE());

        }
        return stringList;
    }
}
