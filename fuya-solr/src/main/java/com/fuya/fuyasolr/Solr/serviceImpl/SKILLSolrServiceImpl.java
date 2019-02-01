package com.fuya.fuyasolr.Solr.serviceImpl;

import com.fuya.fuyadao.entity.SKILL;
import com.fuya.fuyasolr.Solr.dao.SKILLSearchdao;
import com.fuya.fuyasolr.Solr.service.SKILLSolrService;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class SKILLSolrServiceImpl implements SKILLSolrService {
    @Autowired
    SKILLSearchdao skillSearchdao;

    public void addSKILL(int id) throws IOException, SolrServerException {

        skillSearchdao.addSKILL(id);
    }

    @Override
    public List<SKILL> searchbyuseridandtype(int userid, int type) throws IOException, SolrServerException {

        SolrQuery solrQuery=new SolrQuery();
        solrQuery.set("skillUSERID: "+userid);
        solrQuery.set("skillTYPE:"+type);
        List<SKILL>skillList=skillSearchdao.Search(solrQuery);
        return skillList;
    }



}
