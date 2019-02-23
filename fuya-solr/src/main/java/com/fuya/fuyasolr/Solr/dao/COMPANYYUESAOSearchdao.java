package com.fuya.fuyasolr.Solr.dao;

import com.fuya.fuyadao.entity.COMPANYYUESAO;
import com.fuya.fuyaservice.COMPANYYUESAOService;
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
public class COMPANYYUESAOSearchdao {
    @Autowired
    SolrClient solrClient;
    @Autowired
    COMPANYYUESAOService companyyuesaoService;
    //添加
    public  void  addCOMPANYYUESAO(int id) throws IOException, SolrServerException {
        COMPANYYUESAO companyyuesao=companyyuesaoService.findByCOMPANYID(id);

        if (companyyuesao!=null){
            solrClient.addBean(companyyuesao);
            solrClient.commit();
        }

    }
    public List<String> findid(SolrQuery solrQuery) throws IOException, SolrServerException {

        QueryResponse queryResponse=solrClient.query(solrQuery);
        SolrDocumentList solrDocumentList=queryResponse.getResults();
        List<String> id=new ArrayList<>();
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
