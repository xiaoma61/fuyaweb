package com.fuya.fuyasolr.Solr.serviceImpl;

import com.fuya.fuyaservice.PROVEINFOService;
import com.fuya.fuyasolr.Solr.dao.PROVEINFOSearchdao;
import com.fuya.fuyasolr.Solr.service.PROVEINFOSolrService;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class PROVEINFOSolrServiceImpl implements PROVEINFOSolrService {
    @Autowired
    PROVEINFOSearchdao proveinfoSearchdao;
//    @Autowired
//    PROVEINFOService proveinfoService;

    public void addPROVEINFO(int id) throws IOException, SolrServerException {
        proveinfoSearchdao.addPROVEINFO(id);
    }

}
