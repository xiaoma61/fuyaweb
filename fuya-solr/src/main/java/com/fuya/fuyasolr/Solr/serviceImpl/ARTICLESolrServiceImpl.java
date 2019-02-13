package com.fuya.fuyasolr.Solr.serviceImpl;


import com.fuya.fuyadao.entity.ARTICLE;
import com.fuya.fuyasolr.SearchResult.SearchResult;
import com.fuya.fuyasolr.Solr.dao.ARTICLESearchdao;
import com.fuya.fuyasolr.Solr.service.ARTICLESolrService;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class ARTICLESolrServiceImpl implements ARTICLESolrService {
    @Autowired
    ARTICLESearchdao articleSearchdao;
    @Override
    public void addARTICLE(int id) throws IOException, SolrServerException {
        articleSearchdao.addARTICLE(id);
    }

    @Override
    public SearchResult Searchbytype(int type, int start, int rows) throws IOException, SolrServerException {
        SolrQuery solrQuery=new SolrQuery();
        String query="articleTYPE:"+type;
        solrQuery.setQuery(query);
        solrQuery.setStart(start);
        solrQuery.setRows(rows);
        solrQuery.setSort("articleNUMS", SolrQuery.ORDER.desc);
        SearchResult searchResult=articleSearchdao.Search(solrQuery);

        return searchResult;
    }

    @Override
    public SearchResult Searchbyid(int id) throws IOException, SolrServerException {
        SolrQuery solrQuery=new SolrQuery();
        String query="articleID:"+id;
        SearchResult searchResult=articleSearchdao.Search(solrQuery);


        return searchResult;
    }

    @Override
    public SearchResult SearchbyLikeName(String Name,int start,int rows) throws IOException, SolrServerException {
        SolrQuery solrQuery=new SolrQuery();
        solrQuery.set("q","articleTITLE:"+"*"+Name+"*");

        solrQuery.setStart(start);
        solrQuery.setRows(rows);
        SearchResult searchResult=articleSearchdao.Search(solrQuery);
        return searchResult;
    }

    @Override
    public void update(int articleid) throws IOException, SolrServerException {
        String id=null;
        SolrQuery solrQuery=new SolrQuery();
        solrQuery.set("q","articleTITLE:"+id);
        id=articleSearchdao.Searchid(solrQuery);
        //删除
        articleSearchdao.delete(id);
        articleSearchdao.addARTICLE(articleid);



    }
}
