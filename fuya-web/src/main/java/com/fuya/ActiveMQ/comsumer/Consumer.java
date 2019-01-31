package com.fuya.ActiveMQ.comsumer;

import com.fuya.fuyadao.entity.YUESOBASICINFO;
import com.fuya.fuyaservice.YUESOBASICINFOService;
import com.fuya.fuyasolr.Solr.service.COMPANYBASICINFOSolrService;
import com.fuya.fuyasolr.Solr.service.COMPANYINFOSolrService;
import com.fuya.fuyasolr.Solr.service.USERSSolrservice;
import com.fuya.fuyasolr.Solr.service.YUESOBASICINFOSolrservice;
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


    @JmsListener(destination = "zh-topic")
    @SendTo("return-queue")
    public String receiveQueue(String id) throws IOException, SolrServerException {
        //实现solr插入users
        usersSolrservice.addUSER(Integer.parseInt(id));
        //是月嫂还是公司
        YUESOBASICINFO yuesobasicinfo=yuesobasicinfoService.findByUSERSID(Integer.parseInt(id));
        if (yuesobasicinfo!=null){
            //月嫂插入
            yuesobasicinfoSolrservice.addYUESOBASICINFO(Integer.parseInt(id));
        }else {

            //公司插入
            companybasicinfoSolrService.addCOMPANYBASICINFO(Integer.parseInt(id));
            companyinfoSolrService.addCOMPANYINFO(Integer.parseInt(id));
        }

        return "Consumer2收到!";
    }


}
