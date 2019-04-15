package com.fuya.fuyaweb.IndexRegisterLoginController;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    /**
     * 跳转登录页面
     * @return
     */
    @RequestMapping("/Login")
    public  String Login(){

        return "login";
    }
    @RequestMapping("/403")
    public  String error(){

        return "/403";
    }

    /**
     * 确认登陆信息
     * @param name
     * @param password
     * @param model
     * @param request
     * @return
     */

    @RequestMapping("/CheckLogin")
    public  String CheckLogin(String name , String password, Model model, HttpServletRequest request){
        HttpSession session=request.getSession();
        //获取subject
        Subject subject= SecurityUtils.getSubject();
        session.setAttribute("password",password);
        //获取token
        UsernamePasswordToken token=new UsernamePasswordToken(name,password);
        //验证后跳转
        try{
            //进行验证，这里可以捕获异常，然后返回对应信息
            subject.login(token);
        }
        catch(Exception e){
            //跳转失败
            System.out.println("密码或者用户名错误");
            model.addAttribute("Msg","密码或者用户名错误");
            return "redirect:Login";
        }
        //将用户名

        session.setAttribute("username",name);

        return "index";

    }
    //登出
    @RequestMapping(value = "/logout")
    public String logout(HttpServletRequest request){
        //清除session
        HttpSession session=request.getSession();
        session.removeAttribute("username");
        session.removeAttribute("id");
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "index";
    }





}
