package com.fuya.fuyasolr.Solr.dao;

import com.fuya.fuyadao.entity.COMMENTS;
import com.fuya.fuyadao.entity.EMPLOYERINFORMATION;
import com.fuya.fuyaservice.EMPLOYERINFORMATIONService;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrResponse;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;

@Repository
public class EMPLOYERINFORMATIONSearchdao {
    @Autowired
    EMPLOYERINFORMATIONService employerinformationService;
    @Autowired
    SolrClient solrClient;
    public  void addEMPLOYERINFORMATION(int id) throws IOException, SolrServerException {
        EMPLOYERINFORMATION employerinformation =employerinformationService.findByID(id);
        SolrInputDocument solrDocument=new SolrInputDocument();
        solrDocument.addField("employerinformationADDRESS",employerinformation.getADDRESS());
        solrDocument.addField("employerinformationAREA",employerinformation.getAREA());
        solrDocument.addField("employerinformationID",employerinformation.getID());
        solrDocument.addField("employerinformationIDCARD",employerinformation.getIDCARD());
        solrDocument.addField("employerinformationNAME",employerinformation.getNAME());
        solrDocument.addField("employerinformationODERID",employerinformation.getODERID());
        solrDocument.addField("employerinformationPHONE",employerinformation.getPHONE());
        solrDocument.addField("employerinformationTYPE",employerinformation.getTYPE());
        solrClient.add(solrDocument);
        solrClient.commit();
    }
    //查找orderid
    public EMPLOYERINFORMATION searchbyorderid(SolrQuery solrQuery) throws IOException, SolrServerException {
        QueryResponse solrResponse=solrClient.query(solrQuery);
        EMPLOYERINFORMATION employerinformation=new EMPLOYERINFORMATION();
        SolrDocumentList solrDocumentList= solrResponse.getResults();
        for (SolrDocument solrDocument:solrDocumentList){
            employerinformation.setADDRESS((String) solrDocument.get("employerinformationADDRESS"));
            employerinformation.setAREA((String) solrDocument.get("employerinformationAREA"));
            employerinformation.setNAME((String) solrDocument.get("employerinformationNAME"));
            employerinformation.setPHONE((String) solrDocument.get("employerinformationPHONE"));
            employerinformation.setTYPE((Integer) solrDocument.get("employerinformationTYPE"));


        }
        return  employerinformation;

    }

}
