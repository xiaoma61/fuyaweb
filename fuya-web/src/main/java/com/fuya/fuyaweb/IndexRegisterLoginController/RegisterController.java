package com.fuya.fuyaweb.IndexRegisterLoginController;

import com.fuya.ActiveMQ.service.ProductService;
import com.fuya.fuyadao.entity.*;
import com.fuya.fuyaservice.*;
import com.fuya.fuyasolr.Solr.service.USERSSolrservice;
import com.fuya.fuyautil.StringNameUtil;
import net.sf.json.JSONArray;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.jms.Queue;
import javax.jms.Topic;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @RequestMapping("/Register/users")
    JSONArray Register(String name, String password, String phone, @RequestParam(name="type" ,defaultValue = "1")int type) throws IOException, SolrServerException {
        List<USERS>usersList=usersSolrservice.searchbyusername(name);
        Map<String,Object> msg=new HashMap<>();
        if (usersList.size()>0){
            msg.put("msg","error");
            return  JSONArray.fromObject(msg);
        }
        USERS users=new USERS();
        users.setNAME(name);
        users.setPASSWORD(password);
        users.setPHONE(phone);
        users.setTYPE(type);
        userService.save(users);
        productService.sendMessage(this.topic,"users:"+ users.getUSERSID());
        msg.put("msg","success");
        return JSONArray.fromObject(msg);
    }
    //月嫂用户注册3
    @RequestMapping("/Register/yuesao")
    JSONArray yuesaoRegister(@RequestParam(name = "name",defaultValue = "肖彩珠") String name,@RequestParam(name = "phone",defaultValue = "1314333") String phone,
                          @RequestParam(name = "idcard",defaultValue = "44440000")String idcard,@RequestParam(name = "age",defaultValue = "45") String age,
                          @RequestParam(name = "education",defaultValue = "高中") String education ,@RequestParam(name = "nativeplace",defaultValue = "北京") String nativeplace,
                          @RequestParam(name = "email",defaultValue = "123@qq.com") String email,@RequestParam(name = "photo",defaultValue = "file:") String photo,
                          @RequestParam(name = "widght",defaultValue = "63kg") String widght,@RequestParam(name = "height",defaultValue = "163cm") String height ,
                          @RequestParam(name = "wages",defaultValue = "13") String wages,@RequestParam(name = "seniority",defaultValue = "ssss") String seniority,
                          @RequestParam(name = "yuesaotype",defaultValue = "育婴师") String yuesaotype,
                          @RequestParam(name = "workarea",defaultValue = "工作地点") String workarea,
                          @RequestParam(name = "yuesaosyndrome",defaultValue = "yuesaosyndrome") String yuesaosyndrome,
                          @RequestParam(name = "healthcertificates",defaultValue = "healthcertificates") String healthcertificates,
                          @RequestParam(name = "report",defaultValue = "report") String report,
                          @RequestParam(name = "score",defaultValue = "12") String score,
                          @RequestParam(name = "servicepiceture",defaultValue = "头像") String servicepiceture) throws IOException, SolrServerException {

        List<USERS>usersList=usersSolrservice.searchbyusername(name);
        Map<String,Object> msg=new HashMap<>();
        if (usersList.size()>0){
            msg.put("msg","error");
            return  JSONArray.fromObject(msg);
        }
        //未被审核不能被查找到
        int type=4;
        //以姓名为账号名， 密码身份证后6位
        String password=idcard.substring(0,6);//身份证后6位
        USERS users=new USERS();
        String nums= StringNameUtil.getRandomString(6);
        users.setNAME(name+nums);
        users.setPASSWORD(password);
        users.setPHONE(phone);
        users.setTYPE(type);
        userService.save(users);
        productService.sendMessage(this.topic,"users:"+ users.getUSERSID());
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
        productService.sendMessage(this.topic,"yuesobasicinfo:"+ yuesobasicinfo.getYUESOBASICINFOID());

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
        productService.sendMessage(this.topic,"proveinfo:"+ proveinfo.getPROVEINFOID());





        //类型为4
        //照片上传为回显功能



        //跳转到下一个页面
        msg.put("msg","success");
        return JSONArray.fromObject(msg);
    }



    //企业用户注册2
    @RequestMapping("/Register/company")
    public JSONArray companyRegister(@RequestParam(name="name",defaultValue = "优先有限公司") String name, @RequestParam(name = "phone",defaultValue = "1314333") String phone,
                                     @RequestParam(name = "password",defaultValue = "1314") String password,
                                     @RequestParam(name = "corporatename",defaultValue = "corporatename")String corporatename, @RequestParam(name = " email",defaultValue = " email")String email,
                                     @RequestParam(name = "licene",defaultValue = "licene")String licene,
                                     @RequestParam(name = "contactname",defaultValue = "contactname") String contactname, @RequestParam(name = "contantphone",defaultValue = "131422")String contantphone,
                                     @RequestParam(name = "liceneno",defaultValue = "1314")String liceneno, @RequestParam(name = "idcard",defaultValue = "1314")String idcard,
                                     @RequestParam(name = "address",defaultValue = "1314")String address, @RequestParam(name = "idcardfile",defaultValue = "1314")String idcardfile
                                    ) throws IOException, SolrServerException {


        int type=2;
        List<USERS>usersList=usersSolrservice.searchbyusername(name);
        Map<String,Object> msg=new HashMap<>();
        if (usersList.size()>0){
            msg.put("msg","error");
            return  JSONArray.fromObject(msg);
        }
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
        productService.sendMessage(this.topic,"users:"+users.getUSERSID());
        productService.sendMessage(this.topic,"companyinfo:"+companyinfo.getCOMPANYINFOID());
        productService.sendMessage(this.topic,"companybasicinfo:"+companybasicinfo.getCOMPANYBASICINFOID());
        msg.put("msg","success");
        return JSONArray.fromObject(msg);
    }

    //实现用户名查找
    @RequestMapping("/Register/findusername")
    @ResponseBody
    public JSONArray findusername(@RequestParam(name = "keyword")String keyword){
        List<USERS>usersList=usersSolrservice.search(keyword);
        Map<String,Object> msg=new HashMap<>();
        if (usersList!=null){

            msg.put("msg",usersList);

         }
         msg.put("msg","error");
        return  JSONArray.fromObject(msg);

    }




}
