package com.fuya.fuyaweb.IndexcompanyController;

import com.fuya.Redis.Util.RedisUtil;

import com.fuya.fuyadao.entity.*;
import com.fuya.fuyadao.model.Yuesaolistyuesaomodel;
import com.fuya.fuyaservice.COMPANYBASICINFOService;
import com.fuya.fuyaservice.COMPANYYUESAOService;
import com.fuya.fuyaservice.YUESOBASICINFOService;
import com.fuya.fuyautil.EntityUtils;
import com.fuya.fuyautil.JpaPageHelperUtil;
import com.fuya.fuyautil.TypeUtil;
import com.github.pagehelper.PageInfo;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.function.Function;

@Controller
@CrossOrigin
public class IndexcompanyController {
    //公司列表
    @Autowired
    RedisUtil redisUtil;
    @Autowired
    COMPANYBASICINFOService companybasicinfoService;
    @Autowired
    COMPANYYUESAOService companyyuesaoService;
    @Autowired
    YUESOBASICINFOService yuesobasicinfoService;

    @RequestMapping("/company/list")
    @ResponseBody
    public JSONObject companylist(@RequestParam(name = "start",defaultValue = "0")int start, @RequestParam(name = "rows",defaultValue = "10")int rows, HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin", "*");
        Page<COMPANYBASICINFO>companybasicinfos=companybasicinfoService.findALL(start,rows);


        Map<String,Object> msg=new HashMap<>();
        msg.put("msg", companybasicinfos);
        return JSONObject.fromObject(msg);
    }
    //公司信息
    @RequestMapping("/company/msg/id")
    @ResponseBody
    public JSONObject companymagid(@RequestParam(name = "id",defaultValue = "0")int id,HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin", "*");

        Companymsgidinfo companymsgidinfo=new Companymsgidinfo();

        Object[] object= (Object[]) companybasicinfoService.findidMsg(id);

        companymsgidinfo.setCONTACTNAME(object[0].toString());
        companymsgidinfo.setCONTACTPHONE(object[1].toString());
        companymsgidinfo.setADDRESS(object[2].toString());
        companymsgidinfo.setNUMS((Integer) object[3]);
        companymsgidinfo.setCORPORATENAME(object[4].toString());
        companymsgidinfo.setLEVELS((Integer) object[5]);
        companymsgidinfo.setLICENCE(object[6].toString());
        companymsgidinfo.setCOMPANYBASICINFOID((Integer) object[7]);
        companymsgidinfo.setUSERID((Integer) object[8]);

        Map<String,Object> msg=new HashMap<>();
        msg.put("msg", companymsgidinfo);
        return JSONObject.fromObject(msg);
    }

