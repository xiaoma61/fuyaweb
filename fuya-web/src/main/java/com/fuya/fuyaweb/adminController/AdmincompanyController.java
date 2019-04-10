package com.fuya.fuyaweb.adminController;

import com.fuya.fuyadao.model.CompanyInfo;
import com.fuya.fuyadao.model.CompanysInfosModle;
import com.fuya.fuyaservice.COMPANYBASICINFOService;
import com.fuya.fuyaservice.COMPANYINFOService;
import com.fuya.fuyaservice.USERService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import net.sf.json.JSONObject;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Controller

public class AdmincompanyController {
    @Autowired
    COMPANYINFOService companyinfoService;
    @Autowired
    COMPANYBASICINFOService companybasicinfoService;
    @Autowired
    USERService userService;
    //公司列表
    @RequiresRoles("admin")
    @RequestMapping("/admin/companylist")
    @ResponseBody
    public JSONObject companylist(@RequestParam(name = "start",defaultValue = "0")int start, @RequestParam(name = "rows",defaultValue = "10")int rows){

        List<Object>objectList=companyinfoService.find();

        List<CompanyInfo>companyInfoList=new ArrayList<>();
        for (int i=0;i<objectList.size();i++){
            Object[]objects=(Object[])objectList.get(i);
            CompanyInfo companyInfo=new CompanyInfo();
            companyInfo.setCONTACTNAME((objects[2].toString()));
            companyInfo.setCORPORATENAME(objects[4].toString());
            companyInfo.setIDCARD(objects[0].toString());
            companyInfo.setLICENCENO(objects[1].toString());
            companyInfo.setUSERSID((Integer) objects[3]);
            companyInfoList.add(companyInfo);
        }


        PageHelper.startPage(start,rows);
        PageInfo<CompanyInfo>pageInfo=new PageInfo<>(companyInfoList);
        Map<String,Object> msg=new HashMap<>();
        msg.put("msg",pageInfo);
        return JSONObject.fromObject(msg);


    }
    //公司名称搜索
    @RequiresRoles("admin")
    @RequestMapping("/admin/companynamelist/search")
    @ResponseBody
    public JSONObject companynamelist(@RequestParam(name = "name")String name,@RequestParam(name = "start",defaultValue = "0")int start, @RequestParam(name = "rows",defaultValue = "10")int rows){


        List<CompanysInfosModle>companysInfosModleList=companybasicinfoService.findCOMPANYBASICINFOByCORPORATENAMELike(name);

        PageHelper.startPage(start,rows);
        PageInfo<CompanysInfosModle>pageInfo=new PageInfo<>(companysInfosModleList);
        Map<String,Object> msg=new HashMap<>();
        msg.put("msg",pageInfo);
        return JSONObject.fromObject(msg);

    }

    @RequiresRoles("admin")
    @RequestMapping("/admin/companynamelist")
    @ResponseBody
    public JSONObject companynamelist(@RequestParam(name = "name")String name){

        List<String>stringList=companybasicinfoService.findByCORPORATENAMELike(name);
        HashSet<String>hashSet=new HashSet<>();
        for (String s:stringList){
            hashSet.add(s);
        }

//        PageHelper.startPage(start,rows);
//        PageInfo<String>pageInfo=new PageInfo<>(stringList);
        Map<String,Object> msg=new HashMap<>();
        msg.put("msg",hashSet);
        return JSONObject.fromObject(msg);

    }



    //删除
    @RequiresRoles("admin")
    @RequestMapping("/admin/company/delete")
    @ResponseBody
    public JSONObject companydelete(@RequestParam(name = "id")int id){


        //用户名删除
        //信息删除
        //
        //月嫂删除
        //订单删除
        userService.deleteAllByUSERSID(id);

        Map<String,Object> msg=new HashMap<>();
        msg.put("msg","success");
        return JSONObject.fromObject(msg);


    }
    //查看
    @RequiresRoles("admin")
    @RequestMapping("/admin/company/id")
    @ResponseBody
    public JSONObject companyid(@RequestParam(name = "id")int id){


        CompanysInfosModle companysInfosModle=companyinfoService.findByUSERSID(id);
        Map<String,Object> msg=new HashMap<>();
        msg.put("msg",companysInfosModle);
        return JSONObject.fromObject(msg);

    }

}
