package com.fuya.fuyaweb.companyController;


import com.fuya.ActiveMQ.service.ProductService;
import com.fuya.fuyadao.entity.COMPANYYUESAO;
import com.fuya.fuyadao.entity.PROVEINFO;
import com.fuya.fuyadao.entity.USERS;
import com.fuya.fuyadao.entity.YUESOBASICINFO;
import com.fuya.fuyadao.model.PROVEINFOANDBAISINFO;
import com.fuya.fuyaservice.COMPANYYUESAOService;
import com.fuya.fuyaservice.PROVEINFOService;
import com.fuya.fuyaservice.USERService;
import com.fuya.fuyaservice.YUESOBASICINFOService;
import com.fuya.fuyasolr.Solr.service.USERSSolrservice;
import com.fuya.fuyautil.StringNameUtil;
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

import javax.jms.Queue;
import javax.jms.Topic;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class CompanyyuesaosController {
    @Autowired
    private Queue queue;
    @Autowired
    private Topic topic;
    @Autowired
    private ProductService productService;
    @Autowired
    private USERSSolrservice usersSolrservice;
    @Autowired
    private USERService userService;
    @Autowired
    YUESOBASICINFOService yuesobasicinfoService;
    @Autowired
    PROVEINFOService proveinfoService;
    @Autowired
    COMPANYYUESAOService companyyuesaoService;


    //月嫂信息录入--增加
    //月嫂用户注册3
    @RequiresRoles("company")
    @RequestMapping("/companys/yuesao/add")
    @ResponseBody
    JSONObject Companyyuesaoadd(@RequestParam(name = "name",defaultValue = "肖彩珠") String name, @RequestParam(name = "phone",defaultValue = "1314333") String phone,
                               @RequestParam(name = "idcard",defaultValue = "44440000")String idcard, @RequestParam(name = "age",defaultValue = "45") String age,
                               @RequestParam(name = "education",defaultValue = "高中") String education , @RequestParam(name = "nativeplace",defaultValue = "北京") String nativeplace,
                               @RequestParam(name = "email",defaultValue = "123@qq.com") String email, @RequestParam(name = "photo",defaultValue = "file:") String photo,
                               @RequestParam(name = "widght",defaultValue = "63kg") String widght, @RequestParam(name = "height",defaultValue = "163cm") String height ,
                               @RequestParam(name = "wages",defaultValue = "13") String wages, @RequestParam(name = "seniority",defaultValue = "ssss") String seniority,
                               @RequestParam(name = "yuesaotype",defaultValue = "育婴师") String yuesaotype,
                               @RequestParam(name = "workarea",defaultValue = "工作地点") String workarea,
                               @RequestParam(name = "yuesaosyndrome",defaultValue = "yuesaosyndrome") String yuesaosyndrome,
                               @RequestParam(name = "healthcertificates",defaultValue = "healthcertificates") String healthcertificates,
                               @RequestParam(name = "report",defaultValue = "report") String report,
                               @RequestParam(name = "score",defaultValue = "12") String score,
                               @RequestParam(name = "servicepiceture",defaultValue = "头像") String servicepiceture, HttpServletRequest request) throws IOException, SolrServerException {

        HttpSession session=request.getSession();
        int id= (int) session.getAttribute("id");

//        List<USERS> usersList =usersSolrservice.searchbyusername(name);
        Map<String,Object> msg=new HashMap<>();
//        if (usersList.size()>0){
//            msg.put("msg","error");
//            return  JSONArray.fromObject(msg);
//        }
        //未被审核不能被查找到
        int type=3;
        //以姓名为账号名， 密码身份证后6位
        String password=idcard.substring(0,6);//身份证后6位
        USERS users=new USERS();
        String nums= StringNameUtil.getRandomString(6);
        users.setNAME(name+nums);
        users.setPASSWORD(password);
        users.setPHONE(phone);
        users.setTYPE(type);
        userService.save(users);






//        productService.sendMessage(this.topic,"users:"+ users.getUSERSID());
        //其他信息录入
        YUESOBASICINFO yuesobasicinfo=new YUESOBASICINFO();
        yuesobasicinfo.setPHONE(phone);
        yuesobasicinfo.setEDUCATION(education);
        yuesobasicinfo.setWAGES(wages);
        yuesobasicinfo.setLEVELS(0);
        yuesobasicinfo.setPHOTO(photo);
        yuesobasicinfo.setPHONE(phone);
        yuesobasicinfo.setSENIORITY(seniority);
        yuesobasicinfo.setWEIGHT(widght);
        yuesobasicinfo.setNATIVEPLACE(nativeplace);
        yuesobasicinfo.setEDUCATION(education);
        yuesobasicinfo.setIDCARD(idcard);
        yuesobasicinfo.setNAME(name);
        yuesobasicinfo.setHEIGHT(height);
        yuesobasicinfo.setEMAIL(email);
        yuesobasicinfo.setTYPE(yuesaotype);
        yuesobasicinfo.setAGE(age);
        yuesobasicinfo.setUSERSID(users.getUSERSID());
        yuesobasicinfo.setWORKAREA(workarea);
        yuesobasicinfoService.save(yuesobasicinfo);
//        productService.sendMessage(this.topic,"yuesobasicinfo:"+ yuesobasicinfo.getYUESOBASICINFOID());

        //证明存入
        PROVEINFO proveinfo=new PROVEINFO();
        //健康证明
        proveinfo.setHEALTHCERTIFICATES(healthcertificates);
        //体检证明
        proveinfo.setREPORT(report);
        //考核分数
        proveinfo.setSCORE(score);
        //服务证明
        proveinfo.setSERVICEPICTURE(servicepiceture);
        //月嫂证
        proveinfo.setYUESAOSYNDROME(yuesaosyndrome);
        proveinfo.setUSERSID(users.getUSERSID());

        proveinfoService.save(proveinfo);


        //存入数据库,同时存入solr库
        //Activemq通知
//        productService.sendMessage(this.topic,"proveinfo:"+ proveinfo.getPROVEINFOID());



        COMPANYYUESAO companyyuesao=new COMPANYYUESAO();
        companyyuesao.setCOMPANYID(id);
        companyyuesao.setYUESAOID(users.getUSERSID());
        companyyuesaoService.save(companyyuesao);
//        productService.sendMessage(this.topic,"companyyuesao:"+ companyyuesao.getCOMPANYYUESAOID());



        //用户关联
        //跳转到下一个页面
        msg.put("msg","success");
        return JSONObject.fromObject(msg);
    }
    //删除
    @RequiresRoles("company")
    @RequestMapping("/companys/yuesao/delete")
    @ResponseBody
    JSONObject Companyyuesaodelete(@RequestParam(name = "id",defaultValue = "id") int id,HttpServletRequest request){
        //删除user



        //删除证明信息
        yuesobasicinfoService.deleteByYUESOBASICINFOID(id);
        proveinfoService.deleteByUSERSID(id);
//        productService.sendMessage(this.topic,"yuesobasicinfoService-delete:"+id);
//        productService.sendMessage(this.topic,"proveinfoService-delete:"+id);


        //删除关系
        companyyuesaoService.deleteByCOMPANYYUESAOID(id);
//        productService.sendMessage(this.topic,"companyyuesaoService-delete:"+id);
//        HttpSession session=request.getSession();
//        int id= (int) session.getAttribute("id");
        userService.delete(id);
//        productService.sendMessage(this.topic,"users-delete:"+id);



        Map<String,Object> msg=new HashMap<>();
        msg.put("msg","success");
        return JSONObject.fromObject(msg);
    }
    //列表
    @RequiresRoles("company")
    @RequestMapping("/companys/yuesao/list")
    @ResponseBody
    JSONObject Companyyuesaolist(@RequestParam(name = "start",defaultValue = "0")int start,@RequestParam(name = "rows",defaultValue = "10")int rows,HttpServletRequest request){

        HttpSession session=request.getSession();
        int id= (int) session.getAttribute("id");
        //查找月嫂列表
        List<COMPANYYUESAO> companyyuesaoList=companyyuesaoService.findByRealCOMPANYID(id);
        List<PROVEINFOANDBAISINFO>objectLists=new ArrayList<>();
        for (COMPANYYUESAO companyyuesao : companyyuesaoList){
            List<PROVEINFOANDBAISINFO>objectList=proveinfoService.findPROVEINFOByAndYUESAOBASICINFOByUSERSID(companyyuesao.getYUESAOID());

            objectLists.addAll(objectList);

        }

        PageHelper.startPage(start,rows);
        PageInfo<PROVEINFOANDBAISINFO>proveinfoandbaisinfoPageInfo=new PageInfo<>(objectLists);

        Map<String,Object> msg=new HashMap<>();
        msg.put("msg",objectLists);
        return JSONObject.fromObject(proveinfoandbaisinfoPageInfo);
//        return JSONArray.fromObject(proveinfoandbaisinfoPageInfo);
    }
    //修改
    //月嫂信息录入--增加
    //月嫂用户注册3
    @RequiresRoles("company")
    @RequestMapping("/companys/yuesao/update")
    @ResponseBody
    JSONObject Companyyuesaoupdate(


                               @RequestParam(name = "name",defaultValue = "肖彩珠") String name, @RequestParam(name = "phone",defaultValue = "1314333") String phone,
                               @RequestParam(name = "idcard",defaultValue = "0000")String idcard, @RequestParam(name = "age",defaultValue = "45") String age,
                               @RequestParam(name = "education",defaultValue = "高中") String education , @RequestParam(name = "nativeplace",defaultValue = "北京") String nativeplace,
                               @RequestParam(name = "email",defaultValue = "123@qq.com") String email, @RequestParam(name = "photo",defaultValue = "file:") String photo,
                               @RequestParam(name = "widght",defaultValue = "63kg") String widght, @RequestParam(name = "height",defaultValue = "163cm") String height ,
                               @RequestParam(name = "wages",defaultValue = "13") String wages, @RequestParam(name = "seniority",defaultValue = "ssss") String seniority,
                               @RequestParam(name = "yuesaotype",defaultValue = "育婴师") String yuesaotype,
                               @RequestParam(name = "workarea",defaultValue = "工作地点") String workarea,
                               @RequestParam(name = "yuesaosyndrome",defaultValue = "yuesaosyndrome") String yuesaosyndrome,
                               @RequestParam(name = "healthcertificates",defaultValue = "healthcertificates") String healthcertificates,
                               @RequestParam(name = "report",defaultValue = "report") String report,
                               @RequestParam(name = "score",defaultValue = "12") String score,
                               @RequestParam(name = "servicepiceture",defaultValue = "头像") String servicepiceture,@RequestParam(name = "id",defaultValue = "20")int userid, HttpServletRequest request) throws IOException, SolrServerException {

        HttpSession session=request.getSession();
        int id= (int) session.getAttribute("id");
        //数据库更新
        //月嫂信息更新
        //证明信息更新
        proveinfoService.update(yuesaosyndrome,healthcertificates,report,servicepiceture,userid);
        yuesobasicinfoService.update(idcard,email,workarea,phone,education,name,widght,age,height,nativeplace,photo,seniority,wages,userid);






        //用户关联
        //跳转到下一个页面
        Map<String,Object> msg=new HashMap<>();
        msg.put("msg","success");
        return JSONObject.fromObject(msg);
    }
    //订单


}
