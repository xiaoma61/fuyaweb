package com.fuya.fuyaweb.IndexyuesaoController;


import com.fuya.ActiveMQ.service.ProductService;
import com.fuya.fuyadao.entity.YUESOBASICINFO;
import com.fuya.fuyadao.model.Yuesaolistyuesaomodel;
import com.fuya.fuyaservice.USERService;
import com.fuya.fuyaservice.YUESOBASICINFOService;
import com.fuya.fuyasolr.Solr.service.YUESOBASICINFOSolrservice;
import com.fuya.fuyautil.JpaPageHelperUtil;
import com.fuya.fuyautil.TypeUtil;
import com.github.pagehelper.PageInfo;
import net.sf.json.JSONObject;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.jms.Queue;
import javax.jms.Topic;
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

    @RequestMapping("/fuyayuesaoindex/search/index")//名字
    @ResponseBody
    public  JSONObject Searchname (@RequestParam(name="name",defaultValue = "null")String name,
                                   @RequestParam(name = "workarea",defaultValue = "null")String workarea,
                                   @RequestParam(name = "type",defaultValue = "1")int type,
                                   @RequestParam(name = "minwages",defaultValue = "null")String minwages,
                                   @RequestParam(name = "maxwages",defaultValue = "null")String maxwages,
                                   @RequestParam(name = "nativeplace",defaultValue = "null")String nativeplace,
                                   @RequestParam(name = " minage" ,defaultValue = "null")String  minage,
                                   @RequestParam(name = " maxage" ,defaultValue = "null")String  maxage,
                                   @RequestParam(name = "start",defaultValue = "0") int start,
                                   @RequestParam(name = "rows",defaultValue = "10") int rows) throws IOException, SolrServerException {

        Page<YUESOBASICINFO> yuesobasicinfos=yuesobasicinfoService.query(name, workarea,  minwages, maxwages,type, nativeplace, minage, maxage, start, rows);
        List<Yuesaolistyuesaomodel>yuesobasicinfoList=new ArrayList<>();
        if (yuesobasicinfos!=null){
            for (YUESOBASICINFO yuesobasicinfo: yuesobasicinfos.getContent()){


                if (userService.findByID(yuesobasicinfo.getUSERSID())!=null&&userService.findByID(yuesobasicinfo.getUSERSID()).getTYPE()==3){
                    Yuesaolistyuesaomodel yuesaolistyuesaomodel=new Yuesaolistyuesaomodel();
                    yuesaolistyuesaomodel.setAGE(String.valueOf(yuesobasicinfo.getAGE()));
                    yuesaolistyuesaomodel.setTYPE(String.valueOf(TypeUtil.getTypeUtil(yuesobasicinfo.getTYPE())));
                    yuesaolistyuesaomodel.setNATIVEPLACE(yuesobasicinfo.getNATIVEPLACE());
                    yuesaolistyuesaomodel.setWAGES(String.valueOf(yuesobasicinfo.getWAGES()));
                    yuesaolistyuesaomodel.setPHOTO(yuesobasicinfo.getPHOTO());
                    yuesaolistyuesaomodel.setNAME(yuesobasicinfo.getNAME());
                    yuesaolistyuesaomodel.setLEVELS(yuesobasicinfo.getLEVELS());
                    yuesaolistyuesaomodel.setUSERSID(yuesobasicinfo.getUSERSID());
                    yuesobasicinfoList.add(yuesaolistyuesaomodel);
                }



            }
            List<PageInfo>problemAnswerPageInfo= JpaPageHelperUtil.SetStartPage(yuesobasicinfoList,start+1,rows);

            problemAnswerPageInfo.get(0).setTotal(yuesobasicinfos.getTotalElements());
            problemAnswerPageInfo.get(0).setPageSize(yuesobasicinfos.getTotalPages());
            problemAnswerPageInfo.get(0).setIsFirstPage(yuesobasicinfos.isFirst());
            problemAnswerPageInfo.get(0).setIsLastPage(yuesobasicinfos.isLast());
            problemAnswerPageInfo.get(0).setStartRow(yuesobasicinfos.getNumber());
            problemAnswerPageInfo.get(0).setEndRow(yuesobasicinfos.getNumberOfElements());

            Map<String,Object> msg=new HashMap<>();
            msg.put("msg", problemAnswerPageInfo);
            return JSONObject.fromObject(msg);
        }

        Map<String,Object> msg=new HashMap<>();
        msg.put("msg", "暂无信息");
        return JSONObject.fromObject(msg);

    }
    @RequestMapping("/fuyayuesaoindex/search/name/keyword")//名字--关键词
    @ResponseBody
    public  JSONObject Searchnamekeyword (@RequestParam(name="name")String name) throws IOException, SolrServerException {
        Page<YUESOBASICINFO>yuesobasicinfos=yuesobasicinfoService.query(name, "null", "null", "null", 1, "null", "null", "null", 0, 10);
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






}
