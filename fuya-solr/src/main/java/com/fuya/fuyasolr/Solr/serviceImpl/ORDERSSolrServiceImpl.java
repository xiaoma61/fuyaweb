package com.fuya.fuyasolr.Solr.serviceImpl;

import com.fuya.fuyadao.entity.ORDERS;
import com.fuya.fuyaservice.ORDERSService;
import com.fuya.fuyasolr.Solr.dao.ORDERSSearchdao;
import com.fuya.fuyasolr.Solr.service.ORDERSSolrService;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class ORDERSSolrServiceImpl implements ORDERSSolrService {

    @Autowired
    ORDERSSearchdao ordersSearchdao;
    @Autowired
    SolrClient solrClient;

    @Override
    public void addORDERS(int id) throws IOException, SolrServerException {
        ordersSearchdao.addORDERS(id);

    }

    @Override
    public List<ORDERS> Searchbytoid(int toid,int start,int rows) throws IOException, SolrServerException {
        SolrQuery solrQuery=new SolrQuery();
        solrQuery.set("TOID"+toid);
        solrQuery.setStart(start);
        solrQuery.setRows(rows);
        List<ORDERS>ordersList=ordersSearchdao.Search(solrQuery);

        return ordersList;
    }
}