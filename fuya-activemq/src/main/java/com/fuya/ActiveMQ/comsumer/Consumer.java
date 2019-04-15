package com.fuya.ActiveMQ.comsumer;



import com.fuya.fuyadao.entity.EMPLOYERINFORMATION;
import com.fuya.fuyadao.entity.ORDERS;
import com.fuya.fuyadao.entity.SERVICECONTENT;
import com.fuya.fuyaservice.EMPLOYERINFORMATIONService;
import com.fuya.fuyaservice.ORDERSService;
import com.fuya.fuyaservice.SERVICECONTENTService;
import com.fuya.fuyaservice.YUESOBASICINFOService;
import com.fuya.fuyasolr.Solr.service.*;

import net.sf.json.JSONObject;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class Consumer {


    @Autowired
    SERVICECONTENTService servicecontentService;
    @Autowired
    ORDERSService ordersService;
    @Autowired
    EMPLOYERINFORMATIONService employerinformationService;




    @JmsListener(destination = "zh-topic")
    @SendTo("return-queue")
    public String receiveQueue(String text) throws IOException, SolrServerException {
        System.out.println(text);
        JSONObject jsonObject = JSONObject.fromObject(text);
        JSONObject servicecontentObject = jsonObject.getJSONObject("servicecontent");
       /* JSONObject ordersObject = jsonObject.getJSONObject("orders");*/
        JSONObject employerinformationObject = jsonObject.getJSONObject("employerinformation");
       /* ORDERS orders= (ORDERS) JSONObject.toBean(ordersObject,ORDERS.class);
        ordersService.save(orders);*/
        SERVICECONTENT servicecontent= (SERVICECONTENT) JSONObject.toBean(servicecontentObject ,SERVICECONTENT.class);
        servicecontentService.save(servicecontent);
        EMPLOYERINFORMATION employerinformation= (EMPLOYERINFORMATION) JSONObject.toBean(employerinformationObject,EMPLOYERINFORMATION.class);
        employerinformationService.save(employerinformation);

        return "success";
    }


}
