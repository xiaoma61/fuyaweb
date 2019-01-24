package com.fuya.fuyaweb.yusaoController;



import com.fuya.ActiveMQ.service.ProductService;

import com.fuya.fuyasolr.SearchResult.SearchResult;
import com.fuya.fuyasolr.Solr.service.YUESOBASICINFOSolrservice;
import net.sf.json.JSONObject;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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



    @GetMapping("/yusao/queue/{msg}")
    public String sendQueue(@PathVariable("msg") String msg){
        productService.sendMessage(this.queue,msg);
        return "/index";
    }

    @GetMapping("/topic/{msg}")
    public String sendTopic(@PathVariable("msg")String msg){
        productService.sendMessage(this.topic,msg);
        return "/index";
    }



    @RequestMapping("/yusao/index")
    public String YuesaoIndex(){
        //分页
        //第几页，多少条


        return null;
    }
    @RequestMapping("/search/workarea")//工作地点
    public String Searchworkarea (String  workarea){
        return null;

    }
    @RequestMapping("/search/name")//名字
    public String Searchname (@RequestParam(name="name")String name,@RequestParam(name="page",defaultValue = "0")int page,@RequestParam(name = "rows",defaultValue = "10") int rows) throws IOException, SolrServerException {
        SearchResult result=yuesobasicinfoSolrservice.search(name,page,rows);
        JSONObject jsonResult = JSONObject.fromObject(result);
        System.out.println(jsonResult);
        return null;

    }
    @RequestMapping("/search/name/keyword")//名字--关键词
    public String Searchnamekeyword (@RequestParam(name="name")String name){


        return null;

    }
    @RequestMapping("/search/type")//类型
    public String Searchtype (String  type){
        return null;

    }
    @RequestMapping("/search/wages")//工资
    public String Searchwages (String  wages){
        return null;

    }
    @RequestMapping("/search/nativeplace")//籍贯
    public String Searchnativeplace (String  nativeplace){


        return null;

    }
    @RequestMapping("/search/age")//年龄
    public String Searchage (String  age){
        return null;

    }




}
