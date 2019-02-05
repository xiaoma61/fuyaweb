package com.fuya.fuyaweb.yusaoController;

import com.fuya.Redis.Util.RedisUtil;
import com.fuya.fuyadao.entity.MSG;
import com.fuya.fuyaservice.MSGService;
import com.fuya.fuyautil.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller

public class TalkingController {

    @Autowired
    RedisUtil redisUtil;
    @Autowired
    MSGService msgService;
    //实现聊天功能
    //生产者
    @RequestMapping("/fuyayusao/talkingsend")
    public String talkingsend(@RequestParam(name = "id")String id, @RequestParam(name = "msg")String msg, HttpServletRequest request){

        //获取
        HttpSession session=request.getSession();
        String fromid= (String) session.getAttribute("id");
        String key=id;
        String value=fromid+":"+msg;
        redisUtil.lpush(key,value);
        //存储到数据库
        MSG msg1=new MSG();
        msg1.setFROMID(Integer.parseInt(fromid));
        msg1.setTOID(Integer.parseInt(id));
        //SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        java.sql.Date date=TimeUtil.getsqldate(new Date());
        msg1.setTIME(date);
        msg1.setMSG(msg);
        msgService.save(msg1);

        return "";
    }
    //消费者
    @RequestMapping("/fuyayusao/talking")
    public String talkingget(@RequestParam(name = "id")String id){

        List<String>msgs=redisUtil.brpop(0,id);
        String key=msgs.get(0);
        String msg=msgs.get(1);
        String fromidandmsg[]=msg.split(":");
        String fromid=fromidandmsg[0];
        msg=fromidandmsg[1];
        //把所有的消息包装好


        return "";
    }
}
