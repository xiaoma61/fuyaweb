package com.fuya.fuyasolr.Solr.serviceImpl;

import com.fuya.fuyaservice.PROVEINFOService;
import com.fuya.fuyasolr.Solr.dao.PROVEINFOSearchdao;
import com.fuya.fuyasolr.Solr.service.PROVEINFOSolrService;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class PROVEINFOSolrServiceImpl implements PROVEINFOSolrService {
    @Autowired
    PROVEINFOSearchdao proveinfoSearchdao;
//    @Autowired
//    PROVEINFOService proveinfoService;

    public void addPROVEINFO(int id) throws IOException, SolrServerException {
        proveinfoSearchdao.addPROVEINFO(id);
    }

    @Override
    public void delete(int id) throws IOException, SolrServerException {
        SolrQuery solrQuery=new SolrQuery();
        //查询语句
        solrQuery.set("q","USERSID:"+id);
        List<String> ids=proveinfoSearchdao.findid(solrQuery);
        if (ids!=null&&ids.size()>0){
            for (String mid:ids){
                proveinfoSearchdao.delete(mid);
            }

        }

    }

}
