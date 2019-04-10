package com.fuya.fuyasolr.Solr.dao;

import com.fuya.fuyadao.entity.COMPANYBASICINFO;
import com.fuya.fuyaservice.COMPANYBASICINFOService;
import com.fuya.fuyasolr.SearchResult.SearchResult;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
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

//        SolrInputDocument solrInputDocument=new SolrInputDocument();
//        solrInputDocument.addField("companybasicinfoID",companybasicinfo.getID());
//        solrInputDocument.addField("companybasicinfoUSERID",companybasicinfo.getUSERID());
//        solrInputDocument.addField("companybasicinfoLEVELS",companybasicinfo.getLEVELS());//等级
//        solrInputDocument.addField("companybasicinfoINTRODUCE",companybasicinfo.getINTRODUCE());//介绍
//        solrInputDocument.addField("companybasicinfoCORPORATENAME",companybasicinfo.getCORPORATENAME());//
//        solrInputDocument.addField("companybasicinfoADDRESS",companybasicinfo.getADDRESS());//服务地址
//        solrInputDocument.addField("companybasicinfoNUMS",companybasicinfo.getNUMS());//服务数量
//        solrClient.add(solrInputDocument);
        solrClient.addBean(companybasicinfo);
        solrClient.commit();
        System.out.println("companybasicinfo:"+id);
    }
    //查找信息
    public SearchResult SearchCOMPANYBASICINFO(SolrQuery solrQuery, String keyword) throws IOException, SolrServerException {
        List<COMPANYBASICINFO>companybasicinfoList=new ArrayList<>();
        QueryResponse Response=solrClient.query(solrQuery);
        SolrDocumentList solrDocumentList=Response.getResults();


        Map<String, Map<String,List<String>>>highlight=Response.getHighlighting();
        SearchResult searchResult=new SearchResult();
        long result=solrDocumentList.getNumFound();
        searchResult.setResultCount((int) result);
        for (SolrDocument solrInputDocument :solrDocumentList){
            COMPANYBASICINFO companybasicinfo=new COMPANYBASICINFO();
            companybasicinfo.setUSERID((Integer) solrInputDocument.getFieldValue("USERID"));
            companybasicinfo.setCORPORATENAME((String) solrInputDocument.getFieldValue("CORPORATENAME"));
            companybasicinfo.setADDRESS((String) solrInputDocument.getFieldValue("ADDRESS"));
            companybasicinfo.setINTRODUCE((String) solrInputDocument.getFieldValue("INTRODUCE"));
            companybasicinfo.setLEVELS((Integer) solrInputDocument.getFieldValue("LEVELS"));
            companybasicinfo.setCOMPANYBASICINFOID((Integer) solrInputDocument.getFieldValue("COMPANYBASICINFOID"));
            companybasicinfo.setNUMS((Integer) solrInputDocument.getFieldValue("NUMS"));

            if (keyword!=null&&!keyword.equals("")){
                List<String>list=highlight.get(solrInputDocument.get("id")).get("CORPORATENAME");
                String name=null;
                if (list!=null&&list.size()>0){
                    name=list.get(0);

                }else {
                    name= (String) solrInputDocument.getFieldValue("CORPORATENAME");

                }
                companybasicinfo.setCORPORATENAME(name);
            }


            companybasicinfoList.add(companybasicinfo);


        }
        searchResult.setObjects(companybasicinfoList);




        return  searchResult;
    }
    public COMPANYBASICINFO Search(SolrQuery solrQuery) throws IOException, SolrServerException {
        COMPANYBASICINFO companybasicinfo=new COMPANYBASICINFO();
        QueryResponse solrResponse=solrClient.query(solrQuery);
        SolrDocumentList solrDocumentList= solrResponse.getResults();
        for (SolrDocument solrInputDocument :solrDocumentList){

            long COMPANYBASICINFOID= (long) solrInputDocument.getFieldValue("COMPANYBASICINFOID");
            companybasicinfo.setCOMPANYBASICINFOID(Math.toIntExact(COMPANYBASICINFOID));
            long USERSID= (long) solrInputDocument.getFieldValue("USERID");
            companybasicinfo.setUSERID(Math.toIntExact(USERSID));
            companybasicinfo.setCORPORATENAME((String) solrInputDocument.getFieldValue("CORPORATENAME"));
            companybasicinfo.setADDRESS((String) solrInputDocument.getFieldValue("ADDRESS"));
            companybasicinfo.setINTRODUCE((String) solrInputDocument.getFieldValue("INTRODUCE"));
            long LEVELS= (long) solrInputDocument.getFieldValue("LEVELS");
            companybasicinfo.setLEVELS(Math.toIntExact(LEVELS));
            String nums= (String) solrInputDocument.getFieldValue("NUMS");
            companybasicinfo.setNUMS(Integer.parseInt(nums));
            return companybasicinfo;
        }
        return null;

    }
    //查找信息
    public  String SearchNAME(SolrQuery solrQuery) throws IOException, SolrServerException {
        String name=null;
        QueryResponse Response=solrClient.query(solrQuery);
        SolrDocumentList solrDocumentList=Response.getResults();
        //查找公司姓名
        for (SolrDocument solrInputDocument :solrDocumentList){
            name= (String) solrInputDocument.getFieldValue("CORPORATENAME");
        }
        return name;


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
