package com.fuya.fuyaweb.RegisterLoginController;

import com.fuya.fuyadao.entity.*;
import com.fuya.fuyaservice.COMPANYINFOService;
import com.fuya.fuyaservice.PROVEINFOService;
import com.fuya.fuyaservice.USERService;
import com.fuya.fuyaservice.YUESOBASICINFOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
        yuesobasicinfo.setUSERSID(users.getID());
        yuesobasicinfo.setWORKAREA(workarea);


        //证明存入
        PROVEINFO proveinfo=new PROVEINFO();
        proveinfo.setHEALTHCERTIFICATES(healthcertificates);
        proveinfo.setREPORT(report);
        proveinfo.setSCORE(score);
        proveinfo.setSERVICEPICTURE(servicepiceture);
        proveinfo.setYUESAOSYNDROME(yuesaosyndrome);
        proveinfo.setUSERSID(users.getID());

        proveinfoService.save(proveinfo);



        //存入数据库,同时存入solr库






        //类型为4
        //照片上传为回显功能



        //跳转到下一个页面
        return "redirect:/Login";
    }



    //企业用户注册2
    public String companyRegister(String name,String phone,String password,
                                  String corporatename,String email,String licene,
                                  String contactname,String contantphone,String liceneno,String idcard,
                                  String address,String idcardfile){

        //未被审核不能被查找到
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
        companybasicinfo.setUSERID(users.getID());
        COMPANYINFO companyinfo=new COMPANYINFO();
        companyinfo.setADDRESS(address);
        companyinfo.setCONTACTNAME(contactname);
        companyinfo.setUSERSID(users.getID());
        companyinfo.setLICENCENO(liceneno);
        companyinfo.setLICENCE(licene);
        companyinfo.setEMAIL(email);
        companyinfo.setIDCARDFILE(idcardfile);
        companyinfo.setCONTACTPHONE(contantphone);
        




        return "redirect:/Login";
    }

}
