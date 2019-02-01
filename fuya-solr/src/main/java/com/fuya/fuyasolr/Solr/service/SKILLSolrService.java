package com.fuya.fuyasolr.Solr.service;


import com.fuya.fuyadao.entity.SKILL;
import org.apache.solr.client.solrj.SolrServerException;

import java.io.IOException;
import java.util.List;

public interface SKILLSolrService {
    void addSKILL(int id) throws IOException, SolrServerException;
    //通过userid 和type 查找
    public List<SKILL>searchbyuseridandtype(int userid,int type) throws IOException, SolrServerException;
}
