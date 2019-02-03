package com.fuya.fuyasolr.Solr.service;


import com.fuya.fuyadao.entity.COLLECTIONS;
import org.apache.solr.client.solrj.SolrServerException;

import java.io.IOException;

public interface COLLECTIONSSolrService {
    public  void  addCOLLECTIONS(int id) throws IOException, SolrServerException;
    public COLLECTIONS Searchbyfromidandtoid(int fromid, int toid) throws IOException, SolrServerException;
    void delete(int id) throws IOException, SolrServerException;
}
