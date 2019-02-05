package com.fuya.fuyasolr.Solr.service;


import com.fuya.fuyadao.entity.COLLECTIONS;
import org.apache.solr.client.solrj.SolrServerException;

import java.io.IOException;
import java.util.List;

public interface COLLECTIONSSolrService {
    public  void  addCOLLECTIONS(int id) throws IOException, SolrServerException;
    public List<String> Searchbyfromidandtoid(int fromid, int toid) throws IOException, SolrServerException;
    void delete(String id) throws IOException, SolrServerException;

    void delete(int fromid, int toid) throws IOException, SolrServerException;
}
