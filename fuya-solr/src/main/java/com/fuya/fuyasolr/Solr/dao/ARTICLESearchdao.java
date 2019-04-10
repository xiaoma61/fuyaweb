package com.fuya.fuyasolr.Solr.dao;

import com.fuya.fuyadao.entity.ARTICLE;
import com.fuya.fuyadao.model.ARTICLEmodel;
import com.fuya.fuyaservice.ARTICLEService;
import com.fuya.fuyasolr.SearchResult.SearchResult;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.text.SimpleDateFormat;
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
        solrClient.addBean(article);
        solrClient.commit();
    }
    //查找
    public SearchResult Search(SolrQuery query) throws IOException, SolrServerException {
        List<ARTICLEmodel> articleList=new ArrayList<ARTICLEmodel>();
        QueryResponse solrResponse=solrClient.query(query);
        SolrDocumentList solrDocumentList= solrResponse.getResults();
//        List<ARTICLE> articleList=solrResponse.getBeans(ARTICLE.class);

        for (SolrDocument solrDocument :solrDocumentList){
            ARTICLEmodel article=new ARTICLEmodel();
            solrDocument.get("id");
            article.setCONTENT((String) solrDocument.getFieldValue("CONTENT"));
            List<Long>stringList= (List<Long>) solrDocument.getFieldValue("ARTICLEID");
            Long articleid= stringList.get(0);
            article.setARTICLEID(Math.toIntExact(articleid));
            String  nums= (String) solrDocument.getFieldValue("NUMS");
            article.setNUMS(Integer.parseInt(nums));
            java.util.Date dates=(java.util.Date) solrDocument.getFieldValue("TIME");

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dateString = formatter.format(dates);

            article.setTIME(dateString);
            article.setTITLE((String) solrDocument.getFieldValue("TITLE"));
            String  type= (String) solrDocument.getFieldValue("TYPE");
            article.setTYPE(Integer.parseInt(type));

            articleList.add(article);
        }
//        System.out.println(articleList.get(0).getTITLE());
        if (articleList.size()>0){
            System.out.println(articleList.get(0).getTITLE());
            SearchResult searchResult=new SearchResult();
            searchResult.setObjects(articleList);
            searchResult.setResultCount(articleList.size());
            return searchResult;
        }

        return null;

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
