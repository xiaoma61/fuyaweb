package com.fuya.fuyaweb.RegisterLoginController;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {
    @RequestMapping("/Login")
    public  String Login(){

        return "login";
    }

    @RequestMapping("/CheckLogin")
    public  String CheckLogin(String name , String password, Model model){
        //获取subject
        Subject subject= SecurityUtils.getSubject();
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
        return "index";

    }
    //登出
    @RequestMapping(value = "/logout")
    public String logout(){
        return "logout";
    }

    //错误页面展示
    @RequestMapping(value = "/error",method = RequestMethod.POST)
    public String error(){
        return "error ok!";
    }


//    @RequestMapping(value = "/index")
//    public String index(){
//        return "index";
//    }


}
