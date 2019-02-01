package com.fuya.fuyasolr.Solr.dao;

import com.fuya.fuyadao.entity.ORDERS;
import com.fuya.fuyaservice.ORDERSService;
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
import java.util.ArrayList;
import java.util.List;

@Repository
public class ORDERSSearchdao {
    @Autowired
    ORDERSService ordersService;
    @Autowired
    SolrClient solrClient;

    //添加订单
    public  void addORDERS(int id) throws IOException, SolrServerException {
       ORDERS orders=ordersService.findByID(id);
        SolrInputDocument solrInputDocument=new SolrInputDocument();
        solrInputDocument.addField("ordersCONTRACTNUMBER",orders.getCONTRACTNUMBER());
        solrInputDocument.addField("ordersTOID",orders.getTOID());
        solrInputDocument.addField("ordersFROMID",orders.getFROMID());
        solrInputDocument.addField("ordersID",orders.getID());
        solrClient.add(solrInputDocument);
        solrClient.commit();
    }
    //查找订单---根据月嫂查询
    public List<ORDERS>Search(SolrQuery query) throws IOException, SolrServerException {
        List<ORDERS>ordersList=new ArrayList<>();
        QueryResponse queryResponse=solrClient.query(query);
        SolrDocumentList solrDocumentList=queryResponse.getResults();
        for (SolrDocument Document : solrDocumentList ){
            ORDERS orders=new ORDERS();
            orders.setID((Integer) Document.get("ordersID"));
            orders.setCONTRACTNUMBER((String) Document.get("ordersCONTRACTNUMBER"));
            orders.setFROMID((Integer) Document.get("ordersFROMID"));
            orders.setTOID((Integer) Document.get("ordersTOID"));
            ordersList.add(orders);
        }
        return  ordersList;


    }

}
