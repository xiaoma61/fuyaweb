package com.fuya.fuyasolr.Solr.serviceImpl;


import com.fuya.fuyasolr.SearchResult.SearchResult;
import com.fuya.fuyasolr.Solr.dao.RECRUITSearchdao;
import com.fuya.fuyasolr.Solr.service.RECRUITSolrService;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class RECRUITSolrServiceImpl implements RECRUITSolrService {
    @Autowired
    RECRUITSearchdao recruitSearchdao;
    @Override
    public void addRECRUIT(int id) throws IOException, SolrServerException {
        recruitSearchdao.addRECRUIT(id);

    }

    @Override
    public SearchResult SearchRECRUITByUserid(int id,int start,int rows) throws IOException, SolrServerException {
        SolrQuery solrQuery=new SolrQuery();
        solrQuery.set("q","USERSID:"+id);
        solrQuery.setStart(start);
        solrQuery.setRows(rows);
        solrQuery.setSort("TIME", SolrQuery.ORDER.desc);

        SearchResult searchResult= recruitSearchdao.Search(solrQuery);
        return searchResult;
    }

    @Override
    public SearchResult SearchRECRUITByid(int id) throws IOException, SolrServerException {
        SolrQuery solrQuery=new SolrQuery();
        solrQuery.set("q","RECRUITID:"+id);
        SearchResult searchResult= recruitSearchdao.Search(solrQuery);
        return searchResult;
    }

    @Override
    public String SearchIDByRECRUITID(int id) throws IOException, SolrServerException {
        return null;
    }

    @Override
    public void UpdateByRECRUITID(int id) throws IOException, SolrServerException {

    }

    @Override
    public void DeleteByRECRUITID(int id) throws IOException, SolrServerException {

    }
}
