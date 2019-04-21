package com.fuya.fuyaweb.adminController;

import com.fuya.fuyadao.entity.USERS;
import com.fuya.fuyadao.model.AdminYuesaoinfo;
import com.fuya.fuyadao.model.PROVEINFOANDBAISINFO;
import com.fuya.fuyaservice.*;
import com.fuya.fuyautil.JpaPageHelperUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import net.sf.json.JSONObject;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@CrossOrigin
public class AdminyuesaoController {
    @Autowired
    private YUESOBASICINFOService yuesobasicinfoService;
    @Autowired
    private ORDERSService ordersService;
    @Autowired
    private USERService userService;
   /* @Autowired
    private USERSSolrservice usersSolrservice;*/
    @Autowired
    private PROVEINFOService proveinfoService;
    @Autowired
    private YUESAOOTHERPROVEService yuesaootherproveService;
    @Autowired
    private JavaMailSender mailSender;


    @Value("${spring.mail.username}")
    private String from;

    //未审核
    @RequiresRoles("admin")
    @RequestMapping("/admin/uncheckyuesaolist")
    @ResponseBody
    public JSONObject uncheckyuesao(@RequestParam(name = "start",defaultValue = "0")int start, @RequestParam(name = "rows",defaultValue = "10")int rows){

        int type=4;
        List<Object> objectList=yuesobasicinfoService.findByTYPE(type);
        List<AdminYuesaoinfo> adminYuesaoinfofoList=new ArrayList<>();
        for (int i=0;i<objectList.size();i++){
            Object[]objects=(Object[])objectList.get(i);
            AdminYuesaoinfo adminYuesaoinfo=new AdminYuesaoinfo();
            adminYuesaoinfo.setUSERSID((Integer) objects[0]);
            adminYuesaoinfo.setNAME(objects[1].toString());
            adminYuesaoinfo.setTYPE(objects[2].toString());
            adminYuesaoinfo.setSENIORITY(objects[3].toString());
            adminYuesaoinfo.setEDUCATION(objects[4].toString());
            adminYuesaoinfo.setPHONE(objects[5].toString());
            adminYuesaoinfo.setSCORE(objects[6].toString());
            adminYuesaoinfo.setNums(String.valueOf(0));
            adminYuesaoinfofoList.add(adminYuesaoinfo);
        }
        PageInfo pageInfo= JpaPageHelperUtil.SetStartPage(adminYuesaoinfofoList,start+1,rows);
        Map<String,Object> msg=new HashMap<>();
        msg.put("msg",pageInfo);
        return JSONObject.fromObject(msg);
    }
    //已经审核
    @RequiresRoles("admin")
    @RequestMapping("/admin/checkyuesaolist")
    @ResponseBody
    public JSONObject checkyuesao(@RequestParam(name = "start",defaultValue = "0")int start, @RequestParam(name = "rows",defaultValue = "10")int rows){

        int type=3;
        List<Object> objectList=yuesobasicinfoService.findByTYPE(type);

        List<AdminYuesaoinfo> adminYuesaoinfofoList=new ArrayList<>();
        for (int i=0;i<objectList.size();i++){
            Object[]objects=(Object[])objectList.get(i);
            AdminYuesaoinfo adminYuesaoinfo=new AdminYuesaoinfo();
            adminYuesaoinfo.setUSERSID((Integer) objects[0]);
            adminYuesaoinfo.setNAME(objects[1].toString());
            adminYuesaoinfo.setTYPE(objects[2].toString());
            adminYuesaoinfo.setSENIORITY(objects[3].toString());
            adminYuesaoinfo.setEDUCATION(objects[4].toString());
            adminYuesaoinfo.setPHONE(objects[5].toString());
            if (objects[6]!=null){
                adminYuesaoinfo.setSCORE(objects[6].toString());
            }

            //查找做了多少订单
            int sum=ordersService.findcountByTOID((Integer) objects[0]);
            adminYuesaoinfo.setNums(String.valueOf(sum));
//            adminYuesaoinfo.setNums(String.valueOf(0));
            adminYuesaoinfofoList.add(adminYuesaoinfo);
        }

   /*     PageHelper.startPage(start,rows);*/
 /*       PageInfo<AdminYuesaoinfo> pageInfo=new PageInfo<>(adminYuesaoinfofoList);*/
        PageInfo pageInfo= JpaPageHelperUtil.SetStartPage(adminYuesaoinfofoList,start+1,rows);
        Map<String,Object> msg=new HashMap<>();
        msg.put("msg",pageInfo);
        return JSONObject.fromObject(msg);
    }
    //通过
    @RequiresRoles("admin")
    @RequestMapping("/admin/uncheckyuesaolist/pass")
    @ResponseBody
    public JSONObject uncheckyuesaopass(@RequestParam(name = "id",defaultValue = "0")int id) throws IOException, SolrServerException {

        int type=4;
        //更新
        USERS users=userService.findByID(id);
        userService.updatebyuserid(type,id);

        String email=yuesobasicinfoService.findEMILbyUERSID(id);

        //发送邮件
        SimpleMailMessage message = new SimpleMailMessage();


        System.out.println(from);
        System.out.println(email);
        if(email!=null){
            message.setFrom(from); // 发送人的邮箱
            message.setSubject("富雅公司应聘"); //标题
            message.setTo(email); //发给谁  对方邮箱
            message.setText("你已经申请通过了您的用户名为："+users.getNAME()+"密码为："+users.getPASSWORD()); //内容
            mailSender.send(message); //发送
        }



        Map<String,Object> msg=new HashMap<>();
        msg.put("msg","success");
        return JSONObject.fromObject(msg);
    }
    //查看
    @RequiresRoles("admin")
    @RequestMapping("/admin/checkyuesaolist/id")
    @ResponseBody
    public JSONObject yuesaoid(@RequestParam(name = "id",defaultValue = "0")int id) throws IOException, SolrServerException {

        List<PROVEINFOANDBAISINFO>objectList=proveinfoService.findPROVEINFOByAndYUESAOBASICINFOByUSERSID(id);
        Map<String,Object> msg=new HashMap<>();
        msg.put("msg",objectList);
        return JSONObject.fromObject(msg);
    }



    //删除
    @RequiresRoles("admin")
    @RequestMapping("/admin/checkyuesaolist/delete")
    @ResponseBody
    public JSONObject yuesaoiddelete(@RequestParam(name = "id",defaultValue = "0")int id) throws IOException, SolrServerException {


          userService.deleteAllByUSERSID(id);
        Map<String,Object> msg=new HashMap<>();
        msg.put("msg","success");
        return JSONObject.fromObject(msg);
    }

    //申请时间


    //姓名查询

}
