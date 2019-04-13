package com.fuya.shiro;

import com.fuya.Redis.Util.RedisUtil;
import com.fuya.fuyadao.entity.USERS;
import com.fuya.fuyaservice.PERMISSIONService;
import com.fuya.fuyaservice.USERService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class MyShiroRealm extends AuthorizingRealm {


    @Autowired
    private USERService userService;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private PERMISSIONService permissionService;
   /* @Autowired
    private USERSSolrservice usersSolrservice;*/


    //    执行授权逻辑
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

       // 获取登录名
        String name= (String) principalCollection.getPrimaryPrincipal();
        System.out.println("name: "+name);
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session=request.getSession();
        int type= (int) session.getAttribute("type");

        if (name.equals("")||name==null){
            return null;
        }



        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        List<String>roles=new ArrayList<>();
        //添加角色和权限
        if (type==1){
            //普通用户
            roles.add("users");

        }else if(type==2){
            //企业用户
            roles.add("company");

        }else if(type==3||type==7){
            //月嫂用户
            roles.add("yuesaos");

        }else if(type==5){
            roles.add("admin");

        }
        simpleAuthorizationInfo.addRoles(roles);

        //将角色添加到数据库中
//            simpleAuthorizationInfo.addRoles(roles);
//        simpleAuthorizationInfo.addStringPermissions(permissions);
        return simpleAuthorizationInfo;




      // return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //认证逻辑
        //null unknowAccount

        UsernamePasswordToken token= (UsernamePasswordToken) authenticationToken;
        String name= token.getUsername();




        USERS users =userService.findUSERSByNAME(name);
        System.out.println(users.getNAME());
        System.out.println(users.getPASSWORD());
        if (users==null){
            return null;
        }else{

            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            HttpSession session=request.getSession();
            session.setAttribute("id",users.getUSERSID());
            session.setAttribute("type",users.getTYPE());
            return new SimpleAuthenticationInfo(users.getNAME(),users.getPASSWORD(),getName());
        }




    }
}
