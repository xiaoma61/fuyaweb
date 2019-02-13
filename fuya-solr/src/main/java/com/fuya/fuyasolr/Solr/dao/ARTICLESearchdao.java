package com.fuya.fuyasolr.Solr.dao;

import com.fuya.fuyadao.entity.ARTICLE;
import com.fuya.fuyaservice.ARTICLEService;
import com.fuya.fuyasolr.SearchResult.SearchResult;
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
import java.util.List;
@Repository
public class ARTICLESearchdao {
    @Autowired
    ARTICLEService articleService;
    @Autowired
    SolrClient solrClient;
    public  void  addARTICLE(int id) throws IOException, SolrServerException {
        ARTICLE article= articleService.findByID(id);
        SolrInputDocument solrInputDocument=new SolrInputDocument();
        solrInputDocument.addField("articleCONTENT",article.getCONTENT());
        solrInputDocument.addField("articleTITLE",article.getTITLE());
        solrInputDocument.addField("articleNUMS",article.getNUMS());
        solrInputDocument.addField("articleTIME",article.getTIME());
        solrInputDocument.addField("articleTYPE",article.getTYPE());
        solrInputDocument.addField("articleID",article.getID());

        solrClient.add(solrInputDocument);
        solrClient.commit();
    }
    //查找
    public SearchResult Search(SolrQuery query) throws IOException, SolrServerException {
        List<ARTICLE> articleList=new ArrayList<>();
        QueryResponse solrResponse=solrClient.query(query);
        SolrDocumentList solrDocumentList= solrResponse.getResults();
        for (SolrDocument solrDocument :solrDocumentList){
            ARTICLE article=new ARTICLE();
            article.setCONTENT((String) solrDocument.getFieldValue("articleCONTENT"));
            article.setID((Integer) solrDocument.getFieldValue("articleID"));
            article.setNUMS((Integer) solrDocument.getFieldValue("articleNUMS"));
            article.setTIME((Date) solrDocument.getFieldValue("articleTIME"));
            article.setTITLE((String) solrDocument.getFieldValue("articleTITLE"));
            article.setTYPE((Integer) solrDocument.getFieldValue("articleTYPE"));
            articleList.add(article);
        }
        SearchResult searchResult=new SearchResult();
        searchResult.setObjects(articleList);
        searchResult.setResultCount(articleList.size());
        return searchResult;

    }

    public String Searchid(SolrQuery query) throws IOException, SolrServerException {

        String id = null;
        QueryResponse solrResponse=solrClient.query(query);
        SolrDocumentList solrDocumentList= solrResponse.getResults();
        for (SolrDocument solrDocument :solrDocumentList){
          id= (String) solrDocument.get("id");

        }
        return id;

    }
    public void delete(String id) throws IOException, SolrServerException {
        solrClient.deleteById(id);
        solrClient.commit();

    }


}
