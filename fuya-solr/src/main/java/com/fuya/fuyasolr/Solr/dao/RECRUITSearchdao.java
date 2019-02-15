package com.fuya.fuyasolr.Solr.dao;

import com.fuya.fuyadao.entity.RECRUIT;
import com.fuya.fuyadao.model.ARTICLEmodel;
import com.fuya.fuyadao.model.RECRUITmodel;
import com.fuya.fuyaservice.RECRUITService;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class RECRUITSearchdao {
    @Autowired
    SolrClient solrClient;
    @Autowired
    RECRUITService recruitService;
    public  void  addRECRUIT(int id) throws IOException, SolrServerException {
        RECRUIT recruit =recruitService.findByID(id);
        solrClient.addBean(recruit);
        solrClient.commit();
    }
    public SearchResult Search(SolrQuery query) throws IOException, SolrServerException {
        List< RECRUITmodel> recruitList=new ArrayList<RECRUITmodel>();
        QueryResponse solrResponse=solrClient.query(query);
        SolrDocumentList solrDocumentList= solrResponse.getResults();
//        List<RECRUITmodel> recruitList=solrResponse.getBeans(RECRUITmodel.class);
        SearchResult searchResult=new SearchResult();

        for (SolrDocument solrDocument :solrDocumentList){
            RECRUITmodel recruit=new RECRUITmodel();
            long RECRUITID= (long) solrDocument.getFieldValue("RECRUITID");
            recruit.setRECRUITID(Math.toIntExact(RECRUITID));
            long USERSID= (long) solrDocument.getFieldValue("USERSID");
            recruit.setUSERSID(Math.toIntExact(USERSID));
            recruit.setPOSITION((String) solrDocument.getFieldValue("POSITION"));
            recruit.setSALARY((String) solrDocument.getFieldValue("SALARY"));
            recruit.setEDUCATION((String) solrDocument.getFieldValue("EDUCATION"));
            recruit.setWORKAREA((String) solrDocument.getFieldValue("WORKAREA"));
            recruit.setNUMS((String) solrDocument.getFieldValue("NUMS"));
            recruit.setHIGHLIGHT((String) solrDocument.getFieldValue("HIGHLIGHT"));
            recruit.setLINKMAN((String) solrDocument.getFieldValue("LINKMAN"));
            recruit.setPHONE((String) solrDocument.getFieldValue("PHONE"));
            recruit.setDESCRIBE((String) solrDocument.getFieldValue("DESCRIBE"));
            recruit.setWORKBACKGROUND((String) solrDocument.getFieldValue("WORKBACKGROUND"));
            Date time= (Date) solrDocument.getFieldValue("TIME");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String timeString=sdf.format(time);

            Date stime= (Date) solrDocument.getFieldValue("STARTTIME");
            String stimeString=sdf.format(stime);


            Date etime= (Date) solrDocument.getFieldValue("ENDTIME");
            String etimeString=sdf.format(etime);
            recruit.setTIME(timeString);
            recruit.setSTARTTIME(stimeString);
            recruit.setENDTIME(etimeString);
            recruitList.add(recruit);
        }
        if (recruitList.size()>0){
            System.out.println(recruitList.get(0).getRECRUITID());
            searchResult.setObjects(recruitList);
            searchResult.setResultCount(recruitList.size());
        }
//        System.out.println(articleList.get(0).getTITLE());

        return searchResult;
    }
    public String Searchid(SolrQuery query) throws IOException, SolrServerException {
        List< RECRUITmodel> recruitList=new ArrayList<RECRUITmodel>();
        QueryResponse solrResponse=solrClient.query(query);
        SolrDocumentList solrDocumentList= solrResponse.getResults();
        String id=null;
        for (SolrDocument solrDocument :solrDocumentList){
            id= (String) solrDocument.get(id);


        }
        return id;

    }
    public void delete(String id) throws IOException, SolrServerException {
        solrClient.deleteById(id);
        solrClient.commit();
    }

}
