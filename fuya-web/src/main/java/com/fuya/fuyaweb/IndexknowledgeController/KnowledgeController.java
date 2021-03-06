package com.fuya.fuyaweb.IndexknowledgeController;

import com.fuya.ActiveMQ.service.ProductService;
import com.fuya.fuyadao.model.ARTICLEidinfo;
import com.fuya.fuyadao.model.ARTICLEinfo;
import com.fuya.fuyadao.model.ARTICLEmodel;
import com.fuya.fuyaservice.ARTICLEService;
import com.fuya.fuyasolr.SearchResult.SearchResult;
import com.fuya.fuyasolr.Solr.service.ARTICLESolrService;
import com.fuya.fuyautil.SearchKeyword;
import net.sf.json.JSONObject;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.jms.Queue;
import javax.jms.Topic;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 完成
 */
@Controller
@CrossOrigin
public class KnowledgeController {
    @Autowired
    ARTICLESolrService articleSolrService;
    @Autowired
    ARTICLEService articleService;
   /* @Autowired
    private Queue queue;
    @Autowired
    private Topic topic;
    @Autowired
    private ProductService productService;*/

    //显示主要页面
    @RequestMapping("/knowledge")
    @ResponseBody
    public JSONObject knowledge(@RequestParam(name="type",defaultValue = "1")int type, @RequestParam(name = "start",defaultValue = "0")int start,
                                @RequestParam(name = "rows",defaultValue = "10")int rows, HttpServletResponse response) throws IOException, SolrServerException {
        response.setHeader("Access-Control-Allow-Origin", "*");
       SearchResult searchResult= articleSolrService.Searchbytype(type,start,rows);
       if (searchResult!=null&&searchResult.getObjects().size()>0){
           //和相关推荐
           ARTICLEmodel article= (ARTICLEmodel) searchResult.getObjects().get(0);
           SearchResult articlesearchResult=articleSolrService.SearchbyLikeName(article.getTITLE(),start,rows);
           List<ARTICLEmodel>articleList=articlesearchResult.getObjects();
           ARTICLEinfo articlEinfo=new ARTICLEinfo();
           articlEinfo.setObjects(Collections.singletonList(articleList));
           articlEinfo.setSearchResult(searchResult);
           return JSONObject.fromObject(articlEinfo);
       }

        Map<String,Object> msg=new HashMap<>();
        msg.put("msg","暂无文章");
        return JSONObject.fromObject(msg);









    }

    //查看文章
    @RequestMapping("/knowledge/id")
    @ResponseBody
    public JSONObject knowledgebyid(@RequestParam(name = "id")int id,HttpServletResponse response) throws IOException, SolrServerException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        SearchResult searchResult=articleSolrService.Searchbyid(id);
        List<ARTICLEmodel> articleList=searchResult.getObjects();
        ARTICLEmodel article=articleList.get(0);
        int nums=article.getNUMS()+1;
        //更新数据库
        articleService.updatebyid(nums,id);
        //更新solr
        /*productService.sendMessage(this.topic,"article-update:"+id);*/

        //和相关推荐

        SearchResult articlesearchResult=articleSolrService.SearchbyLikeName(article.getTITLE(),0,10);
        List<ARTICLEmodel>articleList1=articlesearchResult.getObjects();
        ARTICLEidinfo articleidinfo=new ARTICLEidinfo();
        articleidinfo.setArticle(article);
        articleidinfo.setObjects(Collections.singletonList(articleList1));



        return JSONObject.fromObject(articleidinfo);
    }
    //搜索
    @RequestMapping("/knowledge/searchtitle")
    @ResponseBody
    public JSONObject knowledgebyTitle(@RequestParam(name = "Title")String title,@RequestParam(name = "start",defaultValue = "0")int start,
                                @RequestParam(name="rows",defaultValue = "5")int rows,HttpServletResponse response) throws IOException, SolrServerException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        SearchResult searchResult=articleSolrService.SearchbyLikeName(title,start,rows);
        Map<String,Object> msg=new HashMap<>();
        if (SearchKeyword.searchkeyword(searchResult)==null){
            msg.put("msg","暂无信息");
        }else {
            List<String>stringList=SearchKeyword.searchkeyword(searchResult);
            msg.put("msg",stringList);
        }



        return JSONObject.fromObject( msg);
    }
    //根据文章题目搜索
    @RequestMapping("/knowledge/searchbytitle")
    @ResponseBody
    public JSONObject knowledgeSearchbyTitle(@RequestParam(name = "Title")String title,@RequestParam(name = "start",defaultValue = "0")int start,
                                      @RequestParam(name="rows",defaultValue = "10")int rows,HttpServletResponse response) throws IOException, SolrServerException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        SearchResult searchResult=articleSolrService.SearchbyLikeName(title,start,rows);
        return JSONObject.fromObject(searchResult);
    }


}
