package com.fuya.fuyasolr.Solr.service;

import com.fuya.fuyasolr.SearchResult.SearchResult;
import org.apache.solr.client.solrj.SolrServerException;

import java.io.IOException;


public interface COMPANYBASICINFOSolrService {
    //查找信息 公司
    public SearchResult search(int id) throws IOException, SolrServerException;
    //分页查找
    //如何将所有的信息结合起来
    public SearchResult findall(String keyword , int start, int rows) throws IOException, SolrServerException;
    //添加id
    public void  addCOMPANYBASICINFO(int id) throws IOException, SolrServerException;


}
