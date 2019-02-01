package com.fuya.fuyasolr.Solr.service;

import com.fuya.fuyadao.entity.EMPLOYERINFORMATION;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;

import java.io.IOException;

public interface EMPLOYERINFORMATIONSolrService {
    public  void addEMPLOYERINFORMATION(int id) throws IOException, SolrServerException;
    public EMPLOYERINFORMATION searchbyorderid(int orderid) throws IOException, SolrServerException;
}
