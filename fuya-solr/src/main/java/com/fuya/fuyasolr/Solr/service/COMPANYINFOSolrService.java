package com.fuya.fuyasolr.Solr.service;

import com.fuya.fuyadao.entity.COMMENTS;
import com.fuya.fuyadao.entity.COMPANYINFO;
import org.apache.solr.client.solrj.SolrServerException;

import java.io.IOException;
import java.util.List;

public interface COMPANYINFOSolrService {
    public void  addCOMPANYINFO(int id) throws IOException, SolrServerException;
    public COMPANYINFO findCOMPANYINFObyuserid(int id) throws IOException, SolrServerException;

    public void update(int id) throws IOException, SolrServerException;
    public void delete(String id) throws IOException, SolrServerException;
}
