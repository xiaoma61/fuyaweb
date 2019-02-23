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
    public void receiveQueue(String id) {
        String[] text=id.split(":");
        //实现redis插入
        if (text[0].equals("companyyuesao")){
                    COMPANYYUESAO companyyuesao= companyyuesaoService.findByCOMPANYID(Integer.parseInt(text[1]));
                    //存入数据库
//                    companyyuesao.getYUESAOID();
                    YUESOBASICINFO yuesobasicinfo =yuesobasicinfoService.findByUSERSID(companyyuesao.getYUESAOID());
                    List<PROVEINFO> proveinfo=proveinfoService.findByUSERSID(companyyuesao.getYUESAOID());
                    YUESAOINFO yuesaoinfo=new YUESAOINFO();
                    yuesaoinfo.setProveinfo(proveinfo);
                    yuesaoinfo.setYuesobasicinfo(yuesobasicinfo);
                    JSONObject object=JSONObject.fromObject(yuesaoinfo);
                    redisUtil.lpush(String.valueOf(companyyuesao.getCOMPANYID()), object.toString());


        }





        System.out.println("con:"+id);
    }
}
