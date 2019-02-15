package com.fuya.fuyasolr.Solr.dao;

import com.fuya.fuyadao.entity.YUESAOOTHERPROVE;
import com.fuya.fuyaservice.YUESAOOTHERPROVEService;
import com.fuya.fuyasolr.SearchResult.SearchResult;
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
public class YUESAOOTHERPROVESearchdao {
    @Autowired
    SolrClient solrClient;
    @Autowired
    YUESAOOTHERPROVEService yuesaootherproveService;
    public void addYUESAOOTHERPROVE(int id) throws IOException, SolrServerException {
        YUESAOOTHERPROVE yuesaootherprove=yuesaootherproveService.findByID(id);
//        SolrInputDocument solrInputDocument=new SolrInputDocument();
//        solrInputDocument.addField("yuesaootherproveFILEAREA",yuesaootherprove.getFILEAREA());
//        solrInputDocument.addField("yuesaootherproveID",yuesaootherprove.getID());
//        solrInputDocument.addField("yuesaootherproveTITLE",yuesaootherprove.getTITLE());
//        solrInputDocument.addField("yuesaootherproveUSERID",yuesaootherprove.getUSERID());
//
//
//        solrClient.add(solrInputDocument);
        solrClient.addBean(yuesaootherprove);
        solrClient.commit();


    }
    public SearchResult search(SolrQuery solrQuery) throws IOException, SolrServerException {
        List<YUESAOOTHERPROVE>yuesaootherproves=new ArrayList<>();
        QueryResponse solrResponse=solrClient.query(solrQuery);
        SolrDocumentList solrDocumentList= solrResponse.getResults();
        SearchResult searchResult=new SearchResult();
        searchResult.setResultCount((int) solrDocumentList.getNumFound());
        for (SolrDocument solrocument:solrDocumentList){
            YUESAOOTHERPROVE yuesaootherprove=new YUESAOOTHERPROVE();
            yuesaootherprove.setFILEAREA((String) solrocument.getFieldValue("FILEAREA"));
            yuesaootherprove.setYUESAOOTHERPROVEID((Integer) solrocument.getFieldValue("ID"));
            yuesaootherprove.setTITLE((String) solrocument.getFieldValue("TITLE"));
            yuesaootherprove.setUSERID((Integer) solrocument.getFieldValue("USERID"));
            yuesaootherproves.add(yuesaootherprove);
        }
        searchResult.setObjects(yuesaootherproves);


        return searchResult;

    }


}
