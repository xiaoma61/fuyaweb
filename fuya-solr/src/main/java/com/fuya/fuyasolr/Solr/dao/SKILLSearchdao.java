package com.fuya.fuyasolr.Solr.dao;

import com.fuya.fuyadao.entity.SKILL;
import com.fuya.fuyadao.entity.USERS;
import com.fuya.fuyaservice.SKILLService;
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
public class SKILLSearchdao {
    @Autowired
    SKILLService skillService;
    @Autowired
    SolrClient solrClient;
    public void addSKILL(int id) throws IOException, SolrServerException {
        SKILL skill=skillService.findByID(id);
        SolrInputDocument solrInputDocument=new SolrInputDocument();
        solrInputDocument.addField("skillID",skill.getID());
        solrInputDocument.addField("skillSKILL",skill.getSKILL());
        solrInputDocument.addField("skillTYPE",skill.getTYPE());
        solrInputDocument.addField("skillUSERID",skill.getUSERID());
        solrClient.add(solrInputDocument);
        solrClient.commit();

    }
    public List<SKILL> Search(SolrQuery solrQuery) throws IOException, SolrServerException {
        List<SKILL>skillList=new ArrayList<>();
        QueryResponse queryResponse=solrClient.query(solrQuery);
        SolrDocumentList solrDocumentList=queryResponse.getResults();
        for (SolrDocument Document : solrDocumentList ){
            SKILL skill=new SKILL();
            //skill.setID();
            skill.setSKILL((String) Document.getFieldValue("skillSKILL"));
           // skill.setTYPE(Document.get(""));
            skillList.add(skill);
        }
        return  skillList;
    }
}
