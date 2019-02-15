package com.fuya.fuyasolr.Solr.dao;

import com.fuya.fuyadao.entity.ORDERS;
import com.fuya.fuyadao.entity.PROVEINFO;
import com.fuya.fuyaservice.PROVEINFOService;
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
public class PROVEINFOSearchdao {
    @Autowired
    PROVEINFOService proveinfoService;
    @Autowired
    SolrClient solrClient;
    public void addPROVEINFO(int id) throws IOException, SolrServerException {
        PROVEINFO proveinfo=proveinfoService.findByID(id);
//        SolrInputDocument solrInputDocument=new SolrInputDocument();
//        solrInputDocument.addField("proveinfoHEALTHCERTIFICATES",proveinfo.getHEALTHCERTIFICATES());
        solrClient.addBean(proveinfo);

    }
    public List<PROVEINFO>search(SolrQuery solrQuery) throws IOException, SolrServerException {
        List<PROVEINFO>proveinfoList=new ArrayList<>();
        QueryResponse queryResponse=solrClient.query(solrQuery);
        SolrDocumentList solrDocumentList=queryResponse.getResults();
        for (SolrDocument Document : solrDocumentList ){

        }
    return  proveinfoList;
    }

}
