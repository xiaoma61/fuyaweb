package com.fuya.fuyasolr.Solr.serviceImpl;

import com.fuya.fuyadao.entity.YUESAOOTHERPROVE;
import com.fuya.fuyasolr.SearchResult.SearchResult;
import com.fuya.fuyasolr.Solr.dao.YUESAOOTHERPROVESearchdao;
import com.fuya.fuyasolr.Solr.service.YUESAOOTHERPROVESolrService;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class YUESAOOTHERPROVESolrServiceImpl implements YUESAOOTHERPROVESolrService {
    @Autowired
    YUESAOOTHERPROVESearchdao yuesaootherproveSearchdao;
    @Override
    public void addYUESAOOTHERPROVE(int id) throws IOException, SolrServerException {
        yuesaootherproveSearchdao.addYUESAOOTHERPROVE(id);

    }

    @Override
    public void delete(int id) throws IOException, SolrServerException {
        SolrQuery solrQuery=new SolrQuery();
        String query="YUESAOOTHERPROVEID:"+id;
        solrQuery.set("q",query);
        String ids=yuesaootherproveSearchdao.Searchid(solrQuery);
        yuesaootherproveSearchdao.delete(ids);

        System.out.println(query);
        yuesaootherproveSearchdao.delete(query);

    }

    @Override
    public SearchResult searchbyuserid(int userid, int start, int rows) throws IOException, SolrServerException {
        SolrQuery solrQuery=new SolrQuery();

        solrQuery.set("q","YUESAOOTHERPROVEUSERID:"+userid);
        System.out.println("YUESAOOTHERPROVEUSERID:"+userid);
        solrQuery.setStart(start);
        solrQuery.setRows(rows);
        SearchResult result=yuesaootherproveSearchdao.search(solrQuery);
        return result;

    }
}
