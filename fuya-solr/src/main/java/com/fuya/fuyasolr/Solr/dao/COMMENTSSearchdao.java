package com.fuya.fuyasolr.Solr.dao;

import com.fuya.fuyadao.entity.COMMENTS;
import com.fuya.fuyaservice.COMMENTSService;
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

@Repository
public class COMMENTSSearchdao {
    @Autowired
    COMMENTSService commentsService;
    @Autowired
    SolrClient solrClient;

    //添加
    public void addCOMMENTS(int id) throws IOException, SolrServerException {


        COMMENTS comments=commentsService.findbyid(id);

//        SolrInputDocument solrInputDocument=new SolrInputDocument();
//        solrInputDocument.addField("commentsCONTENT",comments.getCONTENT());
//        solrInputDocument.addField("commentsLEVELS",comments.getLEVELS());
//        solrInputDocument.addField("commentsUSERID",comments.getUSERID());
//        solrInputDocument.addField("commentsORDERID",comments.getORDERID());
//        solrInputDocument.addField("commentsID",comments.getID());
//        solrClient.add(solrInputDocument);
        solrClient.addBean(comments);
        solrClient.commit();
    }
    //查找信息
    public List<COMMENTS>  search(SolrQuery solrQuery) throws IOException, SolrServerException {
        List<COMMENTS>commentsList=new ArrayList<>();
        QueryResponse solrResponse=solrClient.query(solrQuery);
        SolrDocumentList solrDocumentList= solrResponse.getResults();
        for (SolrDocument solrDocument :solrDocumentList){
            COMMENTS comments=new COMMENTS();

            comments.setCOMMENTSID((Integer) solrDocument.getFieldValue("COMMENTSID"));
            comments.setORDERID((Integer) solrDocument.getFieldValue("ORDERID"));
            comments.setCONTENT((String) solrDocument.getFieldValue("CONTENT"));
            comments.setLEVELS((Integer) solrDocument.getFieldValue("LEVELS"));
            commentsList.add(comments);

        }


        return commentsList;

    }

}
