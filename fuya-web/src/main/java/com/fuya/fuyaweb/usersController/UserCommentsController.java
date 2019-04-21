package com.fuya.fuyaweb.usersController;

import com.fuya.fuyadao.entity.COMMENTS;
import com.fuya.fuyadao.entity.YUESOBASICINFO;
import com.fuya.fuyadao.model.YuesaobasicInfo;
import com.fuya.fuyaservice.COMMENTSService;
import com.fuya.fuyaservice.YUESOBASICINFOService;
import net.sf.json.JSONObject;
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
import java.util.Map;

@Controller
@CrossOrigin
public class UserCommentsController {
    @Autowired
    COMMENTSService commentsService;
    @Autowired
    YUESOBASICINFOService yuesobasicinfoService;
    //添加评论
    @RequiresRoles("users")
    @RequestMapping("/users/comments/add")
    @ResponseBody
    @CrossOrigin
    JSONObject Userscommentsadd(@RequestParam(name = "orderid",defaultValue = "2")int orderid, @RequestParam(name = "content",defaultValue = "10")String content,
                               @RequestParam(name = "levels",defaultValue = "0")int levels,HttpServletRequest request){

        HttpSession session=request.getSession();
        int id= (int) session.getAttribute("id");
        COMMENTS comments=new COMMENTS();
        comments.setORDERID(orderid);
        comments.setLEVELS(levels);
        comments.setUSERID(id);
        comments.setCONTENT(content);
        commentsService.save(comments);
        Map<String,Object> msg=new HashMap<>();
        msg.put("msg","success");
        return JSONObject.fromObject(msg);
    }


    //评论页面
    @RequiresRoles("users")
    @RequestMapping("/users/comments/yuesao")
    @ResponseBody
    JSONObject Userscommentsyuesao(@RequestParam(name = "userid")int userid){


        YUESOBASICINFO yuesobasicinfo=yuesobasicinfoService.findByUSERSID(userid);
        YuesaobasicInfo yuesaobasicInfo=new YuesaobasicInfo();
        yuesaobasicInfo.setId(yuesobasicinfo.getUSERSID());
        yuesaobasicInfo.setImage(yuesobasicinfo.getPHOTO());
        yuesaobasicInfo.setName(yuesobasicinfo.getNAME());
        yuesaobasicInfo.setLevels(yuesobasicinfo.getLEVELS());


        Map<String,Object> msg=new HashMap<>();
        msg.put("msg",yuesaobasicInfo);
        return JSONObject.fromObject(msg);
    }
}
