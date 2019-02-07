package com.fuya.fuyaweb.yusaoController;

import com.fuya.Redis.Util.RedisUtil;
import com.fuya.fuyadao.entity.MSG;
import com.fuya.fuyadao.entity.MSGInfo;
import com.fuya.fuyadao.entity.MSGinfodetail;
import com.fuya.fuyaservice.MSGService;
import com.fuya.fuyasolr.Solr.service.MSGSolrService;
import com.fuya.fuyasolr.Solr.service.USERSSolrservice;
import com.fuya.fuyautil.TimeUtil;
import net.sf.json.JSONObject;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Controller

public class TalkingController {

    private ScheduledExecutorService batchTaskPool = Executors.newScheduledThreadPool(2);
    @Autowired
    RedisUtil redisUtil;
    @Autowired
    MSGService msgService;
    @Autowired
    MSGSolrService msgSolrService;
    @Autowired
    USERSSolrservice usersSolrservice;
    //实现聊天功能
    //生产者
    @RequestMapping("/fuyayusao/talkingsend")
    public String talkingsend(@RequestParam(name = "id")String id, @RequestParam(name = "msg")String msg, HttpServletRequest request) throws IOException, SolrServerException {

        //获取
        java.sql.Date date=TimeUtil.getsqldate(new Date());
        HttpSession session=request.getSession();
        String fromid= (String) session.getAttribute("id");
        String key=id;


        //存储到数据库
        MSG msg1=new MSG();
        msg1.setFROMID(Integer.parseInt(fromid));
        msg1.setTOID(Integer.parseInt(id));
        //SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        msg1.setTIME(date);
        msg1.setMSG(msg);
        msgService.save(msg1);
        String value=fromid+":"+msg+":"+msg1.getID()+":"+date;
        redisUtil.lpush(key,value);
        msgSolrService.addMSG(msg1.getID());

        return "";
    }
    //消费者
    @RequestMapping("/fuyayusao/talking")
    public JSONObject talkingget(@RequestParam(name = "id")String toid) throws IOException, SolrServerException {
        boolean flag=true;
        List<MSGinfodetail>msGinfodetails=new ArrayList<>();
//        Runnable runnable= () -> {
             while (flag){
                List<String>msgs=redisUtil.brpop(0,toid);

                if (msgs!=null){
                    MSGinfodetail msGinfodetail=new MSGinfodetail();
                    flag=true;
                    String key=msgs.get(0);
                    String msg=msgs.get(1);
                    String fromidandmsg[]=msg.split(":");
                    String fromid=fromidandmsg[0];
                    msg=fromidandmsg[1];
                    String id=fromidandmsg[2];
                    String date=fromidandmsg[3];
                    String name=null;
                    //查找用户名，msg，time
                    try {
                        name=usersSolrservice.searchbyid(Integer.parseInt(key));
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (SolrServerException e) {
                        e.printStackTrace();
                    }


                    msGinfodetail.setDate(date);
                    msGinfodetail.setMsg(msg);
                    msGinfodetail.setName(name);
                    msGinfodetail.setId(Integer.parseInt(key));

                    msGinfodetails.add(msGinfodetail);
                    //把所有的消息包装好
                    //修改solr将type信息修改为2
                    try {
                        msgSolrService.updatebytall(Integer.parseInt(id),2);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (SolrServerException e) {
                        e.printStackTrace();
                    }
                }else {
                    flag=false;
                }

            }
//        };
//        batchTaskPool.scheduleWithFixedDelay(runnable, 3,5, TimeUnit.SECONDS);
        return JSONObject.fromObject(msGinfodetails);
    }
    //系统通知
    @RequestMapping("/fuyayusao/talkingsystem")
    public String talkingsystem(HttpServletRequest request) throws IOException, SolrServerException {
        //得到通知列表
        HttpSession session=request.getSession();
        String toid= (String) session.getAttribute("id");
        //未读
        List<MSGInfo>msgInfoList=msgSolrService.findbytoid(Integer.parseInt(toid),1);


        return "";
    }

    //最近信息

}
