package com.fuya.fuyaweb.usersController;

import com.fuya.fuyadao.entity.COMMENTS;
import com.fuya.fuyaservice.COMMENTSService;
import net.sf.json.JSONArray;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
public class UserCommentsController {
    @Autowired
    COMMENTSService commentsService;
    //添加评论
    @RequiresRoles("users")
    @RequestMapping("/users/comments/add")
    @ResponseBody
    JSONArray Userscommentsadd(@RequestParam(name = "orderid",defaultValue = "2")int orderid, @RequestParam(name = "content",defaultValue = "10")String content,
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
        return JSONArray.fromObject(msg);
    }
}
