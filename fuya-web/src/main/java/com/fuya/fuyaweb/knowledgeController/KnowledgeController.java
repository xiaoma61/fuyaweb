package com.fuya.fuyaweb.knowledgeController;

import com.fuya.ActiveMQ.service.ProductService;
import com.fuya.fuyadao.entity.ARTICLE;
import com.fuya.fuyadao.model.ARTICLEidinfo;
import com.fuya.fuyadao.model.ARTICLEinfo;
import com.fuya.fuyaservice.ARTICLEService;
import com.fuya.fuyasolr.SearchResult.SearchResult;
import com.fuya.fuyasolr.Solr.service.ARTICLESolrService;
import net.sf.json.JSONObject;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.jms.Queue;
import javax.jms.Topic;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
public class KnowledgeController {
    @Autowired
    ARTICLESolrService articleSolrService;
    @Autowired
    ARTICLEService articleService;
    @Autowired
    private Queue queue;
    @Autowired
    private Topic topic;
    @Autowired
    private ProductService productService;

    //显示主要页面
    @RequestMapping("/knowledge")
    JSONObject knowledge(@RequestParam(name="type",defaultValue = "1")int type,@RequestParam(name = "start",defaultValue = "0")int start,@RequestParam(name = "rows",defaultValue = "10")int rows) throws IOException, SolrServerException {

       SearchResult searchResult= articleSolrService.Searchbytype(type,start,rows);
        int totalpages=0;
        if (searchResult.getObjects().size()%rows!=0){
            totalpages=(searchResult.getObjects().size()/rows)+1;
        }else {
            totalpages=(searchResult.getObjects().size()/rows);
        }
        searchResult.setTotalPage(totalpages);


        //和相关推荐
        ARTICLE article= (ARTICLE) searchResult.getObjects().get(0);
        SearchResult articlesearchResult=articleSolrService.SearchbyLikeName(article.getTITLE(),start,rows);
        List<ARTICLE>articleList=articlesearchResult.getObjects();
        ARTICLEinfo articlEinfo=new ARTICLEinfo();
        articlEinfo.setObjects(Collections.singletonList(articleList));
        articlEinfo.setSearchResult(searchResult);





        return JSONObject.fromObject(articlEinfo);

    }

    //查看文章
    @RequestMapping("/knowledge/id")
    JSONObject knowledgebyid(@RequestParam(name = "id")int id) throws IOException, SolrServerException {
        SearchResult searchResult=articleSolrService.Searchbyid(id);
        List<ARTICLE> articleList=searchResult.getObjects();
        ARTICLE article=articleList.get(0);
        int nums=article.getNUMS()+1;
        //更新数据库
        articleService.updatebyid(nums,id);
        //更新solr
        productService.sendMessage(this.topic,"article-update:"+id);

        //和相关推荐

        SearchResult articlesearchResult=articleSolrService.SearchbyLikeName(article.getTITLE(),0,10);
        List<ARTICLE>articleList1=articlesearchResult.getObjects();
        ARTICLEidinfo articleidinfo=new ARTICLEidinfo();
        articleidinfo.setArticle(article);
        articleidinfo.setObjects(Collections.singletonList(articleList1));



        return JSONObject.fromObject(articleidinfo);
    }
    //搜索
    @RequestMapping("/knowledge/searchtitle")
    JSONObject knowledgebyTitle(@RequestParam(name = "Title")String title,@RequestParam(name = "start",defaultValue = "0")int start,
                                @RequestParam(name="rows",defaultValue = "5")int rows) throws IOException, SolrServerException {
        SearchResult searchResult=articleSolrService.SearchbyLikeName(title,start,rows);
        List<ARTICLE> articleList=searchResult.getObjects();
        List<String>stringList=new ArrayList<>();
        for (ARTICLE article:articleList){
            stringList.add(article.getTITLE());

        }
        return JSONObject.fromObject(stringList);
    }
    //根据文章题目搜索
    @RequestMapping("/knowledge/searchbytitle")
    JSONObject knowledgeSearchbyTitle(@RequestParam(name = "Title")String title,@RequestParam(name = "start",defaultValue = "0")int start,
                                      @RequestParam(name="rows",defaultValue = "10")int rows) throws IOException, SolrServerException {
        SearchResult searchResult=articleSolrService.SearchbyLikeName(title,start,rows);
        return JSONObject.fromObject(searchResult);
    }


}
