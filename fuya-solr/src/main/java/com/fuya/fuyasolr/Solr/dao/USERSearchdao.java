package com.fuya.fuyasolr.Solr.dao;

import com.fuya.fuyadao.entity.USERS;
import com.fuya.fuyaservice.USERService;
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
public class USERSearchdao {
    @Autowired
    private SolrClient solrClient;
    @Autowired
    private USERService service;
    public void addUSER(int id) throws IOException, SolrServerException {

        USERS users=service.findByID(id);
        SolrInputDocument solrDocument=new SolrInputDocument();
        solrDocument.addField("usersID",users.getID());
//        solrDocument.addField("usersIMAGE",users.getIMAGE());
        solrDocument.addField("usersNAME",users.getNAME());
//        solrDocument.addField("usersPHONE",users.getPHONE());
//        solrDocument.addField("usersPASSWORD",users.getPASSWORD());
        solrDocument.addField("usersTYPE",users.getTYPE());
         solrClient.add(solrDocument);
         solrClient.commit();
    }

    public List<USERS> searchUSER(SolrQuery solrQuery) throws IOException, SolrServerException {
        List<USERS>usersList=new ArrayList<>();
        QueryResponse queryResponse=solrClient.query(solrQuery);
        SolrDocumentList solrDocumentList=queryResponse.getResults();
        for (SolrDocument Document : solrDocumentList ){
            USERS users=new USERS();
            if (!Document.get("usersTYPE").equals("4")){
                users.setNAME((String) Document.get("usersNAME"));
            }
            usersList.add(users);

        }


        return usersList;
    }

}
