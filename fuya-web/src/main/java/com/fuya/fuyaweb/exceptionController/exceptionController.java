package com.fuya.fuyaweb.exceptionController;

import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;


@ControllerAdvice
public class exceptionController {
    @ExceptionHandler(value = UnauthorizedException .class)//处理访问方法时权限不足问题
    public String defaultErrorHandler(HttpServletRequest req,Exception e){
        return "redirect:Login";
    }
}
