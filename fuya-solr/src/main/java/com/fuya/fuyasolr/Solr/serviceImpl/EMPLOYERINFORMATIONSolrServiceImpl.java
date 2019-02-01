package com.fuya.fuyasolr.Solr.serviceImpl;

import com.fuya.fuyadao.entity.EMPLOYERINFORMATION;
import com.fuya.fuyasolr.Solr.dao.EMPLOYERINFORMATIONSearchdao;
import com.fuya.fuyasolr.Solr.service.EMPLOYERINFORMATIONSolrService;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class EMPLOYERINFORMATIONSolrServiceImpl implements EMPLOYERINFORMATIONSolrService {
    @Autowired
    EMPLOYERINFORMATIONSearchdao employerinformationSearchdao;
    @Override
    public void addEMPLOYERINFORMATION(int id) throws IOException, SolrServerException {
        employerinformationSearchdao.addEMPLOYERINFORMATION(id);

    }

    @Override
    public EMPLOYERINFORMATION searchbyorderid(int orderid) throws IOException, SolrServerException {
        SolrQuery solrQuery=new SolrQuery();
        solrQuery.set("employerinformationODERID:"+orderid);
        EMPLOYERINFORMATION employerinformation=employerinformationSearchdao.search(solrQuery);

        return employerinformation;
    }

}
