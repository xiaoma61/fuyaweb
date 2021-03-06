package com.fuya.fuyasolr.Solr.dao;

import com.fuya.fuyadao.entity.COLLECTIONS;
import com.fuya.fuyaservice.COLLECTIONSService;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Repository
//@Repository注解可以标记在任何的类上，用来表明该类是用来执行与数据库相关的操作（即dao对象），并支持自动处理数据库操作产生的异常
public class COLLECTIONSSearchdao {
    @Autowired
    COLLECTIONSService service;
    @Autowired
    SolrClient solrClient;
    public  void  addCOLLECTIONS(int id) throws IOException, SolrServerException {
        COLLECTIONS collections=service.findByID(id);
//        SolrInputDocument solrInputDocument=new SolrInputDocument();
//        solrInputDocument.addField("collectionsFROMID",collections.getFROMID());
//        solrInputDocument.addField("collectionsID",collections.getID());
//        solrInputDocument.addField("collectionsTOID",collections.getTOID());
//        solrClient.add(solrInputDocument);
        solrClient.addBean(collections);
        solrClient.commit();
    }
    //查找收藏
    public List<String> Search(SolrQuery query) throws IOException, SolrServerException {
        List<String> collectionsList=new ArrayList<>();
        QueryResponse solrResponse=solrClient.query(query);
        SolrDocumentList solrDocumentList= solrResponse.getResults();
        for (SolrDocument solrDocument :solrDocumentList){
            String id= (String) solrDocument.get("id");
//                 COLLECTIONS collections=new COLLECTIONS();
//                 collections.setID(Integer.parseInt(id));
                 collectionsList.add(id);

        }
        return collectionsList;

    }
    public void delete(String id) throws IOException, SolrServerException {
        solrClient.deleteById(id);
        solrClient.commit();
    }


}
