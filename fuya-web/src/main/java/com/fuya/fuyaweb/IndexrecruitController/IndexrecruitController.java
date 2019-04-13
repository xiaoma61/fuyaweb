package com.fuya.fuyaweb.IndexrecruitController;

import com.fuya.fuyadao.entity.RECRUIT;
import com.fuya.fuyaservice.RECRUITService;
import com.fuya.fuyasolr.SearchResult.SearchResult;
import com.fuya.fuyasolr.Solr.service.RECRUITSolrService;
import net.sf.json.JSONObject;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 完成
 */
@Controller
public class IndexrecruitController {

    @Autowired
    RECRUITService recruitService;

    @RequestMapping("/recruit")
    @ResponseBody
    public Map<String,Object>  recruit(@RequestParam(name = "start",defaultValue = "0")int start, @RequestParam(name = "rows",defaultValue = "10")int rows)  {

        Pageable pageable = PageRequest.of(start, rows);
        Page<RECRUIT> allPicturesPage = recruitService.findAll(pageable);
        Map<String,Object> msg=new HashMap<>();
        if (allPicturesPage==null){
            msg.put("msg","暂无信息");

        }else {
            msg.put("msg",allPicturesPage);
        }

        return msg;


    }
}
