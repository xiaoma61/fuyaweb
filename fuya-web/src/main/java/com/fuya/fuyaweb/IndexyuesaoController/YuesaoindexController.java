package com.fuya.fuyaweb.IndexyuesaoController;



import com.fuya.ActiveMQ.service.ProductService;

import com.fuya.fuyasolr.SearchResult.SearchResult;
import com.fuya.fuyasolr.Solr.service.YUESOBASICINFOSolrservice;
import net.sf.json.JSONObject;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.jms.Queue;
import javax.jms.Topic;
import java.io.IOException;

@Controller

public class YuesaoindexController {

    @Autowired
    private Queue queue;
    @Autowired
    private Topic topic;
    @Autowired
    private ProductService productService;
    @Autowired
    private YUESOBASICINFOSolrservice yuesobasicinfoSolrservice;

    @RequestMapping("/fuyayuesao/search/index")//名字
    @ResponseBody
    public  JSONObject Searchname (@RequestParam(name="name")String name,@RequestParam(name="page",defaultValue = "0")int page,
                              @RequestParam(name = "workarea",defaultValue = "null")String workarea,
                              @RequestParam(name = "type",defaultValue = "null")String type,
                              @RequestParam(name = "wages",defaultValue = "null")String minwages,
                              @RequestParam(name = "wages",defaultValue = "null")String maxwages,
                              @RequestParam(name = "nativeplace",defaultValue = "null")String nativeplace,
                              @RequestParam(name = " age" ,defaultValue = "null")String  age,
                              @RequestParam(name = "rows",defaultValue = "10") int rows) throws IOException, SolrServerException {
        SearchResult result=yuesobasicinfoSolrservice.search(name,workarea,type,minwages,maxwages,nativeplace,age,page,rows);
        JSONObject jsonResult = JSONObject.fromObject(result);
        System.out.println(jsonResult);
        return jsonResult;

    }
    @RequestMapping("/fuyayuesao/search/name/keyword")//名字--关键词
    @ResponseBody
    public  JSONObject Searchnamekeyword (@RequestParam(name="name")String name) throws IOException, SolrServerException {
        if (name==null||name.equals("")){
            return null;
        }
        SearchResult result=yuesobasicinfoSolrservice.search(name);
        JSONObject jsonResult = JSONObject.fromObject(result);
        return jsonResult;




    }






}
