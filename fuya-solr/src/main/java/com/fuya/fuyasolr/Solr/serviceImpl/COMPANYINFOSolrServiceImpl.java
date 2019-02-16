package com.fuya.fuyasolr.Solr.serviceImpl;

import com.fuya.fuyadao.entity.COMPANYINFO;
import com.fuya.fuyasolr.Solr.dao.COMPANYINFOSearchdao;
import com.fuya.fuyasolr.Solr.service.COMPANYINFOSolrService;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class COMPANYINFOSolrServiceImpl implements COMPANYINFOSolrService {

   @Autowired
    COMPANYINFOSearchdao companyinfoSearchdao;
    @Override
    public void addCOMPANYINFO(int id) throws IOException, SolrServerException {

        companyinfoSearchdao.addCOMPANYINFO(id);
    }

    @Override
    public COMPANYINFO findCOMPANYINFObyuserid(int id) throws IOException, SolrServerException {
        SolrQuery query=new SolrQuery();
        query.set("q","USERSID:"+id);
        return companyinfoSearchdao.Search(query);
    }

    @Override
    public void update(int COMPANYINFOID) throws IOException, SolrServerException {
        //

        SolrQuery query=new SolrQuery();
        query.set("q","COMPANYINFOID:"+COMPANYINFOID);
        String id=companyinfoSearchdao.Searchid(query);
        delete(id);
        addCOMPANYINFO(COMPANYINFOID);
    }

    @Override
    public void delete(String id) throws IOException, SolrServerException {

        companyinfoSearchdao.delete(id);
    }
}
