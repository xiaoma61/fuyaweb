package com.fuya.fuyasolr.Solr.dao;

import com.fuya.fuyadao.entity.COMPANYINFO;
import com.fuya.fuyaservice.COMPANYBASICINFOService;
import com.fuya.fuyaservice.COMPANYINFOService;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
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

//        SolrInputDocument solrInputDocument=new SolrInputDocument();
//        solrInputDocument.addField("companyinfoADDRESS",companyinfo.getADDRESS());
//        solrInputDocument.addField("companyinfoCONTACTNAME",companyinfo.getCONTACTNAME());
//        solrInputDocument.addField("companyinfoCONTACTPHONE",companyinfo.getCONTACTPHONE());
//        solrInputDocument.addField("companyinfoEMAIL",companyinfo.getEMAIL());
//        solrInputDocument.addField("companyinfoID",companyinfo.getID());
//        solrInputDocument.addField("companyinfoIDCARD",companyinfo.getIDCARD());
//        solrInputDocument.addField("companyinfoIDCARDFILE",companyinfo.getIDCARDFILE());
//        solrInputDocument.addField("companyinfoLICENCE",companyinfo.getLICENCE());
//
//        solrInputDocument.addField("companyinfoLICENCENO",companyinfo.getLICENCENO());
//        solrInputDocument.addField("companyinfoUSERSID",companyinfo.getUSERSID());
//        solrClient.add(solrInputDocument);
        solrClient.addBean(companyinfo);
        solrClient.commit();
        System.out.println("companyinfo:"+id);
    }
    public COMPANYINFO Search(SolrQuery solrQuery) throws IOException, SolrServerException {
            COMPANYINFO companyinfo=new COMPANYINFO();
            QueryResponse solrResponse=solrClient.query(solrQuery);
            SolrDocumentList solrDocumentList= solrResponse.getResults();
            for (SolrDocument solrDocument :solrDocumentList){

            long COMPANYINFOID= (long) solrDocument.getFieldValue("COMPANYINFOID");
            companyinfo.setCOMPANYINFOID(Math.toIntExact(COMPANYINFOID));
            long USERSID= (long) solrDocument.getFieldValue("USERSID");
            companyinfo.setUSERSID(Math.toIntExact(USERSID));
            companyinfo.setCONTACTNAME((String) solrDocument.getFieldValue("CONTACTNAME"));//公司联系人
            companyinfo.setCONTACTPHONE((String) solrDocument.getFieldValue("CONTACTPHONE"));//联系人电话
            companyinfo.setLICENCENO((String) solrDocument.getFieldValue("LICENCENO"));//注册码
            companyinfo.setIDCARDFILE((String) solrDocument.getFieldValue("IDCARDFILE"));//身份证
            companyinfo.setIDCARD((String) solrDocument.getFieldValue("IDCARD"));
            companyinfo.setADDRESS((String) solrDocument.getFieldValue("ADDRESS"));//公司地址
            companyinfo.setLICENCE((String) solrDocument.getFieldValue("LICENCE"));//营业执照
            companyinfo.setEMAIL((String) solrDocument.getFieldValue("EMAIL"));//邮箱

                return companyinfo;

        }
        return null;
    }
    public  String Searchid(SolrQuery solrQuery) throws IOException, SolrServerException {
        String id=null;
        QueryResponse Response=solrClient.query(solrQuery);
        SolrDocumentList solrDocumentList=Response.getResults();
        //查找公司姓名
        for (SolrDocument solrInputDocument :solrDocumentList){
            id= (String) solrInputDocument.get("id");
        }
        return id;


    }
    public void delete(String id) throws IOException, SolrServerException {
        solrClient.deleteById(id);
        solrClient.commit();
    }



}
