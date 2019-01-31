package com.fuya.fuyasolr.Solr.dao;

import com.fuya.fuyadao.entity.COMPANYINFO;
import com.fuya.fuyaservice.COMPANYBASICINFOService;
import com.fuya.fuyaservice.COMPANYINFOService;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;

@Repository
public class COMPANYINFOSearchdao {
    //添加
    @Autowired
    COMPANYINFOService service;
    @Autowired
    SolrClient solrClient;
    public void addCOMPANYINFO(int id) throws IOException, SolrServerException {
        COMPANYINFO companyinfo=service.findbyid(id);
        SolrInputDocument solrInputDocument=new SolrInputDocument();
        solrInputDocument.addField("companyinfoADDRESS",companyinfo.getADDRESS());
        solrInputDocument.addField("companyinfoCONTACTNAME",companyinfo.getCONTACTNAME());
        solrInputDocument.addField("companyinfoCONTACTPHONE",companyinfo.getCONTACTPHONE());
        solrInputDocument.addField("companyinfoEMAIL",companyinfo.getEMAIL());
        solrInputDocument.addField("companyinfoID",companyinfo.getID());
        solrInputDocument.addField("companyinfoIDCARD",companyinfo.getIDCARD());
        solrInputDocument.addField("companyinfoIDCARDFILE",companyinfo.getIDCARDFILE());
        solrInputDocument.addField("companyinfoLICENCE",companyinfo.getLICENCE());

        solrInputDocument.addField("companyinfoLICENCENO",companyinfo.getLICENCENO());
        solrInputDocument.addField("companyinfoUSERSID",companyinfo.getUSERSID());
        solrClient.add(solrInputDocument);
        solrClient.commit();
    }


}
