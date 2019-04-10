package com.fuya.fuyaweb.adminController;

import com.fuya.fuyadao.entity.RECRUIT;
import com.fuya.fuyadao.model.CompanyRecruitinfo;
import com.fuya.fuyaservice.RECRUITService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import net.sf.json.JSONObject;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
public class AdminrecruitController {
    @Autowired
    RECRUITService recruitService;

    //删除
    @RequiresRoles("admin")
    @RequestMapping("/admin/recruitlist")
    @ResponseBody
    public JSONObject recruitlist(@RequestParam(name = "start",defaultValue = "0")int start, @RequestParam(name = "rows",defaultValue = "10")int rows
            , HttpServletRequest request) throws IOException, SolrServerException {


        List<CompanyRecruitinfo>companyRecruitinfoList=new ArrayList<>();
        List<Object> objectList=recruitService.find();
        for (int i=0;i<objectList.size();i++){
            Object[]objects=(Object[])objectList.get(i);
            CompanyRecruitinfo companyRecruitinfo=new CompanyRecruitinfo();
            companyRecruitinfo.setCORPORATENAME(objects[0].toString());
            companyRecruitinfo.setPOSITION(objects[1].toString());
            companyRecruitinfo.setTIME(objects[2].toString());
            companyRecruitinfo.setSALARY(objects[3].toString());
            companyRecruitinfo.setEDUCATION(objects[4].toString());
            companyRecruitinfo.setWORKAREA(objects[5].toString());
            companyRecruitinfo.setRECRUITID(Integer.parseInt((String) objects[6]));


        }

        PageHelper.startPage(start,rows);
        PageInfo<CompanyRecruitinfo>recruitPageInfo=new PageInfo<>(companyRecruitinfoList);
        return JSONObject.fromObject(recruitPageInfo);


    }

    //查看
    @RequiresRoles("admin")
    @RequestMapping("/admin/recruitlist/id")
    @ResponseBody
    public JSONObject recruitlist(@RequestParam(name = "start",defaultValue = "0")int start, @RequestParam(name = "rows",defaultValue = "10")int rows
            ,@RequestParam(name = "id")int id) throws IOException, SolrServerException {

        RECRUIT recruit=recruitService.findByID(id);

        Map<String,Object> msg=new HashMap<>();
        msg.put("msg",recruit);
        return JSONObject.fromObject(msg);


    }



}
