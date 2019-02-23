package com.fuya.fuyasolr.Solr.service;

import com.fuya.fuyadao.entity.USERS;
import org.apache.solr.client.solrj.SolrServerException;

import java.io.IOException;
import java.util.List;

public interface USERSSolrservice {
    //查找关键词
    public List<USERS> search(String keyword);


    List<USERS> searchbyusername(String name) throws IOException, SolrServerException;

    public  void addUSER(int id) throws IOException, SolrServerException;
    String searchbyid(int id) throws IOException, SolrServerException;
    void delete(int id) throws IOException, SolrServerException;

}
