package com.fuya.fuyaweb.fuyayuesaosController;

import com.fuya.fuyadao.entity.SKILL;
import com.fuya.fuyadao.entity.YUESAOOTHERPROVE;
import com.fuya.fuyadao.model.PROVEINFOANDBAISINFO;
import com.fuya.fuyaservice.PROVEINFOService;
import com.fuya.fuyaservice.SKILLService;
import com.fuya.fuyaservice.YUESAOOTHERPROVEService;
import com.fuya.fuyaservice.YUESOBASICINFOService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
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
    public JSONObject personallist(HttpServletRequest request){
        HttpSession session=request.getSession();
        int id= (int) session.getAttribute("id");
        //查找
        List<PROVEINFOANDBAISINFO> objectList=proveinfoService.findPROVEINFOByAndYUESAOBASICINFOByUSERSID(id);


        Map<String,Object> msg=new HashMap<>();
        msg.put("msg",objectList);
        return JSONObject.fromObject(msg);
    }
    //修改

    //技巧
    @RequiresRoles("yuesaos")
    @RequestMapping("/fuyayuesaos/skillinfo")
    @ResponseBody
    public JSONObject skillinfo(HttpServletRequest request){
        HttpSession session=request.getSession();
        int id= (int) session.getAttribute("id");
        List<SKILL> skillList=skillService.findByUSERID(id,1);
        List<SKILL> skillList1=skillService.findByUSERID(id,2);
        skillList.addAll(skillList1);
        Map<String,Object> msg=new HashMap<>();
        msg.put("msg",skillList);
        return JSONObject.fromObject(msg);
    }
    //证明
    @RequiresRoles("yuesaos")
    @RequestMapping("/fuyayuesaos/proveinfo")
    @ResponseBody
    public JSONObject proveinfo(HttpServletRequest request){
        HttpSession session=request.getSession();
        int id= (int) session.getAttribute("id");

        List<YUESAOOTHERPROVE>yuesaootherproveList=yuesaootherproveService.findByUSERID(id);
        Map<String,Object> msg=new HashMap<>();
        msg.put("msg",yuesaootherproveList);
        return JSONObject.fromObject(msg);
    }


}
