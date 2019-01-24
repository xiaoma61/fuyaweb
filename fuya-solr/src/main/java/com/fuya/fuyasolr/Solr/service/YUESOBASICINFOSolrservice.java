package com.fuya.fuyasolr.Solr.service;


import com.fuya.fuyasolr.SearchResult.SearchResult;
import org.apache.solr.client.solrj.SolrServerException;

import java.io.IOException;

public interface YUESOBASICINFOSolrservice {

    //查找关键词，员工姓名
    public String search(String keyword);
    //搜索查找，同时分页
    public SearchResult search(String keyword,String workarea,String type,String minwages,String maxwages,String nativeplace,String age,int page,int rows) throws IOException, SolrServerException;


}
