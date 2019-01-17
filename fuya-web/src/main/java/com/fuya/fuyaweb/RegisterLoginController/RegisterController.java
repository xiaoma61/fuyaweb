package com.fuya.fuyaweb.RegisterLoginController;

import com.fuya.fuyadao.entity.USERS;
import com.fuya.fuyaservice.USERService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RegisterController {

    @Autowired
    private USERService userService;

    @RequestMapping("/Register")
    String Register(String name,String password,String phone,int type){
        USERS users=new USERS();
        users.setNAME(name);
        users.setPASSWORD(password);
        users.setPHONE(phone);
        users.setTYPE(type);
        userService.save(users);
        return "redirect:/Login";
    }
}
