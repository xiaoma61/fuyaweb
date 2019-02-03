package com.fuya.fuyasolr.Solr.dao;

import com.fuya.fuyadao.entity.COLLECTIONS;
import com.fuya.fuyaservice.COLLECTIONSService;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;

@Repository
//@Repository注解可以标记在任何的类上，用来表明该类是用来执行与数据库相关的操作（即dao对象），并支持自动处理数据库操作产生的异常
public class COLLECTIONSSearchdao {
    @Autowired
    COLLECTIONSService service;
    @Autowired
    SolrClient solrClient;
    public  void  addCOLLECTIONS(int id) throws IOException, SolrServerException {
        COLLECTIONS collections=service.findByID(id);
        SolrInputDocument solrInputDocument=new SolrInputDocument();
        solrInputDocument.addField("collectionsFROMID",collections.getFROMID());
        solrInputDocument.addField("collectionsID",collections.getID());
        solrInputDocument.addField("collectionsTOID",collections.getTOID());
        solrClient.add(solrInputDocument);
        solrClient.commit();
    }
    //查找收藏
    public COLLECTIONS Search(SolrQuery query) throws IOException, SolrServerException {
        COLLECTIONS collections=new COLLECTIONS();
        QueryResponse solrResponse=solrClient.query(query);
        SolrDocumentList solrDocumentList= solrResponse.getResults();
        for (SolrDocument solrDocument :solrDocumentList){
            collections.setID((Integer) solrDocument.get("collectionsID"));
            return collections;
        }
        return null;

    }
    public void delete(String solrQuery) throws IOException, SolrServerException {
        solrClient.deleteByQuery(solrQuery);
    }


}
