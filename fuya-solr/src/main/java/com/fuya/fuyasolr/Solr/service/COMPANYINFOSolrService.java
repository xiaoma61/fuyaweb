package com.fuya.fuyasolr.Solr.service;

import org.apache.solr.client.solrj.SolrServerException;

import java.io.IOException;

public interface COMPANYINFOSolrService {
    public void  addCOMPANYINFO(int id) throws IOException, SolrServerException;
}
