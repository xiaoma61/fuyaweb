package com.fuya.fuyaweb.IndexrecruitController;

import com.fuya.fuyasolr.SearchResult.SearchResult;
import com.fuya.fuyasolr.Solr.service.RECRUITSolrService;
import net.sf.json.JSONObject;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
public class IndexrecruitController {

    @Autowired
    RECRUITSolrService recruitSolrService;

    @RequestMapping("/recruit")
    @ResponseBody
    public JSONObject recruit(@RequestParam(name = "start",defaultValue = "0")int start, @RequestParam(name = "rows",defaultValue = "10")int rows) throws IOException, SolrServerException {
      //获取当前时间
        SearchResult searchResult=recruitSolrService.SearchBytime();
        if (searchResult!=null){
            return JSONObject.fromObject(searchResult);
        }
        Map<String,Object> msg=new HashMap<>();
        msg.put("msg","null");
        return JSONObject.fromObject(msg);



    }
}
