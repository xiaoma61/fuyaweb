package com.fuya.fuyasolr.Solr.serviceImpl;


import com.fuya.fuyasolr.SearchResult.SearchResult;
import com.fuya.fuyasolr.Solr.dao.RECRUITSearchdao;
import com.fuya.fuyasolr.Solr.service.RECRUITSolrService;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
        SolrQuery solrQuery=new SolrQuery();
        solrQuery.set("q","RECRUITID:"+id);
        String searchid= recruitSearchdao.Searchid(solrQuery);
        return searchid;
    }

    @Override
    public void UpdateByRECRUITID(int id) throws IOException, SolrServerException {
        System.out.println("ddddd"+id);
        String ids=SearchIDByRECRUITID(id);
        System.out.println("ids++++"+ids);
        if (ids!=null&&!ids.equals("")){
            recruitSearchdao.delete(ids);
            addRECRUIT(id);
        }


    }

    @Override
    public void DeleteByRECRUITID(int id) throws IOException, SolrServerException {
        String ids=SearchIDByRECRUITID(id);
        recruitSearchdao.delete(ids);
    }

    @Override
    public SearchResult SearchBytime() throws IOException, SolrServerException {
        SolrQuery solrQuery=new SolrQuery();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        String stime = "STARTTIME:[* TO "+ sdf.format(new Date())+"]";

        solrQuery.set("q",stime);
        SearchResult result=recruitSearchdao.Search(solrQuery);
        SolrQuery solrQuery1=new SolrQuery();
        String etime = "ENDTIME:["+ sdf.format(new Date())+" TO *]";
        solrQuery1.set("q",etime);
        SearchResult result1=recruitSearchdao.Search(solrQuery1);

        if (result.getObjects()!=null){
            result.getObjects().removeAll(result1.getObjects());
            result1.getObjects().removeAll(result.getObjects());
            SearchResult resultfin=result1;
            return resultfin;
        }
        return null;







    }
}
