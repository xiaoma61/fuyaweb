package com.fuya.fuyasolr.Solr.service;

import org.apache.solr.client.solrj.SolrServerException;

import java.io.IOException;

public interface EMPLOYERINFORMATIONSolrService {
    public  void addEMPLOYERINFORMATION(int id) throws IOException, SolrServerException;
}
