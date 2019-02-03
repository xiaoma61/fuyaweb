package com.fuya.ActiveMQ.comsumer;

import com.fuya.fuyadao.entity.YUESOBASICINFO;
import com.fuya.fuyaservice.YUESOBASICINFOService;
import com.fuya.fuyasolr.Solr.service.*;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class Consumer {

    @Autowired
    USERSSolrservice usersSolrservice;
    @Autowired
    COMPANYBASICINFOSolrService companybasicinfoSolrService;
    @Autowired
    COMPANYINFOSolrService companyinfoSolrService;
    @Autowired
    YUESOBASICINFOSolrservice yuesobasicinfoSolrservice;
    @Autowired
    YUESOBASICINFOService yuesobasicinfoService;
    @Autowired
    PROVEINFOSolrService proveinfoSolrService;
    @Autowired
    COLLECTIONSSolrService collectionsSolrService;




    @JmsListener(destination = "zh-topic")
    @SendTo("return-queue")
    public String receiveQueue(String id) throws IOException, SolrServerException {
        String[] text=id.split(":");
        //实现solr插入users
        usersSolrservice.addUSER(Integer.parseInt(text[1]));

        if (text[0].equals("yuesobasicinfo")){
            //录入月嫂信息
            yuesobasicinfoSolrservice.addYUESOBASICINFO(Integer.parseInt(text[1]));
            proveinfoSolrService.addPROVEINFO(Integer.parseInt(text[1]));


        }
        if (text[0].equals("companyregister")){
            companybasicinfoSolrService.addCOMPANYBASICINFO(Integer.parseInt(text[1]));
            companyinfoSolrService.addCOMPANYINFO(Integer.parseInt(text[1]));
        }

        if (text[0].equals("collections-delete")){
            collectionsSolrService.delete(Integer.parseInt(text[1]));
        //    collectionsSearchdao.delete(query);

        }
        if (text[0].equals("collections")){
            collectionsSolrService.addCOLLECTIONS(Integer.parseInt(text[1]));

        }

        return "Consumer2收到!";
    }


}
