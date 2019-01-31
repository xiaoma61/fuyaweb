package com.fuya.fuyaweb.yusaoController;

import com.fuya.fuyadao.entity.YUESOBASICINFO;
import com.fuya.fuyasolr.Solr.service.YUESOBASICINFOSolrservice;
import net.sf.json.JSONObject;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@Controller
public class YuesaoMsgController {
    //根据id查找
    @Autowired
    YUESOBASICINFOSolrservice yuesobasicinfoSolrservice;
    @RequestMapping("/fuyayusao/search/id")//名字--关键词
    @ResponseBody
    public  JSONObject Searchbyid (int id) throws IOException, SolrServerException {
        //基本信息
        YUESOBASICINFO yuesobasicinfo=yuesobasicinfoSolrservice.searchbyid(id);


        //用户评价实现分页

        //服务技能

        //相关证明实现分页




    }

}
