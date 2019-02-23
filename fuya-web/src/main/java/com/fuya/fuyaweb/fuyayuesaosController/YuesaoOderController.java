package com.fuya.fuyaweb.fuyayuesaosController;

import com.fuya.fuyadao.model.ODERSEMPCommentMSG;
import com.fuya.fuyadao.model.ODERSEMPMSG;
import com.fuya.fuyaservice.ORDERSService;
import net.sf.json.JSONObject;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class YuesaoOderController {


    @Autowired
    ORDERSService ordersService;

    //当前订单
    @RequiresRoles("yuesaos")
    @RequestMapping("/fuyayuesaos/recentOrder")
    @ResponseBody
    public JSONObject recentOrder(HttpServletRequest request, @RequestParam(name = "start",defaultValue = "0")int start,@RequestParam(name = "rows",defaultValue = "10")int rows){
        HttpSession session=request.getSession();
        int id= (int) session.getAttribute("id");
        int sumstatus=1;//未完成
        List<ODERSEMPMSG> odersempmsgList= ordersService.findODERSEMPMSGByTOID(id,sumstatus);
        Map<String,Object> msg=new HashMap<>();
        msg.put("msg",odersempmsgList);
        return JSONObject.fromObject(msg);

    }
    //历史订单
    @RequiresRoles("yuesaos")
    @RequestMapping("/fuyayuesaos/historyOrder")
    @ResponseBody
    public JSONObject historyOrder(HttpServletRequest request,@RequestParam(name = "start",defaultValue = "0")int start,@RequestParam(name = "rows",defaultValue = "10")int rows){
        HttpSession session=request.getSession();
        int id= (int) session.getAttribute("id");
        int sumstatus=2;//未完成
       List<ODERSEMPCommentMSG>odersempCommentMSGList=ordersService.findODERSEMPCommentMSGByTOID(id,sumstatus);
        Map<String,Object> msg=new HashMap<>();
        msg.put("msg",odersempCommentMSGList);
        return JSONObject.fromObject(msg);

    }

}
