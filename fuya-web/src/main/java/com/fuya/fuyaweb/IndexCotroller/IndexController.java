package com.fuya.fuyaweb.IndexCotroller;

import com.alibaba.druid.support.json.JSONUtils;
import com.fuya.Redis.Util.RedisUtil;
import com.fuya.fuyadao.entity.ARTICLE;
import com.fuya.fuyadao.entity.COMMENTS;
import com.fuya.fuyadao.entity.COMPANYBASICINFO;
import com.fuya.fuyadao.entity.YUESOBASICINFO;
import com.fuya.fuyaservice.ARTICLEService;
import com.fuya.fuyaservice.COMMENTSService;
import com.fuya.fuyaservice.COMPANYBASICINFOService;
import com.fuya.fuyaservice.YUESOBASICINFOService;
import com.fuya.fuyautil.BodyContentUtil;
import com.fuya.fuyautil.BodyImageUtil;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
public class IndexController {

    private String companykey="COMPANYBASICINFO";

    private String yuesaokey="YUESOBASICINFO";

    private  String articlekey="ARTICLE";


    @Autowired
    private COMPANYBASICINFOService companybasicinfoService;
    @Autowired
    private YUESOBASICINFOService yuesobasicinfoService;
    @Autowired
    private COMMENTSService commentsService;
    @Autowired
    private ARTICLEService articleService;


    @Autowired
    RedisUtil redisUtil;
    @RequestMapping("/index")
    @ResponseBody
    public JSONObject index(Model model){
        //redis实现查找前6个
        //前6个企业
        Map<String,Object> msg=new HashMap<>();


        if (redisUtil.hasKey(companykey)){
            //存在key的情况下23

            String value=redisUtil.get(companykey);
            if (value!=null&&!value.equals("")){
                //当redis存在的时候直接取得value
               // return redisUtil.get(companykey);
                msg.put("companybasicinfo", value);
            }

        }else{
            List<COMPANYBASICINFO>companybasicinfoList=companybasicinfoService.findAlllimit();
            List<Map<String,Object>>companybasicinfoListmap=new ArrayList<>();
            for (COMPANYBASICINFO companybasicinfo:companybasicinfoList){
                Map<String,Object>map=new HashMap<>();
                //跳转到公司页面
                map.put("ID",companybasicinfo.getCOMPANYBASICINFOID());
                map.put("ADDRESS",companybasicinfo.getADDRESS());
                map.put("CORPORATENAME",companybasicinfo.getCORPORATENAME());
                map.put("INTRODUCE",companybasicinfo.getINTRODUCE());
                map.put("LEVELS",companybasicinfo.getLEVELS());
                map.put("USERID",companybasicinfo.getUSERID());

                companybasicinfoListmap.add(map);
            }
            String companybasicinfo = JSONUtils.toJSONString(companybasicinfoListmap);
            msg.put("companybasicinfo", companybasicinfo);
            redisUtil.set(companykey,companybasicinfo );


        }


        //前6个月嫂
        if (redisUtil.hasKey(yuesaokey)){
            System.out.println("adssssssss1");
            String value=redisUtil.get(yuesaokey);
            if (value!=null&&!value.equals("")){
                msg.put("yuesobasicinfoListmapJson", value); //添加到
            }

        }else {

           List<YUESOBASICINFO>yuesobasicinfoList=yuesobasicinfoService.findAlllimit();
           List<Map<String,Object>>yuesobasicinfoListmap=new ArrayList<>();

           for (YUESOBASICINFO yuesobasicinfo : yuesobasicinfoList){
               //封装连接---跳转到月嫂的页面
               int id=yuesobasicinfo.getUSERSID();
               List<COMMENTS> commentsList=commentsService.findByUSERID(id);
               if (commentsList!=null){
                   COMMENTS comments=commentsList.get(0);
                   Map<String,Object>map=new HashMap<>();
                   map.put("ID",yuesobasicinfo.getYUESOBASICINFOID());//评论
                   map.put("CONTENT",comments.getCONTENT());//评论
                   map.put("NAME",yuesobasicinfo.getNAME());// 名称
                   map.put("NATIVEPLACE",yuesobasicinfo.getNATIVEPLACE());//地点
                   map.put("WAGES",yuesobasicinfo.getWAGES());//工资
                   map.put("LEVELS",yuesobasicinfo.getLEVELS());//星级
                   map.put("AGE",yuesobasicinfo.getAGE());
                   map.put("WORKAREA",yuesobasicinfo.getWORKAREA());
                   yuesobasicinfoListmap.add(map);
               }


           }
           String yuesobasicinfoListmapJson=JSONUtils.toJSONString(yuesobasicinfoListmap);
            msg.put("yuesobasicinfoListmapJson", yuesobasicinfoListmapJson);
           redisUtil.set(yuesaokey,yuesobasicinfoListmapJson);

        }



        //6篇

        if (redisUtil.hasKey(articlekey)){
            //存在key的情况下

            String value=redisUtil.get(articlekey);
            if (value!=null&&!value.equals("")){
                //当redis存在的时候直接取得value
                // return redisUtil.get(companykey);
                msg.put("articleListmapJson", value);
            }

        }else{
            List<ARTICLE>articleList=articleService.findAlllimit(2);
            List<Map<String,Object>>articleListmap=new ArrayList<>();
            for (ARTICLE article : articleList ){
                Map<String,Object>map=new HashMap<>();
                //封装连接
                //跳转到文章页面
                map.put("ID",article.getARTICLEID());
                map.put("TITLE",article.getTITLE());
                String content=BodyContentUtil.GetContent(article.getCONTENT());


                map.put("CONTENT",content);
                //获取图片
                String Image= BodyImageUtil.GetIMage(article.getCONTENT());
                map.put("IMAGE",Image);
                articleListmap.add(map);

            }
            String articleListmapJson=JSONUtils.toJSONString(articleListmap);
            redisUtil.set(articlekey,articleListmapJson);
            msg.put("articleListmapJson", articleListmapJson);
        }




        return JSONObject.fromObject(msg);
    }
}
