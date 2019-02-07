package com.fuya.fuyasolr.Solr.service;

import com.fuya.fuyadao.entity.MSG;
import com.fuya.fuyadao.entity.MSGInfo;
import org.apache.solr.client.solrj.SolrServerException;

import java.io.IOException;
import java.util.List;

public interface MSGSolrService {
    void  addMSG(int id) throws IOException, SolrServerException;
//    List<MSGInfo> findbytoid(int toid) throws IOException, SolrServerException;

    //谁给谁多少发送信息
    List<MSGInfo> findbytoid(int toid, int type) throws IOException, SolrServerException;
    List<MSGInfo> updatebytall(int id,  int type) throws IOException, SolrServerException;
}
