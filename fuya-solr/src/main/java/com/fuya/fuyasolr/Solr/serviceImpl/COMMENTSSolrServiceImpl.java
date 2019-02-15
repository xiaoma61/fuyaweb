package com.fuya.fuyasolr.Solr.serviceImpl;

import com.fuya.fuyadao.entity.COMMENTS;
import com.fuya.fuyasolr.Solr.dao.COMMENTSSearchdao;
import com.fuya.fuyasolr.Solr.service.COMMENTSSolrService;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class COMMENTSSolrServiceImpl implements COMMENTSSolrService {
    @Autowired
    COMMENTSSearchdao commentsSearchdao;
    @Override
    public void addCOMMENTS(int id) throws IOException, SolrServerException {
        commentsSearchdao.addCOMMENTS(id);

    }

    @Override
    public List<COMMENTS> searchbyuserid(int id) throws IOException, SolrServerException {
        SolrQuery solrQuery=new SolrQuery();
        solrQuery.set("USERID",id);
        List<COMMENTS>commentsList=commentsSearchdao.search(solrQuery);

        return commentsList;
    }

    @Override
    public List<COMMENTS> searchbyorderid(int id) throws IOException, SolrServerException {
        SolrQuery solrQuery=new SolrQuery();
        solrQuery.set("ORDERID",id);
        List<COMMENTS>commentsList=commentsSearchdao.search(solrQuery);

        return commentsList;

    }
}
