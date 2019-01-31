package com.fuya.fuyasolr.Solr.service;


import com.fuya.fuyadao.entity.YUESOBASICINFO;
import com.fuya.fuyasolr.SearchResult.SearchResult;
import org.apache.solr.client.solrj.SolrServerException;

import java.io.IOException;

public interface YUESOBASICINFOSolrservice {

    //查找关键词，员工姓名
    public SearchResult search(String keyword) throws IOException, SolrServerException;
    //搜索查找，同时分页
    public SearchResult search(String keyword,String workarea,String type,String minwages,String maxwages,String nativeplace,String age,int page,int rows) throws IOException, SolrServerException;
    public void addYUESOBASICINFO(int id) throws IOException, SolrServerException;

    //通过id查找
    public YUESOBASICINFO searchbyid(int id) throws IOException, SolrServerException;

}
