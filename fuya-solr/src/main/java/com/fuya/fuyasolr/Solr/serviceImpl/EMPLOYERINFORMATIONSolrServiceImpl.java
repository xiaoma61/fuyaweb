package com.fuya.fuyasolr.Solr.serviceImpl;

import com.fuya.fuyasolr.Solr.dao.EMPLOYERINFORMATIONSearchdao;
import com.fuya.fuyasolr.Solr.service.EMPLOYERINFORMATIONSolrService;
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

}
