package com.fuya.fuyasolr.Solr.dao;

import com.fuya.fuyadao.entity.COMPANYBASICINFO;
import com.fuya.fuyadao.entity.YUESOBASICINFO;
import com.fuya.fuyaservice.COMPANYBASICINFOService;
import com.fuya.fuyasolr.SearchResult.SearchResult;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrResponse;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class COMPANYBASICINFOSearchdao {

    @Autowired
    COMPANYBASICINFOService companybasicinfoService;
    @Autowired
    SolrClient solrClient;
    //添加信息
    public void addCOMPANYBASICINFO(int id) throws IOException, SolrServerException {
        COMPANYBASICINFO companybasicinfo=companybasicinfoService.findByID(id);
        SolrInputDocument solrInputDocument=new SolrInputDocument();
        solrInputDocument.addField("companybasicinfoID",companybasicinfo.getID());
        solrInputDocument.addField("companybasicinfoUSERID",companybasicinfo.getUSERID());
        solrInputDocument.addField("companybasicinfoLEVELS",companybasicinfo.getLEVELS());//等级
        solrInputDocument.addField("companybasicinfoINTRODUCE",companybasicinfo.getINTRODUCE());//介绍
        solrInputDocument.addField("companybasicinfoCORPORATENAME",companybasicinfo.getCORPORATENAME());//
        solrInputDocument.addField("companybasicinfoADDRESS",companybasicinfo.getADDRESS());//服务地址
        solrInputDocument.addField("companybasicinfoNUMS",companybasicinfo.getNUMS());//服务数量
        solrClient.add(solrInputDocument);
        solrClient.commit();
    }
    //查找信息
    public  SearchResult SearchCOMPANYBASICINFO(SolrQuery solrQuery,String keyword) throws IOException, SolrServerException {
        List<COMPANYBASICINFO>companybasicinfoList=new ArrayList<>();
        QueryResponse Response=solrClient.query(solrQuery);
        SolrDocumentList solrDocumentList=Response.getResults();


        Map<String, Map<String,List<String>>>highlight=Response.getHighlighting();
        SearchResult searchResult=new SearchResult();
        long result=solrDocumentList.getNumFound();
        searchResult.setResultCount((int) result);
        for (SolrDocument solrInputDocument :solrDocumentList){
            COMPANYBASICINFO companybasicinfo=new COMPANYBASICINFO();
            companybasicinfo.setUSERID((Integer) solrInputDocument.get("companybasicinfoID"));
            companybasicinfo.setCORPORATENAME((String) solrInputDocument.get("companybasicinfoCORPORATENAME"));
            companybasicinfo.setADDRESS((String) solrInputDocument.get("companybasicinfoADDRESS"));
            companybasicinfo.setINTRODUCE((String) solrInputDocument.get("companybasicinfoINTRODUCE"));
            companybasicinfo.setLEVELS((Integer) solrInputDocument.get("companybasicinfoLEVELS"));
            companybasicinfo.setID((Integer) solrInputDocument.get("companybasicinfoID"));
            companybasicinfo.setNUMS((Integer) solrInputDocument.get("companybasicinfoNUMS"));

            if (keyword!=null&&!keyword.equals("")){
                List<String>list=highlight.get(solrInputDocument.get("id")).get("companybasicinfoCORPORATENAME");
                String name=null;
                if (list!=null&&list.size()>0){
                    name=list.get(0);

                }else {
                    name= (String) solrInputDocument.get("companybasicinfoCORPORATENAME");

                }
                companybasicinfo.setCORPORATENAME(name);
            }


            companybasicinfoList.add(companybasicinfo);


        }
        searchResult.setObjects(companybasicinfoList);




        return  searchResult;
    }


}
