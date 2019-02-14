package com.fuya.fuyasolr.Solr.service;


import com.fuya.fuyasolr.SearchResult.SearchResult;
import org.apache.solr.client.solrj.SolrServerException;

import java.io.IOException;
import java.util.Date;


public interface ARTICLESolrService {
    public  void  addARTICLE(int id) throws IOException, SolrServerException;
    public  SearchResult Searchbytype(int type, int start, int rows) throws IOException, SolrServerException;
    public  SearchResult Searchbyid(int id) throws IOException, SolrServerException;
    public  SearchResult SearchbyLikeName(String Name,int start,int rows) throws IOException, SolrServerException;
    public void update(int articleid) throws IOException, SolrServerException;
    public void delete(int articleid) throws IOException, SolrServerException;
    public SearchResult SearchbyTime (Date starttime,Date endtime,int start,int rows) throws IOException, SolrServerException;
}
