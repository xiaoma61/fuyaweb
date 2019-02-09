package com.fuya.fuyaweb.yusaoController;

import com.fuya.fuyadao.entity.*;
import com.fuya.fuyadao.model.ORDERSINFO;
import com.fuya.fuyadao.model.SKILLInfo;
import com.fuya.fuyadao.model.yuesaobasicinfoandcollection;
import com.fuya.fuyasolr.SearchResult.SearchResult;
import com.fuya.fuyasolr.Solr.service.*;
import net.sf.json.JSONObject;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class YuesaoMsgController {
    //根据id查找
    @Autowired
    YUESOBASICINFOSolrservice yuesobasicinfoSolrservice;
    @Autowired
    ORDERSSolrService ordersSolrService;
    @Autowired
    EMPLOYERINFORMATIONSolrService employerinformationSolrService;
    @Autowired
    COMMENTSSolrService commentsSolrService;
    @Autowired
    SKILLSolrService skillSolrService;
    @Autowired
    YUESAOOTHERPROVESolrService yuesaootherproveSolrService;
    @Autowired
    COLLECTIONSSolrService collectionsSolrService;
    @Autowired
    USERSSolrservice usersSolrservice;






    @RequestMapping("/fuyayusao/search/id")//名字--关键词
    @ResponseBody
    public  JSONObject Searchbyid (@RequestParam(name ="id") int id,
                                   HttpServletRequest request,@RequestParam(name = "start",defaultValue = "0")int start,@RequestParam(name = "rows",defaultValue = "5")int rows) throws IOException, SolrServerException {
        //基本信息
        YUESOBASICINFO yuesobasicinfo=yuesobasicinfoSolrservice.searchbyid(id);

        yuesaobasicinfoandcollection yuesaobasicinfoandcollection=new yuesaobasicinfoandcollection();

        if (request.getSession().getAttribute("username")!=null){
            //查找id
            String name= (String) request.getSession().getAttribute("username");
            List<USERS>usersList=usersSolrservice.searchbyusername(name);
            if (usersList!=null){
                USERS users=usersList.get(0);
                //查看是否收藏
                List<String> collections=collectionsSolrService.Searchbyfromidandtoid(users.getID(),id);
                if (collections!=null){
                    yuesaobasicinfoandcollection.setIscollection(true);
                }else {
                    yuesaobasicinfoandcollection.setIscollection(false);
                }

            }

        }

        yuesaobasicinfoandcollection.setYuesobasicinfo(yuesobasicinfo);
        JSONObject jsonResult = JSONObject.fromObject(yuesaobasicinfoandcollection);
        return  jsonResult;

    }
    @RequestMapping("/fuyayusao/search/id/orders")//id--关键词
    @ResponseBody
    public  JSONObject Searchordersbyid (@RequestParam(name ="id") int id,@RequestParam(name = "start",defaultValue = "0")int start,@RequestParam(name = "rows",defaultValue = "5")int rows) throws IOException, SolrServerException {
        //基本信息
        YUESOBASICINFO yuesobasicinfo=yuesobasicinfoSolrservice.searchbyid(id);
        //获取月嫂的id
        int usersid=yuesobasicinfo.getUSERSID();
        //订单号信息
        List<ORDERS> ordersList=ordersSolrService.Searchbytoid(usersid,start,rows);
        List<ORDERSINFO>ordersinfoList=new ArrayList<>();


        for (ORDERS orders :ordersList){


            //得到客户评价
            List<COMMENTS> comments=commentsSolrService.searchbyorderid(orders.getID());
            //客户信息
            EMPLOYERINFORMATION employerinformation=employerinformationSolrService.searchbyorderid(orders.getID());
            ORDERSINFO ordersinfo=new ORDERSINFO() ;
            ordersinfo.setComments(comments);
            ordersinfo.setEmployerinformation(employerinformation);
            ordersinfo.setOrders(orders);
            ordersinfoList.add(ordersinfo);
        }

        SearchResult searchResult=new SearchResult();
        searchResult.setObjects(ordersinfoList);
        searchResult.setResultCount(ordersinfoList.size());
        int totalpages=0;
        if (ordersinfoList.size()%rows!=0){
            totalpages=(ordersinfoList.size()/rows)+1;
        }else {
            totalpages=(ordersinfoList.size()/rows);
        }
        searchResult.setTotalPage(totalpages);
        //用户评价实现分页

        //服务技能

        //相关证明实现分页
        JSONObject jsonResult = JSONObject.fromObject(searchResult);
        return  jsonResult;
    }

    @RequestMapping("/fuyayusao/search/id/skill")//id--技能
    @ResponseBody
    public  JSONObject Searchskillbyid (@RequestParam(name ="id") int id) throws IOException, SolrServerException {
        //基本信息
        YUESOBASICINFO yuesobasicinfo=yuesobasicinfoSolrservice.searchbyid(id);
        //获取月嫂的id
        int usersid=yuesobasicinfo.getUSERSID();


        //服务技能
        List<SKILL>skillListbady=skillSolrService.searchbyuseridandtype(usersid,1);
        List<SKILL>skillListmon=skillSolrService.searchbyuseridandtype(usersid,2);

        SKILLInfo skillInfo=new SKILLInfo();
        skillInfo.setSkillbaby(skillListbady);
        skillInfo.setSkillmon(skillListmon);


        //相关证明实现分页
        JSONObject jsonResult = JSONObject.fromObject(skillInfo);
        return  jsonResult;
    }
    @RequestMapping("/fuyayusao/search/id/prove")//id--证明
    @ResponseBody
    public  JSONObject Searchprovebyid (@RequestParam(name ="id") int id, @RequestParam(name = "start")int start,
                                        @RequestParam(name = "rows")int rows) throws IOException, SolrServerException {
        //基本信息
        YUESOBASICINFO yuesobasicinfo=yuesobasicinfoSolrservice.searchbyid(id);
        //获取月嫂的id
        int usersid=yuesobasicinfo.getUSERSID();
        //是否被收藏




        SearchResult searchResult=new SearchResult();
        searchResult=yuesaootherproveSolrService.searchbyuserid(usersid,start,rows);
        int totalpages=0;
        if (searchResult.getObjects().size()%rows!=0){
            totalpages=(searchResult.getObjects().size()/rows)+1;
        }else {
            totalpages=(searchResult.getObjects().size()/rows);
        }
        searchResult.setTotalPage(totalpages);

        //相关证明实现分页
        JSONObject jsonResult = JSONObject.fromObject(searchResult);
        return  jsonResult;
    }
}
