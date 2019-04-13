package com.fuya.fuyaweb.IndexcompanyController;

import com.fuya.Redis.Util.RedisUtil;
import com.fuya.fuyadao.entity.COMPANYYUESAO;
import com.fuya.fuyadao.entity.Companylistcompanyinfo;
import com.fuya.fuyadao.entity.Companymsgidinfo;
import com.fuya.fuyadao.entity.YUESOBASICINFO;
import com.fuya.fuyadao.model.Yuesaolistyuesaomodel;
import com.fuya.fuyaservice.COMPANYBASICINFOService;
import com.fuya.fuyaservice.COMPANYYUESAOService;
import com.fuya.fuyaservice.YUESOBASICINFOService;
import com.fuya.fuyautil.JpaPageHelperUtil;
import com.fuya.fuyautil.TypeUtil;
import com.github.pagehelper.PageInfo;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Controller
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

    @RequestMapping("/companylist")
    @ResponseBody
    public JSONObject companylist(@RequestParam(name = "start",defaultValue = "0")int start, @RequestParam(name = "rows",defaultValue = "10")int rows){

            List<Companylistcompanyinfo>companylistcompanyinfoList=new ArrayList<>();
            List<Object>objectList=companybasicinfoService.find();
            for (int i=0;i<objectList.size();i++){
                Object[]objects=(Object[])objectList.get(i);
                Companylistcompanyinfo companylistcompanyinfo=new Companylistcompanyinfo();
                companylistcompanyinfo.setCOMPANYBASICINFOID((Integer) objects[0]);
                companylistcompanyinfo.setCORPORATENAME(objects[1].toString());
                companylistcompanyinfo.setADDRESS(objects[2].toString());
                companylistcompanyinfo.setNUMS((Integer) objects[3]);
                companylistcompanyinfo.setLEVELS((Integer) objects[4]);
                companylistcompanyinfo.setINTRODUCE(objects[5].toString());
                companylistcompanyinfo.setUSERSID((Integer) objects[6]);
                companylistcompanyinfo.setLICENCE(objects[7].toString());
                companylistcompanyinfoList.add(companylistcompanyinfo);
            }
        List<PageInfo>problemAnswerPageInfo= JpaPageHelperUtil.SetStartPage(companylistcompanyinfoList,start+1,rows);
//                redisUtil.zadd(name,i,);
//            }
//
//        }
//        Page<COMPANYBASICINFO> List=companybasicinfoService.findALL(start,rows);
        Map<String,Object> msg=new HashMap<>();
        msg.put("msg", problemAnswerPageInfo);
        return JSONObject.fromObject(msg);
    }
    //公司信息
    @RequestMapping("/company/msg/id")
    @ResponseBody
    public JSONObject companymagid(@RequestParam(name = "id",defaultValue = "0")int id){

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
    public JSONObject companyyuesao(@RequestParam(name = "userid",defaultValue = "0")int userid,@RequestParam(name = "start",defaultValue = "0")int start, @RequestParam(name = "rows",defaultValue = "10")int rows){

        List<COMPANYYUESAO> companyyuesaoList=companyyuesaoService.findByRealCOMPANYID(userid);
        List<Yuesaolistyuesaomodel>objectLists=new ArrayList<>();
        for (COMPANYYUESAO companyyuesao : companyyuesaoList){
            Object[] object= (Object[]) yuesobasicinfoService.findObjectByUSERSID(companyyuesao.getYUESAOID());
            Yuesaolistyuesaomodel yuesaolistyuesaomodel=new Yuesaolistyuesaomodel();
            yuesaolistyuesaomodel.setAGE(object[0].toString());
            yuesaolistyuesaomodel.setTYPE(object[1].toString());
            yuesaolistyuesaomodel.setNATIVEPLACE(object[2].toString());
            yuesaolistyuesaomodel.setWAGES(object[3].toString());
            yuesaolistyuesaomodel.setPHOTO(object[4].toString());
            yuesaolistyuesaomodel.setNAME(object[5].toString());
            yuesaolistyuesaomodel.setLEVELS((Integer) object[6]);
            yuesaolistyuesaomodel.setUSERSID((Integer) object[7]);
            objectLists.add(yuesaolistyuesaomodel);

        }
        List<PageInfo>problemAnswerPageInfo= JpaPageHelperUtil.SetStartPage(objectLists,start+1,rows);
//
//

        Map<String,Object> msg=new HashMap<>();
        msg.put("msg", problemAnswerPageInfo);
        return JSONObject.fromObject(msg);
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
                                   @RequestParam(name = "rows",defaultValue = "10") int rows,@RequestParam(name = "userid",defaultValue = "0")int userid){

        Page<YUESOBASICINFO>yuesobasicinfos=yuesobasicinfoService.query(name, workarea,  minwages, maxwages,type, nativeplace, minage, maxage, start, rows);
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
                    yuesaolistyuesaomodel.setWAGES(String.valueOf(yuesobasicinfo.getWAGES()));
                    yuesaolistyuesaomodel.setPHOTO(yuesobasicinfo.getPHOTO());
                    yuesaolistyuesaomodel.setNAME(yuesobasicinfo.getNAME());
                    yuesaolistyuesaomodel.setLEVELS(yuesobasicinfo.getLEVELS());
                    yuesaolistyuesaomodel.setUSERSID(yuesobasicinfo.getUSERSID());
                    yuesobasicinfoList.add(yuesaolistyuesaomodel);

                }
            }



        }

        List<PageInfo>problemAnswerPageInfo= JpaPageHelperUtil.SetStartPage(yuesobasicinfoList,start+1,rows);
        problemAnswerPageInfo.get(0).setTotal(yuesobasicinfos.getTotalElements());
        problemAnswerPageInfo.get(0).setPageSize(yuesobasicinfos.getTotalPages());
        problemAnswerPageInfo.get(0).setIsFirstPage(yuesobasicinfos.isFirst());
        problemAnswerPageInfo.get(0).setIsLastPage(yuesobasicinfos.isLast());

        problemAnswerPageInfo.get(0).setStartRow(yuesobasicinfos.getNumber());
        problemAnswerPageInfo.get(0).setEndRow(yuesobasicinfos.getNumberOfElements());
//        problemAnswerPageInfo.get(0).set(yuesobasicinfos.getNumberOfElements());
        Map<String,Object> msg=new HashMap<>();
        msg.put("msg", problemAnswerPageInfo);
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
                               @RequestParam(name = "rows",defaultValue = "10") int rows,@RequestParam(name = "userid",defaultValue = "0")int userid){
        Page<YUESOBASICINFO>yuesobasicinfos=yuesobasicinfoService.query(name, workarea, minwages, maxwages,type , nativeplace, minage, maxage, start, rows);
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
