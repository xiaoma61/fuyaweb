package com.fuya.fuyaweb.IndexyuesaoController;


import com.fuya.ActiveMQ.service.ProductService;
import com.fuya.Configuration.DateJsonValueProcessor;
import com.fuya.fuyadao.entity.SKILL;
import com.fuya.fuyadao.entity.YUESAOOTHERPROVE;
import com.fuya.fuyadao.entity.YUESOBASICINFO;
import com.fuya.fuyadao.model.ODERSEMPCommentMSG;
import com.fuya.fuyadao.model.PROVEINFOANDBAISINFO;
import com.fuya.fuyadao.model.Yuesaolistyuesaomodel;
import com.fuya.fuyaservice.*;
import com.fuya.fuyasolr.Solr.service.YUESOBASICINFOSolrservice;
import com.fuya.fuyautil.JpaPageHelperUtil;
import com.fuya.fuyautil.TypeUtil;
import com.github.pagehelper.PageInfo;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.jms.Queue;
import javax.jms.Topic;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

@Controller
/**
 * 完成
 */
public class YuesaoindexController {

    @Autowired
    private Queue queue;
    @Autowired
    private Topic topic;
    @Autowired
    private ProductService productService;
    @Autowired
    private YUESOBASICINFOSolrservice yuesobasicinfoSolrservice;
    @Autowired
    YUESOBASICINFOService yuesobasicinfoService;
    @Autowired
    USERService userService;

    @Autowired
    PROVEINFOService proveinfoService;
    @Autowired
    SKILLService skillService;
    @Autowired
    YUESAOOTHERPROVEService yuesaootherproveService;
    @Autowired
    ORDERSService ordersService;
    @RequestMapping("/fuyayuesaoindex/search/index")//名字
    @ResponseBody
    @CrossOrigin
    public  JSONObject Searchname (@RequestParam(name="name",defaultValue = "null")String name,
                                   @RequestParam(name = "workarea",defaultValue = "null")String workarea,
                                   @RequestParam(name = "type",defaultValue = "1")int type,
                                   @RequestParam(name = "minwages",defaultValue = "null")String minwages,
                                   @RequestParam(name = "maxwages",defaultValue = "null")String maxwages,
                                   @RequestParam(name = "nativeplace",defaultValue = "null")String nativeplace,
                                   @RequestParam(name = "minage" ,defaultValue = "null")String  minage,
                                   @RequestParam(name = "maxage" ,defaultValue = "null")String  maxage,
                                   @RequestParam(name = "start",defaultValue = "0") int start,
                                   @RequestParam(name = "rows",defaultValue = "10") int rows) throws IOException, SolrServerException {

        Page<YUESOBASICINFO> yuesobasicinfos=yuesobasicinfoService.query(name, workarea,  minwages, maxwages,type, nativeplace, minage, maxage, start, rows,3);
        Map<String,Object> msg=new HashMap<>();
        msg.put("msg", yuesobasicinfos);
        return JSONObject.fromObject(msg);

    }
    @RequestMapping("/fuyayuesaoindex/search/name/keyword")//名字--关键词
    @ResponseBody
    @CrossOrigin
    public  JSONObject Searchnamekeyword (@RequestParam(name="name")String name) throws IOException, SolrServerException {
        Page<YUESOBASICINFO>yuesobasicinfos=yuesobasicinfoService.query(name, "null", "null", "null", 1, "null", "null", "null", 0, 10,3);
        List<YUESOBASICINFO>yuesobasicinfoList=yuesobasicinfos.getContent();
        HashSet<String> hashSet=new HashSet<>();
        if (yuesobasicinfos!=null){
        for (YUESOBASICINFO yuesobasicinfo:yuesobasicinfoList){

            if (userService.findByID(yuesobasicinfo.getUSERSID())!=null&&userService.findByID(yuesobasicinfo.getUSERSID()).getTYPE()==3){
                if (hashSet.size()<5){
                    hashSet.add(yuesobasicinfo.getNAME());
                }
            }

        }
        }
        Map<String,Object> msg=new HashMap<>();
        msg.put("msg", hashSet);
        return JSONObject.fromObject(msg);


    }



    @RequestMapping("/fuyayuesaoindex/personalinfo")
    @ResponseBody
    public JSONObject personallist(HttpServletRequest request, HttpServletResponse response,int id){
        response.setHeader("Access-Control-Allow-Origin", "*");

        //查找
        List<PROVEINFOANDBAISINFO> objectList=proveinfoService.findPROVEINFOByAndYUESAOBASICINFOByUSERSID(id);
        Map<String,Object> msg=new HashMap<>();
        msg.put("msg",objectList);
        return JSONObject.fromObject(msg);
    }


    //技巧

    @RequestMapping("/fuyayuesaoindex/skillinfo")
    @ResponseBody
    public JSONObject skillinfo(HttpServletRequest request,HttpServletResponse response,int id){
        response.setHeader("Access-Control-Allow-Origin", "*");
        List<SKILL> skillList=skillService.findByUSERID(id,1);
        List<SKILL> skillList1=skillService.findByUSERID(id,2);
        skillList.addAll(skillList1);
        Map<String,Object> msg=new HashMap<>();
        msg.put("msg",skillList);
        return JSONObject.fromObject(msg);
    }


    @RequestMapping("/fuyayuesaoindex/proveinfo")
    @ResponseBody
    public JSONObject proveinfo(HttpServletRequest request,HttpServletResponse response,int id){
        response.setHeader("Access-Control-Allow-Origin", "*");
        List<YUESAOOTHERPROVE>yuesaootherproveList=yuesaootherproveService.findByUSERID(id);
        Map<String,Object> msg=new HashMap<>();
        msg.put("msg",yuesaootherproveList);
        return JSONObject.fromObject(msg);
    }

    @RequestMapping("/fuyayuesaoindex/comments")
    @ResponseBody
    public JSONObject comment(HttpServletRequest request,HttpServletResponse response,int id){
        response.setHeader("Access-Control-Allow-Origin", "*");
        List<Object> objectList=ordersService.findCommentsByTOID(id);
        Map<String,Object> msg=new HashMap<>();
        msg.put("msg",objectList);
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.registerJsonValueProcessor(java.sql.Date.class, new DateJsonValueProcessor("yyyy-MM-dd HH:mm:ss"));
        return JSONObject.fromObject(msg,jsonConfig);
    }

}
