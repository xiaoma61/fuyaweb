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
        solrDocument.addField("usersID",users.getUSERSID());
//        solrDocument.addField("usersIMAGE",usersController.getIMAGE());
        solrDocument.addField("usersNAME",users.getNAME());
//        solrDocument.addField("usersPHONE",usersController.getPHONE());
//        solrDocument.addField("usersPASSWORD",usersController.getPASSWORD());
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

                users.setNAME((String) Document.get("usersNAME"));
                String type= (String) Document.get("usersTYPE");
                users.setTYPE(Integer.parseInt(type));


               // usersController.setNAME((String) Document.getFieldValue("usersNAME"));
//            }
            usersList.add(users);

        }

        return usersList;
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
