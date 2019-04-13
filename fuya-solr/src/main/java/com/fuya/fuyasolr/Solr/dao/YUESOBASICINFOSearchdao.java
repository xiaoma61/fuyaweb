package com.fuya.fuyasolr.Solr.dao;

import com.fuya.fuyadao.entity.YUESOBASICINFO;
import com.fuya.fuyaservice.YUESOBASICINFOService;
import com.fuya.fuyasolr.SearchResult.SearchResult;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class YUESOBASICINFOSearchdao {
    @Autowired
    private SolrClient client;
    @Autowired
    private YUESOBASICINFOService yuesobasicinfoService;





    //执行查找分页
    public SearchResult searchResult(SolrQuery solrQuery, boolean flag) throws IOException, SolrServerException {

       //flag执不执行高亮，true 高亮
        //根据query查询索引库
        QueryResponse queryResponse=client.query(solrQuery);
        //取得查询结果
        SolrDocumentList solrDocumentList=queryResponse.getResults();
        //取得查询结果总记录数
        long numFound =solrDocumentList.getNumFound();
       // SearchResult result=new SearchResult();
        Map<String,Map<String,List<String>>>highlight=queryResponse.getHighlighting();
        List<YUESOBASICINFO>yuesobasicinfoList=new ArrayList<>();
        SearchResult searchResult=new SearchResult();
        searchResult.setResultCount((int) numFound);
        for (SolrDocument solrDocument: solrDocumentList){
            YUESOBASICINFO yuesobasicinfo=new YUESOBASICINFO();
            yuesobasicinfo.setYUESOBASICINFOID((Integer) solrDocument.getFieldValue("YUESOBASICINFOID"));
            yuesobasicinfo.setAGE((int) solrDocument.getFieldValue("AGE"));
            yuesobasicinfo.setCOMPANYID((String) solrDocument.getFieldValue("LEVELS"));
            yuesobasicinfo.setEDUCATION((String) solrDocument.getFieldValue("WAGES"));
            yuesobasicinfo.setEMAIL((String) solrDocument.getFieldValue("EMAIL"));
            yuesobasicinfo.setHEIGHT((String) solrDocument.getFieldValue("HEIGHT"));
            yuesobasicinfo.setIDCARD((String) solrDocument.getFieldValue("IDCARD"));

            yuesobasicinfo.setNATIVEPLACE((String) solrDocument.getFieldValue("NATIVEPLACE"));
            yuesobasicinfo.setWEIGHT((String) solrDocument.getFieldValue("WEIGHT"));
            yuesobasicinfo.setSENIORITY((String) solrDocument.getFieldValue("SENIORITY"));
            yuesobasicinfo.setPHOTO((String) solrDocument.getFieldValue("PHOTO"));
            yuesobasicinfo.setLEVELS((Integer) solrDocument.getFieldValue("LEVELS"));
            yuesobasicinfo.setWAGES((int) solrDocument.getFieldValue("WAGES"));
            yuesobasicinfo.setEDUCATION((String) solrDocument.getFieldValue("EDUCATION"));
            yuesobasicinfo.setCOMPANYID((String) solrDocument.getFieldValue("COMPANYID"));
            yuesobasicinfo.setPHONE((String) solrDocument.getFieldValue("PHONE"));
            yuesobasicinfo.setWORKAREA((String) solrDocument.getFieldValue("yuasaoWORKAREA"));


            if (flag){
                List<String>list=highlight.get(solrDocument.get("id")).get("NAME");
                String name=null;
                if (list!=null&&list.size()>0){
                    name=list.get(0);

                }else {
                    name= (String) solrDocument.get("NAME");
                }
                yuesobasicinfo.setNAME(name);
            }else{

                yuesobasicinfo.setNAME((String) solrDocument.get("NAME"));
            }


            yuesobasicinfoList.add(yuesobasicinfo);


        }
        searchResult.setObjects(yuesobasicinfoList);
        //返回条数和结果

        return searchResult;
    }
    public YUESOBASICINFO searchbyid(SolrQuery solrQuery) throws IOException, SolrServerException {

        //flag执不执行高亮，true 高亮
        //根据query查询索引库
        QueryResponse queryResponse=client.query(solrQuery);
        //取得查询结果
        SolrDocumentList solrDocumentList=queryResponse.getResults();
        //取得查询结果总记录数
        YUESOBASICINFO yuesobasicinfo=new YUESOBASICINFO();
        for (SolrDocument solrDocument: solrDocumentList){

            yuesobasicinfo.setYUESOBASICINFOID((Integer) solrDocument.get("ID"));
            yuesobasicinfo.setAGE((int) solrDocument.get("AGE"));
            yuesobasicinfo.setCOMPANYID((String) solrDocument.get("LEVELS"));
            yuesobasicinfo.setEDUCATION((String) solrDocument.get("WAGES"));
            yuesobasicinfo.setEMAIL((String) solrDocument.get("EMAIL"));
            yuesobasicinfo.setHEIGHT((String) solrDocument.get("HEIGHT"));
            yuesobasicinfo.setIDCARD((String) solrDocument.get("IDCARD"));

            yuesobasicinfo.setNATIVEPLACE((String) solrDocument.get("NATIVEPLACE"));
            yuesobasicinfo.setWEIGHT((String) solrDocument.get("WEIGHT"));
            yuesobasicinfo.setSENIORITY((String) solrDocument.get("SENIORITY"));
            yuesobasicinfo.setPHOTO((String) solrDocument.get("PHOTO"));
            yuesobasicinfo.setLEVELS((Integer) solrDocument.get("LEVELS"));
            yuesobasicinfo.setWAGES((int) solrDocument.get("WAGES"));
            yuesobasicinfo.setEDUCATION((String) solrDocument.get("EDUCATION"));
            yuesobasicinfo.setCOMPANYID((String) solrDocument.get("COMPANYID"));
            yuesobasicinfo.setPHONE((String) solrDocument.get("PHONE"));
            yuesobasicinfo.setWORKAREA((String) solrDocument.get("yuasaoWORKAREA"));
            yuesobasicinfo.setNAME((String) solrDocument.get("NAME"));



        }

        //返回条数和结果

        return yuesobasicinfo;
    }

    public String findid(SolrQuery solrQuery) throws IOException, SolrServerException {

        QueryResponse queryResponse=client.query(solrQuery);
        SolrDocumentList solrDocumentList=queryResponse.getResults();
        String id=null;
        for (SolrDocument Document : solrDocumentList ){

            id= (String) Document.get("id");
        }
        return id;

    }

}
