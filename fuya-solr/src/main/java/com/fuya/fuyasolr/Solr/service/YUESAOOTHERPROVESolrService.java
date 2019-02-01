package com.fuya.fuyasolr.Solr.service;

import com.fuya.fuyadao.entity.YUESAOOTHERPROVE;
import com.fuya.fuyasolr.SearchResult.SearchResult;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;

import java.io.IOException;
import java.util.List;

public interface YUESAOOTHERPROVESolrService {
    public void addYUESAOOTHERPROVE(int id) throws IOException, SolrServerException;
//    public List<YUESAOOTHERPROVE> searchbyuserid(int userid);

    SearchResult searchbyuserid(int userid, int start, int rows) throws IOException, SolrServerException;
}
