package com.fuya.fuyaweb.RegisterLoginController;

import com.fuya.ActiveMQ.service.ProductService;
import com.fuya.fuyadao.entity.*;
import com.fuya.fuyaservice.*;
import com.fuya.fuyasolr.Solr.service.USERSSolrservice;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.jms.Queue;
import javax.jms.Topic;
import java.util.List;

@Controller
public class RegisterController {

    @Autowired
    private USERService userService;
    @Autowired
    YUESOBASICINFOService yuesobasicinfoService;
    @Autowired
    PROVEINFOService proveinfoService;
    @Autowired
    COMPANYINFOService companyinfoService;
    @Autowired
    private Queue queue;
    @Autowired
    private Topic topic;
    @Autowired
    private ProductService productService;
   @Autowired
   private USERSSolrservice usersSolrservice;
   @Autowired
   private COMPANYBASICINFOService companybasicinfoService;



    //普通用户注册
    @RequestMapping("/Register")
    String Register(String name, String password, String phone, @RequestParam(name="type" ,defaultValue = "1")int type){
        USERS users=new USERS();
        users.setNAME(name);
        users.setPASSWORD(password);
        users.setPHONE(phone);
        users.setTYPE(type);
        userService.save(users);
        return "redirect:/Login";
    }
    //月嫂用户注册3
    @RequestMapping("/yuesaoRegister")
    String yuesaoRegister(String name,String phone,String idcard,String age, String education ,String nativeplace,
                          String email,String photo,String widght,String height ,String wages,String seniority,String yuesaotype,String workarea,
                          String yuesaosyndrome,String healthcertificates,String report,String score,String servicepiceture){

        //未被审核不能被查找到
        int type=4;
        //以姓名为账号名， 密码身份证后6位
        String password=idcard.substring(0,6);//身份证后6位
        USERS users=new USERS();
        users.setNAME(name);
        users.setPASSWORD(password);
        users.setPHONE(phone);
        users.setTYPE(type);
        userService.save(users);

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
        productService.sendMessage(this.topic,"yuesobasicinfo:"+ users.getUSERSID());





        //类型为4
        //照片上传为回显功能



        //跳转到下一个页面
        return "redirect:/Login";
    }



    //企业用户注册2
    @RequestMapping("/companyRegister")
    public String companyRegister(@RequestParam(name="name") String name,@RequestParam(name = "phone") String phone,@RequestParam(name = "password") String password,
                                  String corporatename,String email,String licene,
                                  String contactname,String contantphone,String liceneno,String idcard,
                                  String address,String idcardfile
                                    ){


        int type=2;
        USERS users=new USERS();
        users.setNAME(name);
        users.setPASSWORD(password);
        users.setPHONE(phone);
        users.setTYPE(type);
        userService.save(users);
        COMPANYBASICINFO companybasicinfo=new COMPANYBASICINFO();
        companybasicinfo.setADDRESS(address);
        companybasicinfo.setCORPORATENAME(corporatename);
        companybasicinfo.setUSERID(users.getUSERSID());
        companybasicinfo.setNUMS(0);
        companybasicinfo.setLEVELS(0);
        companybasicinfo.setINTRODUCE("暂无介绍");
        companybasicinfoService.save(companybasicinfo);

        COMPANYINFO companyinfo=new COMPANYINFO();
        companyinfo.setADDRESS(address);
        companyinfo.setCONTACTNAME(contactname);
        companyinfo.setUSERSID(users.getUSERSID());
        companyinfo.setLICENCENO(liceneno);
        companyinfo.setLICENCE(licene);
        companyinfo.setEMAIL(email);
        companyinfo.setIDCARD(idcard);
        companyinfo.setIDCARDFILE(idcardfile);
        companyinfo.setCONTACTPHONE(contantphone);
        companyinfoService.save(companyinfo);


        //Activemq通知
        productService.sendMessage(this.topic,"companyregister:"+users.getUSERSID());
        return "redirect:/Login";
    }

    //实现用户名查找
    @RequestMapping("findusername")
    @ResponseBody
    public JSONArray findusername(@RequestParam(name = "keyword")String keyword){
        List<USERS>usersList=usersSolrservice.search(keyword);
        if (usersList!=null){

            JSONArray jsonArray=JSONArray.fromObject(usersList);
            return jsonArray;
    }
        return null;



    }




}
