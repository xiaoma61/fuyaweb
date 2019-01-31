package com.fuya.fuyasolr.Solr.serviceImpl;

import com.fuya.fuyadao.entity.COMMENTS;
import com.fuya.fuyasolr.Solr.dao.COMMENTSSearchdao;
import com.fuya.fuyasolr.Solr.service.COMMENTSSearchdaoSolrService;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.List;

public class COMMENTSSearchdaoSolrServiceImpl implements COMMENTSSearchdaoSolrService {
    @Autowired
    COMMENTSSearchdao commentsSearchdao;
    @Override
    public void addCOMMENTS(int id) throws IOException, SolrServerException {
        commentsSearchdao.addCOMMENTS(id);

    }

    @Override
    public List<COMMENTS> searchbyuserid(int id) throws IOException, SolrServerException {
        SolrQuery solrQuery=new SolrQuery();
        solrQuery.set("commentsUSERID:",id);
        List<COMMENTS>commentsList=commentsSearchdao.searchbyuserid(solrQuery);

        return commentsList;
    }
}
