package com.fuya.fuyasolr.Solr.serviceImpl;

import com.fuya.fuyasolr.Solr.dao.COMPANYINFOSearchdao;
import com.fuya.fuyasolr.Solr.service.COMPANYINFOSolrService;
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
}
