package com.fuya.fuyasolr.Solr.service;

import com.fuya.fuyasolr.SearchResult.SearchResult;
import org.apache.solr.client.solrj.SolrServerException;

import java.io.IOException;

public interface RECRUITSolrService {
    public  void  addRECRUIT(int id) throws IOException, SolrServerException;

    public SearchResult SearchRECRUITByUserid(int id,int start,int rows) throws IOException, SolrServerException;
    public SearchResult SearchRECRUITByid(int id) throws IOException, SolrServerException;
    public String SearchIDByRECRUITID(int id) throws IOException, SolrServerException;
    public void UpdateByRECRUITID(int id) throws IOException, SolrServerException;
    public void DeleteByRECRUITID(int id) throws IOException, SolrServerException;
}
