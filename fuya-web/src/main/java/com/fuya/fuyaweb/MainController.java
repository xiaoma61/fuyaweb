package com.fuya.fuyaweb;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@CrossOrigin
public class MainController {

    @RequestMapping("/personal")
    String personal(){
        int type= 0;
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session=request.getSession();
        if ( session.getAttribute("type")!=null){
            type= (int) session.getAttribute("type");
        }
        if (type==1){
            //普通用户
            return "redirect:/html/u_personal.html";


        }else if(type==2){
            //企业用户
            return "redirect:/html/c_personal.html";

        }else if(type==3||type==7){
            //月嫂用户
            return "redirect:/html/n_personal.html";

        }else{
            return "redirect:/html/backstage.html";

        }


    }


}
