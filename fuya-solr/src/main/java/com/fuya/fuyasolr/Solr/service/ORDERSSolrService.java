package com.fuya.fuyasolr.Solr.service;


import com.fuya.fuyadao.entity.ORDERS;
import com.fuya.fuyasolr.SearchResult.SearchResult;
import org.apache.solr.client.solrj.SolrServerException;

import java.io.IOException;
import java.util.List;


public interface ORDERSSolrService {
    public  void addORDERS(int id) throws IOException, SolrServerException;
    //查找通过toid查找订单
    public List<ORDERS> Searchbytoid(int toid,int start ,int rows) throws IOException, SolrServerException;
}
