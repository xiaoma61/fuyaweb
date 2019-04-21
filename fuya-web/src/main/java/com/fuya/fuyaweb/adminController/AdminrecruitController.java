package com.fuya.fuyaweb.adminController;

import com.fuya.Configuration.DateJsonValueProcessor;
import com.fuya.fuyadao.entity.RECRUIT;
import com.fuya.fuyadao.model.CompanyRecruitinfo;
import com.fuya.fuyaservice.RECRUITService;
import com.fuya.fuyautil.JpaPageHelperUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//招聘管理
@Controller
@CrossOrigin
public class AdminrecruitController {
    @Autowired
    RECRUITService recruitService;

    //删除
    @RequiresRoles("admin")
    @RequestMapping("/admin/recruitlist")
    @ResponseBody
    public Map<String,Object> recruitlist(@RequestParam(name = "start",defaultValue = "0")int start, @RequestParam(name = "rows",defaultValue = "10")int rows
            , HttpServletRequest request) throws IOException, SolrServerException {

        Pageable pageable = PageRequest.of(start, rows);
        Page<RECRUIT> allPicturesPage = recruitService.findAll(pageable);
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor("yyyy-MM-dd HH:mm:ss"));
        Map<String,Object> msg=new HashMap<>();
        if (allPicturesPage==null){
            msg.put("msg","暂无信息");

        }else {

            msg.put("msg",allPicturesPage);
        }
        return msg;

    }

    //查看
    @RequiresRoles("admin")
    @RequestMapping("/admin/recruitlist/id")
    @ResponseBody
    public Map<String, Object> recruitlist(@RequestParam(name = "start",defaultValue = "0")int start, @RequestParam(name = "rows",defaultValue = "10")int rows
            , @RequestParam(name = "id")int id) throws IOException, SolrServerException {

        RECRUIT recruit=recruitService.findByID(id);

        Map<String,Object> msg=new HashMap<>();
        msg.put("msg",recruit);
        return msg;


    }



}
