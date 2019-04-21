package com.fuya.fuyaweb.IndexRegisterLoginController;

import com.fuya.ActiveMQ.service.ProductService;
import com.fuya.Redis.Util.RedisUtil;
import com.fuya.fuyadao.entity.*;
import com.fuya.fuyaservice.*;
import com.fuya.fuyautil.ImageUtil;
import com.fuya.fuyautil.StringNameUtil;
import com.fuya.fuyautil.uuidUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.jms.Queue;
import javax.jms.Topic;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
@CrossOrigin
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
    private RedisUtil redisUtil;
  /* @Autowired
   private USERSSolrservice usersSolrservice;*/
   @Autowired
   private COMPANYBASICINFOService companybasicinfoService;



    //普通用户注册
    @RequestMapping("/Register/users")
    @ResponseBody()
    @CrossOrigin
    JSONArray Register(String name, String password, String phone, @RequestParam(name="type" ,defaultValue = "1")int type, HttpServletResponse response) throws IOException, SolrServerException {

            response.setHeader("Access-Control-Allow-Origin", "*");

   /*     List<USERS>usersList=usersSolrservice.searchbyusername(name);*/
        Map<String,Object> msg=new HashMap<>();
        /*if (usersList.size()>0){
            msg.put("msg","error");
            return  JSONArray.fromObject(msg);
        }*/
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
    @RequestMapping("/Register/MaternityMatron")
    public  String MaternityMatron(){
        return "/html/maternity_register.html";

    }

    //月嫂用户注册3
    @RequestMapping("/Register/yuesao")
    @ResponseBody()
    @CrossOrigin
    JSONArray yuesaoRegister(@RequestParam(name = "name",defaultValue = "肖彩珠") String name,@RequestParam(name = "phone",defaultValue = "1314333") String phone,
                          @RequestParam(name = "idcard",defaultValue = "44440000")String idcard,@RequestParam(name = "age",defaultValue = "45") int age,
                          @RequestParam(name = "education",defaultValue = "高中") String education ,@RequestParam(name = "nativeplace",defaultValue = "北京") String nativeplace,
                          @RequestParam(name = "email",defaultValue = "123@qq.com") String email,@RequestParam(name = "photo",defaultValue = "file:") String photo,
                          @RequestParam(name = "widght",defaultValue = "63kg") String widght,@RequestParam(name = "height",defaultValue = "163cm") String height ,
                          @RequestParam(name = "wages",defaultValue = "13") int wages,@RequestParam(name = "seniority",defaultValue = "ssss") String seniority,
                          @RequestParam(name = "yuesaotype",defaultValue = "1") int yuesaotype,
                          @RequestParam(name = "workarea",defaultValue = "工作地点") String workarea,
                          @RequestParam(name = "yuesaosyndrome",defaultValue = "yuesaosyndrome") String yuesaosyndrome,
                          @RequestParam(name = "healthcertificates",defaultValue = "healthcertificates") String healthcertificates,
                          @RequestParam(name = "report",defaultValue = "report") String report,
                          @RequestParam(name = "score",defaultValue = "12") String score,
                          @RequestParam(name = "servicepiceture",defaultValue = "头像") String servicepiceture, HttpServletResponse response) throws IOException, SolrServerException {
        response.setHeader("Access-Control-Allow-Origin", "*");

   /*     List<USERS> usersList=usersSolrservice.searchbyusername(name);*/
        Map<String,Object> msg=new HashMap<>();
       /* if (usersList.size()>0){
            msg.put("msg","error");
            return  JSONArray.fromObject(msg);
        }*/
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






        /*productService.sendMessage(this.topic,"users:"+ users.getUSERSID());*/
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
        yuesobasicinfo.setId(uuidUtil.getuuidUtil());
        yuesobasicinfoService.save(yuesobasicinfo);
    /*    productService.sendMessage(this.topic,"yuesobasicinfo:"+ yuesobasicinfo.getYUESOBASICINFOID());*/

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
     /*   productService.sendMessage(this.topic,"proveinfo:"+ proveinfo.getPROVEINFOID());*/


        //用户关联
        //跳转到下一个页面
        msg.put("msg","success");
        return JSONArray.fromObject(msg);
    }



    //企业用户注册2
    @RequestMapping("/Register/company")
    @ResponseBody()
    @CrossOrigin
    public JSONArray companyRegister(@RequestParam(name="name",defaultValue = "优先有限公司") String name, @RequestParam(name = "phone",defaultValue = "1314333") String phone,
                                     @RequestParam(name = "password",defaultValue = "1314") String password,
                                     @RequestParam(name = "corporatename",defaultValue = "corporatename")String corporatename, @RequestParam(name = " email",defaultValue = " email")String email,
                                     @RequestParam(name = "licene",defaultValue = "licene")String licene,
                                     @RequestParam(name = "contactname",defaultValue = "contactname") String contactname, @RequestParam(name = "contantphone",defaultValue = "131422")String contantphone,
                                     @RequestParam(name = "liceneno",defaultValue = "1314")String liceneno, @RequestParam(name = "idcard",defaultValue = "1314")String idcard,
                                     @RequestParam(name = "address",defaultValue = "1314")String address, @RequestParam(name = "idcardfile",defaultValue = "1314")String idcardfile
            , HttpServletResponse response) throws IOException, SolrServerException {

        response.setHeader("Access-Control-Allow-Origin", "*");
        int type=2;
/*        List<USERS>usersList=usersSolrservice.searchbyusername(name);*/
        Map<String,Object> msg=new HashMap<>();
       /* if (usersList.size()>0){
            msg.put("msg","error");
            return  JSONArray.fromObject(msg);
        }*/
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
        companybasicinfo.setId(uuidUtil.getuuidUtil());
        companybasicinfoService.save(companybasicinfo);


        COMPANYINFO companyinfo=new COMPANYINFO();
        companybasicinfo.setId(uuidUtil.getuuidUtil());
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
       /* productService.sendMessage(this.topic,"users:"+users.getUSERSID());
        productService.sendMessage(this.topic,"companyinfo:"+companyinfo.getCOMPANYINFOID());
        productService.sendMessage(this.topic,"companybasicinfo:"+companybasicinfo.getCOMPANYBASICINFOID());*/
        msg.put("msg","success");
        return JSONArray.fromObject(msg);
    }

    //实现用户名查找

    /**
     * error：为存在重复的名称
     * success：为不存在
     * @param keyword
     * @return
     */
    @RequestMapping("/Register/findusername")
    @ResponseBody
    @CrossOrigin
    public JSONObject findusername(@RequestParam(name = "keyword")String keyword, HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin", "*");
        Map<String,Object> msg=new HashMap<>();
        if (userService.findUSERSByNAME(keyword)==null){
            msg.put("msg","success");
        }else {
            msg.put("msg","error");
        }

        return  JSONObject.fromObject(msg);

    }

    /**
     * 文件上传
     * @param file
     * @return
     * @throws IOException
     */

    @RequestMapping(value = "/fileUpload",method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin
    public JSONObject fileUpload(@RequestParam(value = "file") MultipartFile file ,HttpServletResponse response) throws IOException {

            response.setHeader("Access-Control-Allow-Origin", "*");
        Map<String,Object> msg=new HashMap<>();
        if (file==null){
            msg.put("msg","文件不能为空");

        }else {
            msg.put("msg","https://campus.gbdev.cn:8060/fuyaweb"+ ImageUtil.getImage(file));
        }

        return JSONObject.fromObject(msg);

    }





}