    //公司员工列表
    @RequestMapping("/company/yuesaolist")
    @ResponseBody
    public Map<String, Object> companyyuesao(@RequestParam(name = "userid",defaultValue = "0")int userid,
                                             @RequestParam(name = "start",defaultValue = "0")int start, @RequestParam(name = "rows",defaultValue = "10")int rows,
                                             HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin", "*");

        Pageable page= PageRequest.of(start,rows);
        Page<String> companyyuesaoList=companyyuesaoService.findByYUESAOCOMPANYID(userid,page);

        Map<String,Object> msg=new HashMap<>();
        msg.put("msg", companyyuesaoList);
        return msg;
    }
    //搜索月嫂
    @RequestMapping("/company/yuesaolist/search")//名字
    @ResponseBody
    public  JSONObject Search (@RequestParam(name="name")String name,
                                   @RequestParam(name = "workarea",defaultValue = "null")String workarea,
                                   @RequestParam(name = "type",defaultValue = "1")int type,
                                   @RequestParam(name = "minwages",defaultValue = "null")String minwages,
                                   @RequestParam(name = "maxwages",defaultValue = "null")String maxwages,
                                   @RequestParam(name = "nativeplace",defaultValue = "null")String nativeplace,
                                   @RequestParam(name = " minage" ,defaultValue = "40")String  minage,
                                  @RequestParam(name = " maxage" ,defaultValue = "null")String  maxage,
                                   @RequestParam(name = "start",defaultValue = "0") int start,
                                   @RequestParam(name = "rows",defaultValue = "10") int rows,@RequestParam(name = "userid",defaultValue = "0")int userid
    ,HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin", "*");

    Page<YUESOBASICINFO>yuesobasicinfos=yuesobasicinfoService.query(name, workarea,  minwages, maxwages,type, nativeplace, minage, maxage, start, rows,userid);
           List<Yuesaolistyuesaomodel>yuesobasicinfoList=new ArrayList<>();
        List<COMPANYYUESAO> companyyuesaoList=companyyuesaoService.findByRealCOMPANYID(userid);
        for (YUESOBASICINFO yuesobasicinfo: yuesobasicinfos.getContent()){

            for (COMPANYYUESAO companyyuesao: companyyuesaoList){
                if(companyyuesao.getYUESAOID()==yuesobasicinfo.getUSERSID()&&companyyuesao.getCOMPANYID()==userid)
                {
                    Yuesaolistyuesaomodel yuesaolistyuesaomodel=new Yuesaolistyuesaomodel();
                    yuesaolistyuesaomodel.setAGE(String.valueOf(yuesobasicinfo.getAGE()));
                    yuesaolistyuesaomodel.setTYPE(TypeUtil.getTypeUtil(yuesobasicinfo.getTYPE()));
                    yuesaolistyuesaomodel.setNATIVEPLACE(yuesobasicinfo.getNATIVEPLACE());
                    yuesaolistyuesaomodel.setWAGES(yuesobasicinfo.getWAGES());
                    yuesaolistyuesaomodel.setPHOTO(yuesobasicinfo.getPHOTO());
                    yuesaolistyuesaomodel.setNAME(yuesobasicinfo.getNAME());
                    yuesaolistyuesaomodel.setLEVELS(yuesobasicinfo.getLEVELS());
                    yuesaolistyuesaomodel.setUSERSID(yuesobasicinfo.getUSERSID());
                    yuesobasicinfoList.add(yuesaolistyuesaomodel);

                }
            }



        }

        PageInfo problemAnswerPageInfo= JpaPageHelperUtil.SetStartPage(yuesobasicinfoList,start+1,rows);

        Map<String,Object> msg=new HashMap<>();
      /*  msg.put("msg", problemAnswerPageInfo);*/
        return JSONObject.fromObject(msg);

    }

    //搜索月嫂
    @RequestMapping("/company/yuesaolist/name")//名字
    @ResponseBody
    public  JSONObject SearchName (@RequestParam(name="name")String name,
                               @RequestParam(name = "workarea",defaultValue = "null")String workarea,
                               @RequestParam(name = "type",defaultValue = "null")int type,
                               @RequestParam(name = "minwages",defaultValue = "null")String minwages,
                               @RequestParam(name = "maxwages",defaultValue = "null")String maxwages,
                               @RequestParam(name = "nativeplace",defaultValue = "null")String nativeplace,
                               @RequestParam(name = " minage" ,defaultValue = "null")String  minage,
                               @RequestParam(name = " maxage" ,defaultValue = "null")String  maxage,
                               @RequestParam(name = "start",defaultValue = "0") int start,
                               @RequestParam(name = "rows",defaultValue = "10") int rows,@RequestParam(name = "userid",defaultValue = "0")int userid,HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin", "*");
        Page<YUESOBASICINFO>yuesobasicinfos=yuesobasicinfoService.query(name, workarea, minwages, maxwages,type , nativeplace, minage, maxage, start, rows,userid);
        List<YUESOBASICINFO>yuesobasicinfoList=yuesobasicinfos.getContent();
        List<COMPANYYUESAO> companyyuesaoList=companyyuesaoService.findByRealCOMPANYID(userid);
        HashSet<String>hashSet=new HashSet<>();
        for (YUESOBASICINFO yuesobasicinfo:yuesobasicinfoList){
            for (COMPANYYUESAO companyyuesao: companyyuesaoList){
                if(companyyuesao.getYUESAOID()==yuesobasicinfo.getUSERSID()&&companyyuesao.getCOMPANYID()==userid)
                {
                    if (hashSet.size()<5){
                        hashSet.add(yuesobasicinfo.getNAME());
                    }
                }
            }

        }
        Map<String,Object> msg=new HashMap<>();
        msg.put("msg", hashSet);
        return JSONObject.fromObject(msg);

    }
}
