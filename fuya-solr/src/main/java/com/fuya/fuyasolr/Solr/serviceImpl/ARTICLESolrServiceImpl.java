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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class ARTICLESolrServiceImpl implements ARTICLESolrService {
    @Autowired
    ARTICLESearchdao articleSearchdao;
  /*  @Override
    public void addARTICLE(int id) throws IOException, SolrServerException {
        articleSearchdao.addARTICLE(id);
    }
*/
    @Override
    public SearchResult Searchbytype(int type, int start, int rows) throws IOException, SolrServerException {
        SolrQuery solrQuery=new SolrQuery();
        String query="TYPE:"+type;
        solrQuery.setQuery(query);
        solrQuery.setStart(start);
        solrQuery.setRows(rows);
        solrQuery.setSort("NUMS", SolrQuery.ORDER.desc);
        SearchResult searchResult=articleSearchdao.Search(solrQuery,1);

        return searchResult;
    }

    @Override
    public SearchResult Searchbyid(int id) throws IOException, SolrServerException {
        SolrQuery solrQuery=new SolrQuery();
        solrQuery.set("q","ARTICLEID:"+id);
        SearchResult searchResult=articleSearchdao.Search(solrQuery,2);
//        String ids=articleSearchdao.Searchid(solrQuery);
//        System.out.println("ARTICLEID:"+ids);
//        String id=articleSearchdao.Searchid(solrQuery);

//        solrQuery.setQuery(query);
//        SearchResult searchResult=articleSearchdao.Search(solrQuery);


        return searchResult;
    }

    @Override
    public SearchResult SearchbyLikeName(String Name,int start,int rows) throws IOException, SolrServerException {
        SolrQuery solrQuery=new SolrQuery();
        solrQuery.set("q","TITLE:"+"*"+Name+"*");

        solrQuery.setStart(start);
        solrQuery.setRows(rows);
        SearchResult searchResult=articleSearchdao.Search(solrQuery,1);
        return searchResult;
    }

  /*  @Override
    public void update(int articleid) throws IOException, SolrServerException {
//        String id=null;
        SolrQuery solrQuery=new SolrQuery();
        solrQuery.set("q","ARTICLEID:"+articleid);
        String id=articleSearchdao.Searchid(solrQuery);
        System.out.println("ARTICLEID:"+articleid);
        System.out.println(id);
        //删除
        if (id!=null&&!id.equals("")){
            articleSearchdao.delete(id);
            articleSearchdao.addARTICLE(articleid);
        }




    }
*/
 /*   @Override
    public void delete(int articleid) throws IOException, SolrServerException {
        String id=null;
        SolrQuery solrQuery=new SolrQuery();
        solrQuery.set("q","ARTICLEID:"+articleid);
        System.out.println("ARTICLEID:"+articleid);
        id=articleSearchdao.Searchid(solrQuery);
        //删除
        System.out.println(id);
        if (id!=null&&!id.equals("")){
            articleSearchdao.delete(id);
        }


    }
*/
    @Override
    public SearchResult SearchbyTime(String starttime, String endtime,int start,int rows) throws IOException, SolrServerException {
        SolrQuery solrQuery=new SolrQuery();

       /* SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'");*/
        String time = "TIME:["+starttime+" TO "+endtime+"]";
        solrQuery.setStart(start);
        solrQuery.setRows(rows);
        solrQuery.set("q",time);
        SearchResult searchResult=articleSearchdao.Search(solrQuery,1);
        return searchResult;
    }
}
