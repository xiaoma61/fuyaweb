package com.fuya.ActiveMQ.comsumer;

import com.fuya.fuyasolr.Solr.service.COMPANYBASICINFOSolrService;
import com.fuya.fuyasolr.Solr.service.USERSSolrservice;
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

    @JmsListener(destination = "zh-topic")
    @SendTo("return-queue")
    public String receiveQueue(String id) throws IOException, SolrServerException {
        //实现solr插入users
        usersSolrservice.addUSER(Integer.parseInt(id));
        companybasicinfoSolrService.addCOMPANYBASICINFO(Integer.parseInt(id));

        return "Consumer2收到!";
    }


}
