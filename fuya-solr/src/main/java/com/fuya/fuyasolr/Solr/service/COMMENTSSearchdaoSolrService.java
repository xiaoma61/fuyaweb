package com.fuya.fuyasolr.Solr.service;

import com.fuya.fuyadao.entity.COMMENTS;
import com.fuya.fuyasolr.SearchResult.SearchResult;
import org.apache.solr.client.solrj.SolrServerException;

import java.io.IOException;
import java.util.List;

public interface COMMENTSSearchdaoSolrService {
    public void addCOMMENTS(int id) throws IOException, SolrServerException;
    public List<COMMENTS> searchbyuserid(int id) throws IOException, SolrServerException;
}
