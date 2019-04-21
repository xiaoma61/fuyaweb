package com.fuya.fuyaweb.usersController;

import com.fuya.Configuration.DateJsonValueProcessor;
import com.fuya.fuyadao.model.OrderYuesaoCommentServiceEmployee;
import com.fuya.fuyaservice.COMMENTSService;
import com.fuya.fuyaservice.ORDERSService;
import com.fuya.fuyaservice.SERVICECONTENTService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@CrossOrigin
public class UserOderController {


    //我的订单
    @Autowired
    COMMENTSService commentsService;
    @Autowired
    ORDERSService ordersService;
    @Autowired
    SERVICECONTENTService servicecontentService;

    @RequiresRoles("users")
    @RequestMapping("/users/OrdersRecent/")
    @ResponseBody
    @CrossOrigin
    JSONObject UsersOrdersRecent( HttpServletRequest request,@RequestParam(name = "start",defaultValue = "0")int start,@RequestParam(name = "rows",defaultValue = "10")int rows){
        HttpSession session=request.getSession();
        int id= (int) session.getAttribute("id");
        //查找订单目前
        int status=2;
        List<OrderYuesaoCommentServiceEmployee>orderYuesaoCommentServiceEmployeeList=ordersService.findOrderYuesaoCommentServiceEmployeeByFROMID(id,status);
        PageHelper.startPage(start,rows);
        PageInfo<OrderYuesaoCommentServiceEmployee>pageInfo=new PageInfo<>(orderYuesaoCommentServiceEmployeeList);


        Map<String,Object> msg=new HashMap<>();
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor("yyyy-MM-dd HH:mm:ss"));
        msg.put("msg",pageInfo);
        return JSONObject.fromObject(msg,jsonConfig);
    }
    //历史状态
    @RequiresRoles("users")
    @RequestMapping("/users/Ordershistory/")
    @ResponseBody
    JSONObject UsersOrdershistory( HttpServletRequest request,@RequestParam(name = "start",defaultValue = "0")int start,@RequestParam(name = "rows",defaultValue = "10")int rows){
        HttpSession session=request.getSession();
        int id= (int) session.getAttribute("id");
        int status=1;
        List<OrderYuesaoCommentServiceEmployee>orderYuesaoCommentServiceEmployeeList=ordersService.findOrderYuesaoCommentServiceEmployeeByFROMID(id,status);
        PageHelper.startPage(start,rows);
        PageInfo<OrderYuesaoCommentServiceEmployee>pageInfo=new PageInfo<>(orderYuesaoCommentServiceEmployeeList);
        Map<String,Object> msg=new HashMap<>();
        msg.put("msg",pageInfo);
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor("yyyy-MM-dd HH:mm:ss"));
        return JSONObject.fromObject(msg,jsonConfig);
    }


    //订单
    @RequiresRoles("users")
    @RequestMapping("/users/OrdersRecent/id")
    @ResponseBody
    JSONObject UsersOrdershistory( HttpServletRequest request,@RequestParam(name = "id")int id){

        OrderYuesaoCommentServiceEmployee orderYuesaoCommentServiceEmployee=ordersService.findOrderYuesaoCommentServiceEmployeeByOderID(id);
        Map<String,Object> msg=new HashMap<>();
        msg.put("msg",orderYuesaoCommentServiceEmployee);
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor("yyyy-MM-dd HH:mm:ss"));
        return JSONObject.fromObject(msg,jsonConfig);
    }
    //退款
    //订单支付状态
    @RequiresRoles("users")
    @RequestMapping("/users/OrdersRecent/handselreturn/id")
    @ResponseBody
    JSONObject UsersOrdershandselreturn(HttpServletRequest request, @RequestParam(name = "id")int id){
        //退款定金
        int status=3;
        servicecontentService.updateHANDSELSTATUSbySERVICECONTENTID(status,id);
        Map<String,Object> msg=new HashMap<>();
        msg.put("msg","success");
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor("yyyy-MM-dd HH:mm:ss"));
        return JSONObject.fromObject(msg,jsonConfig);
    }
    //退款
    //订单支付状态
    @RequiresRoles("users")
    @RequestMapping("/users/OrdersRecent/sumreturn/id")
    @ResponseBody
    JSONObject UsersOrderssumreturn(HttpServletRequest request, @RequestParam(name = "id")int id){
        //退款定金
        int status=3;

        servicecontentService.updatebySERVICECONTENTID(status,id);
        Map<String,Object> msg=new HashMap<>();
        msg.put("msg","success");
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor("yyyy-MM-dd HH:mm:ss"));
        return JSONObject.fromObject(msg,jsonConfig);
    }
   //支付全款
   @RequiresRoles("users")
   @RequestMapping("/users/OrdersRecent/sum/id")
   @ResponseBody
   JSONObject UsersOrderssum(HttpServletRequest request, @RequestParam(name = "id")int id){
       //退款定金
       int status=2;
       servicecontentService.updatebySERVICECONTENTID(status,id);
       Map<String,Object> msg=new HashMap<>();
       msg.put("msg","success");
       JsonConfig jsonConfig = new JsonConfig();
       jsonConfig.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor("yyyy-MM-dd HH:mm:ss"));
       return JSONObject.fromObject(msg,jsonConfig);
   }


}
