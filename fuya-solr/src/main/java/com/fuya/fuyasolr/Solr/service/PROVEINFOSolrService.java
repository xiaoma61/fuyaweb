package com.fuya.fuyasolr.Solr.service;

import org.apache.solr.client.solrj.SolrServerException;

import java.io.IOException;

public interface PROVEINFOSolrService {
    void addPROVEINFO(int id) throws IOException, SolrServerException;
    public void delete(int id) throws IOException, SolrServerException;
}
