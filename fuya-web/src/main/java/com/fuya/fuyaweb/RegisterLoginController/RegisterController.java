package com.fuya.fuyaweb.RegisterLoginController;

import com.fuya.fuyadao.entity.USERS;
import com.fuya.fuyaservice.USERService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegisterController {

    @Autowired
    private USERService userService;

    //普通用户注册
    @RequestMapping("/Register")
    String Register(String name, String password, String phone, @RequestParam(name="type" ,defaultValue = "1")int type){
        USERS users=new USERS();
        users.setNAME(name);
        users.setPASSWORD(password);
        users.setPHONE(phone);
        users.setTYPE(type);
        userService.save(users);
        return "redirect:/Login";
    }
    //月嫂用户注册3
    @RequestMapping("/yuesaoRegister")
    String yuesaoRegister(String name, String password, String phone, @RequestParam(name="type" ,defaultValue = "1")int type){


        
        //跳转到下一个页面
        return "redirect:/Login";
    }



    //企业用户注册2

}
