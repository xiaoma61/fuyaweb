package com.fuya.ActiveMQ.comsumer;


import com.fuya.Redis.Util.RedisUtil;
import com.fuya.fuyadao.entity.COMPANYYUESAO;
import com.fuya.fuyadao.entity.PROVEINFO;
import com.fuya.fuyadao.entity.YUESOBASICINFO;
import com.fuya.fuyadao.model.YUESAOINFO;
import com.fuya.fuyaservice.COMPANYYUESAOService;
import com.fuya.fuyaservice.PROVEINFOService;
import com.fuya.fuyaservice.YUESOBASICINFOService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class consumer1 {
    @Autowired
    COMPANYYUESAOService companyyuesaoService;
    @Autowired
    YUESOBASICINFOService yuesobasicinfoService;
    @Autowired
    PROVEINFOService proveinfoService;
    @Autowired
    RedisUtil redisUtil;


    @JmsListener(destination = "zh-topic")
    public void receiveQueue(String text) {
        System.out.println(text+"");

    }
}
