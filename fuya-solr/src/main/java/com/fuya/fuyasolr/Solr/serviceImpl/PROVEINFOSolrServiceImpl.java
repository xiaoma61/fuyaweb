package com.fuya.fuyasolr.Solr.serviceImpl;

import com.fuya.fuyaservice.PROVEINFOService;
import com.fuya.fuyasolr.Solr.dao.PROVEINFOSearchdao;
import com.fuya.fuyasolr.Solr.service.PROVEINFOSolrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PROVEINFOSolrServiceImpl implements PROVEINFOSolrService {
    @Autowired
    PROVEINFOSearchdao proveinfoSearchdao;
//    @Autowired
//    PROVEINFOService proveinfoService;

    public void addPROVEINFO(int id){
        proveinfoSearchdao.addPROVEINFO(id);
    }

}
