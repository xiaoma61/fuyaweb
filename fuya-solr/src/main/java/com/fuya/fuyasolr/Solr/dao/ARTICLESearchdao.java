package com.fuya.fuyasolr.Solr.dao;

import com.fuya.fuyadao.entity.ARTICLE;
import com.fuya.fuyadao.model.ARTICLEmodel;
import com.fuya.fuyaservice.ARTICLEService;
import com.fuya.fuyasolr.SearchResult.SearchResult;
import com.fuya.fuyautil.BodyContentUtil;
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
    /*public  void  addARTICLE(int id) throws IOException, SolrServerException {

        ARTICLE article= articleService.findByID(id);
        solrClient.addBean(article);
        solrClient.commit();
    }*/
    //查找
    public  long getSolrIndexCount(int type){
        long num = 0;
        try {
            SolrQuery params = new SolrQuery();
            params.set("q", "TYPE: "+type);
            QueryResponse	rsp = solrClient.query(params);
            SolrDocumentList docs = rsp.getResults();
            num = docs.getNumFound();
            System.out.println(num);
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return num;
    }
    /**
     *
     * @param query
     * @param i=1缩小 i=2正常
     * @return
     * @throws IOException
     * @throws SolrServerException
     */
    public SearchResult Search(SolrQuery query,int i) throws IOException, SolrServerException {

        List<ARTICLEmodel> articleList=new ArrayList<ARTICLEmodel>();
        QueryResponse solrResponse=solrClient.query(query);
        int totalpages=0;


        SearchResult searchResult=new SearchResult();
        Long num=getSolrIndexCount(i)%10==0?getSolrIndexCount(i)/10:getSolrIndexCount(i)/10+1;
        searchResult.setTotalPage(Math.toIntExact(num));
        SolrDocumentList solrDocumentList= solrResponse.getResults();
//        List<ARTICLE> articleList=solrResponse.getBeans(ARTICLE.class);

        for (SolrDocument solrDocument :solrDocumentList){
            ARTICLEmodel article=new ARTICLEmodel();
            solrDocument.get("id");
            if (i==1){
                article.setCONTENT( BodyContentUtil.GetContent((String) solrDocument.getFieldValue("CONTENT")));

            }else {
                article.setCONTENT( (String) solrDocument.getFieldValue("CONTENT"));
            }


            String  ARTICLEID= (String) solrDocument.getFieldValue("ARTICLEID");
            article.setARTICLEID(Integer.parseInt(ARTICLEID));

            article.setNUMS((Integer) solrDocument.getFieldValue("NUMS"));
            java.util.Date dates=(java.util.Date) solrDocument.getFieldValue("TIME");

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dateString = formatter.format(dates);

            article.setTIME(dateString);
            article.setTITLE((String) solrDocument.getFieldValue("TITLE"));

            article.setTYPE((Integer) solrDocument.getFieldValue("TYPE"));

            articleList.add(article);
        }

        if (articleList.size()>0){


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
          id= (String) solrDocument.get("ARTICLEID");

        }
        return id;

    }

 /*   public void delete(String id) throws IOException, SolrServerException {
        solrClient.deleteById(id);
        solrClient.commit();

    }*/


}
