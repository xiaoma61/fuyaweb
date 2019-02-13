package com.fuya.fuyasolr.Solr.serviceImpl;

import com.fuya.fuyadao.entity.MSG;
import com.fuya.fuyadao.model.MSGInfo;
import com.fuya.fuyasolr.Solr.dao.MSGSearchdao;
import com.fuya.fuyasolr.Solr.service.MSGSolrService;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
public class MSGSolrServiceImpl implements MSGSolrService {
    @Autowired
    MSGSearchdao msgSearchdao;
    @Override
    public void addMSG(int id) throws IOException, SolrServerException {
        msgSearchdao.addMSG(id);

    }

//谁给谁多少发送信息
    @Override
    public List<MSGInfo> findbytoid(int toid, int type) throws IOException, SolrServerException {
        SolrQuery solrQuery=new SolrQuery();
        solrQuery.set("msgTOID"+toid);
        List<MSGInfo>msgInfoList=new ArrayList<>();
        HashSet<String> msgstoid=msgSearchdao.Search(solrQuery,type);
        //获取fromid
        for (String fromid: msgstoid ){
            MSGInfo msgInfo=new MSGInfo();
            SolrQuery solrQuery1=new SolrQuery();
            solrQuery1.set("msgTOID"+toid);
            solrQuery1.set("msgFROMID"+fromid);
            solrQuery1.setSort("msgTIME",SolrQuery.ORDER.desc);
//            solrQuery1.setStart(0);
//            solrQuery1.setRows(1);
            List<MSG>msgList=msgSearchdao.Searchby(solrQuery1,type);
            //来自谁，什么话，多少句
            msgInfo.setFromid(Integer.parseInt(fromid));
            msgInfo.setMsg(msgList.get(0).getMSG());
            msgInfo.setNums(msgstoid.size());
            msgInfoList.add(msgInfo);

        }

        return msgInfoList;
    }

    @Override
    public List<MSGInfo> updatebytall(int msgid, int type) throws IOException, SolrServerException {
        SolrQuery solrQuery=new SolrQuery();
        solrQuery.set("msgID:",msgid);
        String id=msgSearchdao.Searchid(solrQuery);
        //开始更新
        //solrQuery.set("msgtype:",2);
        msgSearchdao.update(type,id);


       // solrQuery.set("msgTOID:"+toid);

        return null;
    }
}
