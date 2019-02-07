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

    //添加数据到solr
    public void addALL() throws IOException, SolrServerException {

        //添加数据
        List<YUESOBASICINFO>yuesobasicinfoList=yuesobasicinfoService.findAll();

        for (YUESOBASICINFO yuesobasicinfo:yuesobasicinfoList){
            SolrInputDocument solrInputDocument=new SolrInputDocument();
            solrInputDocument.addField("yuesaoID",yuesobasicinfo.getID());
            solrInputDocument.addField("yuesaoAGE",yuesobasicinfo.getAGE());
            solrInputDocument.addField("yuesaoLEVELS",yuesobasicinfo.getLEVELS());
            solrInputDocument.addField("yuesaoWAGES",yuesobasicinfo.getWAGES());
            solrInputDocument.addField("yuesaoNATIVEPLACE",yuesobasicinfo.getNATIVEPLACE());
            solrInputDocument.addField("yuesaoNAME",yuesobasicinfo.getNAME());
            solrInputDocument.addField("yuesaoSENIORITY",yuesobasicinfo.getSENIORITY());
            solrInputDocument.addField("yuesaoEMAIL",yuesobasicinfo.getEMAIL());
            solrInputDocument.addField("yuesaoEDUCATION",yuesobasicinfo.getEDUCATION());
            solrInputDocument.addField("yuesaoCOMPANYID",yuesobasicinfo.getCOMPANYID());
            solrInputDocument.addField("yuesaoHEIGHT",yuesobasicinfo.getHEIGHT());
            solrInputDocument.addField("yuesaoIDCARD",yuesobasicinfo.getIDCARD());
            solrInputDocument.addField("yuesaoPHONE",yuesobasicinfo.getPHONE());
            solrInputDocument.addField("yuesaoPHOTO",yuesobasicinfo.getPHOTO());
            solrInputDocument.addField("yuesaoWEIGHT",yuesobasicinfo.getWEIGHT());
            solrInputDocument.addField("yuesaoWORKAREA",yuesobasicinfo.getWORKAREA());

                client.add(solrInputDocument);
            }
        client.commit();

    }
    public  void addYUESOBASICINFO(int id) throws IOException, SolrServerException {
       YUESOBASICINFO yuesobasicinfo=yuesobasicinfoService.findbyid(id);
        SolrInputDocument solrInputDocument=new SolrInputDocument();
        solrInputDocument.addField("yuesaoID",yuesobasicinfo.getID());
        solrInputDocument.addField("yuesaoAGE",yuesobasicinfo.getAGE());
        solrInputDocument.addField("yuesaoLEVELS",yuesobasicinfo.getLEVELS());
        solrInputDocument.addField("yuesaoWAGES",yuesobasicinfo.getWAGES());
        solrInputDocument.addField("yuesaoNATIVEPLACE",yuesobasicinfo.getNATIVEPLACE());
        solrInputDocument.addField("yuesaoNAME",yuesobasicinfo.getNAME());
        solrInputDocument.addField("yuesaoSENIORITY",yuesobasicinfo.getSENIORITY());
        solrInputDocument.addField("yuesaoEMAIL",yuesobasicinfo.getEMAIL());
        solrInputDocument.addField("yuesaoEDUCATION",yuesobasicinfo.getEDUCATION());
        solrInputDocument.addField("yuesaoCOMPANYID",yuesobasicinfo.getCOMPANYID());
        solrInputDocument.addField("yuesaoHEIGHT",yuesobasicinfo.getHEIGHT());
        solrInputDocument.addField("yuesaoIDCARD",yuesobasicinfo.getIDCARD());
        solrInputDocument.addField("yuesaoPHONE",yuesobasicinfo.getPHONE());
        solrInputDocument.addField("yuesaoPHOTO",yuesobasicinfo.getPHOTO());
        solrInputDocument.addField("yuesaoWEIGHT",yuesobasicinfo.getWEIGHT());
        solrInputDocument.addField("yuesaoWORKAREA",yuesobasicinfo.getWORKAREA());

        client.add(solrInputDocument);
        client.commit();

    }


    //执行查找分页
    public SearchResult  searchResult(SolrQuery solrQuery,boolean flag) throws IOException, SolrServerException {

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
            yuesobasicinfo.setID((Integer) solrDocument.getFieldValue("yuesaoID"));
            yuesobasicinfo.setAGE((String) solrDocument.getFieldValue("yuesaoAGE"));
            yuesobasicinfo.setCOMPANYID((String) solrDocument.getFieldValue("yuesaoLEVELS"));
            yuesobasicinfo.setEDUCATION((String) solrDocument.getFieldValue("yuesaoWAGES"));
            yuesobasicinfo.setEMAIL((String) solrDocument.getFieldValue("yuesaoEMAIL"));
            yuesobasicinfo.setHEIGHT((String) solrDocument.getFieldValue("yuesaoHEIGHT"));
            yuesobasicinfo.setIDCARD((String) solrDocument.getFieldValue("yuesaoIDCARD"));

            yuesobasicinfo.setNATIVEPLACE((String) solrDocument.getFieldValue("yuesaoNATIVEPLACE"));
            yuesobasicinfo.setWEIGHT((String) solrDocument.getFieldValue("yuesaoWEIGHT"));
            yuesobasicinfo.setSENIORITY((String) solrDocument.getFieldValue("yuesaoSENIORITY"));
            yuesobasicinfo.setPHOTO((String) solrDocument.getFieldValue("yuesaoPHOTO"));
            yuesobasicinfo.setLEVELS((Integer) solrDocument.getFieldValue("yuesaoLEVELS"));
            yuesobasicinfo.setWAGES((String) solrDocument.getFieldValue("yuesaoWAGES"));
            yuesobasicinfo.setEDUCATION((String) solrDocument.getFieldValue("yuesaoEDUCATION"));
            yuesobasicinfo.setCOMPANYID((String) solrDocument.getFieldValue("yuesaoCOMPANYID"));
            yuesobasicinfo.setPHONE((String) solrDocument.getFieldValue("yuesaoPHONE"));
            yuesobasicinfo.setWORKAREA((String) solrDocument.getFieldValue("yuasaoWORKAREA"));


            if (flag){
                List<String>list=highlight.get(solrDocument.get("id")).get("yuesaoNAME");
                String name=null;
                if (list!=null&&list.size()>0){
                    name=list.get(0);

                }else {
                    name= (String) solrDocument.get("yuesaoNAME");
                }
                yuesobasicinfo.setNAME(name);
            }else{

                yuesobasicinfo.setNAME((String) solrDocument.get("yuesaoNAME"));
            }


            yuesobasicinfoList.add(yuesobasicinfo);


        }
        searchResult.setObjects(yuesobasicinfoList);
        //返回条数和结果

        return searchResult;
    }
    public YUESOBASICINFO  searchbyid(SolrQuery solrQuery) throws IOException, SolrServerException {

        //flag执不执行高亮，true 高亮
        //根据query查询索引库
        QueryResponse queryResponse=client.query(solrQuery);
        //取得查询结果
        SolrDocumentList solrDocumentList=queryResponse.getResults();
        //取得查询结果总记录数
        YUESOBASICINFO yuesobasicinfo=new YUESOBASICINFO();
        for (SolrDocument solrDocument: solrDocumentList){

            yuesobasicinfo.setID((Integer) solrDocument.get("yuesaoID"));
            yuesobasicinfo.setAGE((String) solrDocument.get("yuesaoAGE"));
            yuesobasicinfo.setCOMPANYID((String) solrDocument.get("yuesaoLEVELS"));
            yuesobasicinfo.setEDUCATION((String) solrDocument.get("yuesaoWAGES"));
            yuesobasicinfo.setEMAIL((String) solrDocument.get("yuesaoEMAIL"));
            yuesobasicinfo.setHEIGHT((String) solrDocument.get("yuesaoHEIGHT"));
            yuesobasicinfo.setIDCARD((String) solrDocument.get("yuesaoIDCARD"));

            yuesobasicinfo.setNATIVEPLACE((String) solrDocument.get("yuesaoNATIVEPLACE"));
            yuesobasicinfo.setWEIGHT((String) solrDocument.get("yuesaoWEIGHT"));
            yuesobasicinfo.setSENIORITY((String) solrDocument.get("yuesaoSENIORITY"));
            yuesobasicinfo.setPHOTO((String) solrDocument.get("yuesaoPHOTO"));
            yuesobasicinfo.setLEVELS((Integer) solrDocument.get("yuesaoLEVELS"));
            yuesobasicinfo.setWAGES((String) solrDocument.get("yuesaoWAGES"));
            yuesobasicinfo.setEDUCATION((String) solrDocument.get("yuesaoEDUCATION"));
            yuesobasicinfo.setCOMPANYID((String) solrDocument.get("yuesaoCOMPANYID"));
            yuesobasicinfo.setPHONE((String) solrDocument.get("yuesaoPHONE"));
            yuesobasicinfo.setWORKAREA((String) solrDocument.get("yuasaoWORKAREA"));
            yuesobasicinfo.setNAME((String) solrDocument.get("yuesaoNAME"));



        }

        //返回条数和结果

        return yuesobasicinfo;
    }


}
