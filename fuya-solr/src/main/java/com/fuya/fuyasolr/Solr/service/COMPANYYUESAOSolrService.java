package com.fuya.fuyasolr.Solr.service;

import com.fuya.fuyadao.entity.COMPANYYUESAO;
import org.apache.solr.client.solrj.SolrServerException;

import java.io.IOException;

public interface COMPANYYUESAOSolrService {
    void addCOMPANYYUESAO(int id) throws IOException, SolrServerException;
    public void delete(int id) throws IOException, SolrServerException;
}
