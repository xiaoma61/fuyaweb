package com.fuya.fuyasolr.Solr.dao;

import com.fuya.fuyadao.entity.MSG;
import com.fuya.fuyaservice.MSGService;
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
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Repository
public class MSGSearchdao {
    @Autowired
    SolrClient solrClient;
    @Autowired
    MSGService msgService;
    public void  addMSG(int id) throws IOException, SolrServerException {
        MSG msg=msgService.findByID(id);
        SolrInputDocument solrInputDocument=new SolrInputDocument();
        solrInputDocument.addField("msgID",msg.getID());
        solrInputDocument.addField("msgFROMID",msg.getFROMID());
        solrInputDocument.addField("msgMSG",msg.getMSG());
        solrInputDocument.addField("msgTIME",msg.getTIME());
        solrInputDocument.addField("msgTOID",msg.getTOID());
        solrInputDocument.addField("msgtype",1);//未读状态
        solrClient.add(solrInputDocument);
        solrClient.commit();

    }
    public HashSet<String> Search(SolrQuery solrQuery,int type) throws IOException, SolrServerException {
        //获取多少
       // List<MSG>msgList=new ArrayList<>();
        HashSet<String>hashSet=new HashSet<>();
        QueryResponse queryResponse=solrClient.query(solrQuery);
        SolrDocumentList solrDocumentList=queryResponse.getResults();
        for (SolrDocument Document : solrDocumentList ){
            MSG msg=new MSG();
           if (Document.getFieldValue("msgtype").equals(type)){
                hashSet.add((String) Document.getFieldValue("msgFROMID"));
                //未读状态的时候
//                msg.setTIME((Date) Document.getFieldValue("msgTIME"));
//                msg.setTOID((Integer) Document.getFieldValue("msgTOID"));
//                msg.setFROMID((Integer) Document.getFieldValue("msgFROMID"));
//                msg.setMSG((String) Document.getFieldValue("msgMSG"));
//                msg.setID((Integer) Document.getFieldValue("msgID"));
//                msgList.add(msg);
           }

        }
        return hashSet;

    }
    public List<MSG> Searchby(SolrQuery solrQuery,int type) throws IOException, SolrServerException {
        //获取多少
        List<MSG>msgList=new ArrayList<>();
//        HashSet<String>hashSet=new HashSet<>();
        QueryResponse queryResponse=solrClient.query(solrQuery);
        SolrDocumentList solrDocumentList=queryResponse.getResults();
        for (SolrDocument Document : solrDocumentList ){
            MSG msg=new MSG();
            if (Document.getFieldValue("msgtype").equals(type)){
//                hashSet.add((String) Document.getFieldValue("msgFROMID"));
                //未读状态的时候
                msg.setTIME((Date) Document.getFieldValue("msgTIME"));
                msg.setTOID((Integer) Document.getFieldValue("msgTOID"));
                msg.setFROMID((Integer) Document.getFieldValue("msgFROMID"));
                msg.setMSG((String) Document.getFieldValue("msgMSG"));
                msg.setID((Integer) Document.getFieldValue("msgID"));
                msgList.add(msg);
            }

        }
        return msgList;

    }
    public String  Searchid(SolrQuery solrQuery) throws IOException, SolrServerException {
        //获取多少

        String id=null;
        QueryResponse queryResponse=solrClient.query(solrQuery);
        SolrDocumentList solrDocumentList=queryResponse.getResults();
        for (SolrDocument Document : solrDocumentList ){
            id= (String) Document.get("id");

        }
        return id;


    }
    public void update(int type ,String id) throws IOException, SolrServerException {
        SolrInputDocument solrInputDocument=new SolrInputDocument();
        solrInputDocument.addField("id",id);
        solrInputDocument.addField("msgtype",type);
        solrClient.add(solrInputDocument);
        solrClient.commit();
    }
}
