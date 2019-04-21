package com.fuya.fuyaweb.fuyayuesaosController;

import com.fuya.fuyadao.entity.SKILL;
import com.fuya.fuyadao.entity.YUESAOOTHERPROVE;
import com.fuya.fuyadao.model.PROVEINFOANDBAISINFO;
import com.fuya.fuyaservice.PROVEINFOService;
import com.fuya.fuyaservice.SKILLService;
import com.fuya.fuyaservice.YUESAOOTHERPROVEService;
import com.fuya.fuyaservice.YUESOBASICINFOService;
import com.fuya.fuyautil.uuidUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@CrossOrigin
public class YuesaoPersonalController {
    @Autowired
    YUESOBASICINFOService yuesobasicinfoService;
    @Autowired
    PROVEINFOService proveinfoService;
    @Autowired
    SKILLService skillService;
    @Autowired
    YUESAOOTHERPROVEService yuesaootherproveService;

    @RequiresRoles("yuesaos")
    @RequestMapping("/fuyayuesaos/personalinfo")
    @ResponseBody
    public JSONObject personallist(HttpServletRequest request,HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin", "*");
        HttpSession session=request.getSession();
        int id= (int) session.getAttribute("id");
        //查找
        List<PROVEINFOANDBAISINFO> objectList=proveinfoService.findPROVEINFOByAndYUESAOBASICINFOByUSERSID(id);
        Map<String,Object> msg=new HashMap<>();
        msg.put("msg",objectList);
        return JSONObject.fromObject(msg);
    }
    //技巧
    @RequiresRoles("yuesaos")
    @RequestMapping("/fuyayuesaos/skillinfo")
    @ResponseBody
    public JSONObject skillinfo(HttpServletRequest request,HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin", "*");
        HttpSession session=request.getSession();
        int id= (int) session.getAttribute("id");
        List<SKILL> skillList=skillService.findByUSERID(id,1);
        List<SKILL> skillList1=skillService.findByUSERID(id,2);
        skillList.addAll(skillList1);
        Map<String,Object> msg=new HashMap<>();
        msg.put("msg",skillList);
        return JSONObject.fromObject(msg);
    }
    //技巧提交
    @RequiresRoles("yuesaos")
    @RequestMapping(value = "/fuyayuesaos/skillinfo/add",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject skillinfoadd(HttpServletRequest request,@RequestParam(name = "skill")String skill,@RequestParam(name = "type")int type,HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin", "*");
        HttpSession session=request.getSession();
        int id= (int) session.getAttribute("id");
        SKILL skill1=new SKILL();
        skill1.setTYPE(type);
        skill1.setSKILL(skill);
        skill1.setUSERID(id);
        skillService.save(skill1);
        Map<String,Object> msg=new HashMap<>();
        msg.put("msg","success");
        return JSONObject.fromObject(msg);
    }
    //证明提交
    @RequiresRoles("yuesaos")
    @RequestMapping("/fuyayuesaos/yuesao/update")
    @ResponseBody
    JSONArray fuyayuesaosyuesaoupdate(


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
            @RequestParam(name = "servicepiceture",defaultValue = "头像") String servicepiceture, @RequestParam(name = "id",defaultValue = "20")int userid, HttpServletRequest request,
            HttpServletResponse response) throws IOException, SolrServerException {
        response.setHeader("Access-Control-Allow-Origin", "*");


        proveinfoService.update(yuesaosyndrome,healthcertificates,report,servicepiceture,userid);
        yuesobasicinfoService.update(idcard,email,workarea,phone,education,name,widght,age,height,nativeplace,photo,seniority,wages,userid);

        //用户关联
        //跳转到下一个页面
        Map<String,Object> msg=new HashMap<>();
        msg.put("msg","success");
        return JSONArray.fromObject(msg);
    }
    //证明1
    @RequiresRoles("yuesaos")
    @RequestMapping("/fuyayuesaos/proveinfo")
    @ResponseBody
    public JSONObject proveinfo(HttpServletRequest request,HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin", "*");
        HttpSession session=request.getSession();
        int id= (int) session.getAttribute("id");

        List<YUESAOOTHERPROVE>yuesaootherproveList=yuesaootherproveService.findByUSERID(id);
        Map<String,Object> msg=new HashMap<>();
        msg.put("msg",yuesaootherproveList);
        return JSONObject.fromObject(msg);
    }
//证明提交1
@RequiresRoles("yuesaos")
@RequestMapping("/fuyayuesaos/proveinfo/add")
@ResponseBody
public JSONObject proveinfoadd(@RequestBody JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response/* ,@RequestParam(name = "file")String file,@RequestParam(name = "title")String title*/){
    response.setHeader("Access-Control-Allow-Origin", "*");
        HttpSession session=request.getSession();
    int id= (int) session.getAttribute("id");

    YUESAOOTHERPROVE yuesaootherprove=new YUESAOOTHERPROVE();

    yuesaootherprove = (YUESAOOTHERPROVE) JSONObject.toBean(jsonObject,YUESAOOTHERPROVE.class);
    yuesaootherprove.setUSERID(id);
    yuesaootherprove.setId(uuidUtil.getuuidUtil());
    /*yuesaootherprove.setFILEAREA(file);
    yuesaootherprove.setTITLE(title);*/
    yuesaootherproveService.save(yuesaootherprove);
    Map<String,Object> msg=new HashMap<>();
    msg.put("msg","success");
    return JSONObject.fromObject(msg);
}

}
