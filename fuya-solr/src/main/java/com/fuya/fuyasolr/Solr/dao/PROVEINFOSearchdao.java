package com.fuya.fuyasolr.Solr.dao;

import com.fuya.fuyadao.entity.PROVEINFO;
import com.fuya.fuyaservice.PROVEINFOService;
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
public class PROVEINFOSearchdao {
    @Autowired
    PROVEINFOService proveinfoService;
    @Autowired
    SolrClient solrClient;
    public void addPROVEINFO(int id) throws IOException, SolrServerException {
        PROVEINFO proveinfo=proveinfoService.findByID(id);



        if (proveinfo!=null){
            solrClient.addBean(proveinfo);
            solrClient.commit();
        }


    }
    public List<PROVEINFO>search(SolrQuery solrQuery) throws IOException, SolrServerException {
        List<PROVEINFO>proveinfoList=new ArrayList<>();
        QueryResponse queryResponse=solrClient.query(solrQuery);
        SolrDocumentList solrDocumentList=queryResponse.getResults();
        for (SolrDocument Document : solrDocumentList ){

        }
    return  proveinfoList;
    }
    public List<String> findid(SolrQuery solrQuery) throws IOException, SolrServerException {

        QueryResponse queryResponse=solrClient.query(solrQuery);
        SolrDocumentList solrDocumentList=queryResponse.getResults();
        List<String> id=new ArrayList();
        for (SolrDocument Document : solrDocumentList ){

            id.add((String) Document.get("id"));

        }
        return id;

    }
    public void delete(String id) throws IOException, SolrServerException {
        solrClient.deleteById(id);
        solrClient.commit();

    }
}
