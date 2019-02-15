package com.fuya.fuyaweb.adminController;

import com.fuya.ActiveMQ.service.ProductService;
import com.fuya.fuyadao.entity.ARTICLE;
import com.fuya.fuyadao.model.ARTICLEmodel;
import com.fuya.fuyaservice.ARTICLEService;
import com.fuya.fuyasolr.SearchResult.SearchResult;
import com.fuya.fuyasolr.Solr.service.ARTICLESolrService;
import com.fuya.fuyautil.GetTypeUtil;
import com.fuya.fuyautil.SearchKeyword;
import com.fuya.fuyautil.TimeUtil;
import com.fuya.fuyautil.TotalPages;
import net.sf.json.JSONObject;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.jms.Queue;
import javax.jms.Topic;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
//@RequestMapping("/admin/article")
public class AdminarticleController {

    @Autowired
    ARTICLEService articleService;
    @Autowired
    ARTICLESolrService articleSolrService;
    @Autowired
    private Queue queue;
    @Autowired
    private Topic topic;
    @Autowired
    private ProductService productService;



    //增加
    @RequiresRoles("admin")
    @RequestMapping("/admin/article/add")
    @ResponseBody
    public JSONObject add(@RequestParam("type")String articletype, @RequestParam("title")String title,
                          @RequestParam("content")String content){
        int type=0;
        type= GetTypeUtil.GetType(articletype);




        ARTICLE article=new ARTICLE();
        article.setTYPE(type);
        article.setTITLE(title);
        Date date= TimeUtil.getsqldate(new java.util.Date());
        article.setTIME(date);
        article.setNUMS(0);
        article.setCONTENT(content);
        articleService.save(article);
        productService.sendMessage(this.topic,"article-add:"+article.getARTICLEID());

        Map<String,String>msg=new HashMap<>();
        msg.put("msg","success");
        return JSONObject.fromObject(msg);
    }

    //删除
    @RequiresRoles("admin")
    @RequestMapping("/admin/article/delete")
    @ResponseBody
    public JSONObject delete(@RequestParam("id")String articleid) throws IOException, SolrServerException {
        //solr表删除
        articleSolrService.delete(Integer.parseInt(articleid));
        //数据库删除
        articleService.delete(Integer.parseInt(articleid));
        Map<String,String>msg=new HashMap<>();
        msg.put("msg","success");
        return JSONObject.fromObject(msg);
    }
    //修改
    @RequiresRoles("admin")
    @RequestMapping("/admin/article/update")
    @ResponseBody
    public JSONObject update(@RequestParam("type")String articletype, @RequestParam("title")String title,
                             @RequestParam("content")String content,@RequestParam("id")int id){

        int type=0;
        type= GetTypeUtil.GetType(articletype);

        //solr表修改
        //更新solr
        productService.sendMessage(this.topic,"article-update:"+id);

        //数据库修改
        articleService.updateARTICLEbyid(type,title,content,id);

        Map<String,String>msg=new HashMap<>();
        msg.put("msg","success");
        return JSONObject.fromObject(msg);
    }
    //查看
    @RequiresRoles("admin")
    @RequestMapping("/admin/article/id")
    @ResponseBody
    public JSONObject Searchbyid(@RequestParam("id")int id) throws IOException, SolrServerException {


        SearchResult searchResult=articleSolrService.Searchbyid(id);
        if (searchResult!=null&&searchResult.getObjects().size()>0){
            ARTICLEmodel article= (ARTICLEmodel) searchResult.getObjects().get(0);
            System.out.println("artic"+article.getARTICLEID());
            return JSONObject.fromObject(article);
        }


        Map<String,String>msg=new HashMap<>();
        msg.put("msg","error");
        return JSONObject.fromObject(msg);
    }
    //文章列表
    @RequiresRoles("admin")
    @RequestMapping("/admin/article/articles")
    @ResponseBody
    public JSONObject articlelist(@RequestParam(name="type",defaultValue = "1")int type,@RequestParam(name = "start",defaultValue = "0")int start,@RequestParam(name = "rows",defaultValue = "10")int rows) throws IOException, SolrServerException {

        SearchResult searchResult=articleSolrService.Searchbytype(type,start,rows);

        searchResult.setTotalPage(TotalPages.GetIMage(searchResult.getObjects().size(),rows));
        Map<String,String>msg=new HashMap<>();
        msg.put("msg","success");
        return JSONObject.fromObject(searchResult);
    }
    @RequiresRoles("admin")
    @RequestMapping("/admin/article/searchbytime")
    @ResponseBody
    //搜索
    public JSONObject Searchbytime(@RequestParam(name="startTime",defaultValue = "1")String startTime,
                                   @RequestParam(name = "endTime",defaultValue = "0")String endTime,
                                   @RequestParam(name = "start",defaultValue = "0")int start,
                                   @RequestParam(name = "rows",defaultValue = "10")int rows) throws ParseException, IOException, SolrServerException {

        java.util.Date sTime=TimeUtil.stringtodate(startTime);
        java.util.Date eTime=TimeUtil.stringtodate(endTime);
        SearchResult searchResult=articleSolrService.SearchbyTime(sTime,eTime,start,rows);

        return JSONObject.fromObject(searchResult);
    }
    @RequiresRoles("admin")
    @RequestMapping("/admin/article/searchbytile")
    @ResponseBody
    //搜索
    public JSONObject Searchbytitle(@RequestParam("title")String title,@RequestParam(name = "start",defaultValue = "0")int start,@RequestParam(name = "rows",defaultValue = "10")int rows) throws IOException, SolrServerException {


        SearchResult searchResult=articleSolrService.SearchbyLikeName(title,start,rows);
        return JSONObject.fromObject(searchResult);
    }
    //搜索
    @RequestMapping("/admin/article/keyword")
    @ResponseBody
    public JSONObject Searchbykeyword(@RequestParam("title")String title,@RequestParam(name = "start",defaultValue = "0")int start,@RequestParam(name = "rows",defaultValue = "10")int rows) throws IOException, SolrServerException {


        SearchResult searchResult=articleSolrService.SearchbyLikeName(title,start,rows);
        List<String>stringList=SearchKeyword.searchkeyword(searchResult);
        Map<String,List>msg=new HashMap<>();
        msg.put("msg",stringList);
        return JSONObject.fromObject(msg);
    }

}
