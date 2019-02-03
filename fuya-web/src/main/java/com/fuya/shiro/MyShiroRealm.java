package com.fuya.shiro;

import com.fuya.fuyadao.entity.USERS;
import com.fuya.fuyaservice.USERService;
import org.apache.catalina.manager.util.SessionUtils;
import org.apache.shiro.authc.*;

import org.apache.shiro.authz.AuthorizationInfo;

import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class MyShiroRealm extends AuthorizingRealm {


    @Autowired
    private USERService userService;

    //    执行授权逻辑
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
       // 获取登录名
        String name= (String) principalCollection.getPrimaryPrincipal();
        USERS users =userService.findUSERSByNAME(name);
        //添加角色和权限
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        //添加角色

        if (users!=null){
            List<String> permissions=new ArrayList<>();
            List<String> roles =new ArrayList<>();
        if (users.getTYPE()==1){
            //普通用户 1
            permissions.add("Personal");
            roles.add("Personal");

        }
        if (users.getTYPE()==2){
            //企业用户 2
            permissions.add("Company");
            roles.add("Company");
            //不能实现收藏
        }
        if (users.getTYPE()==3){
            //月嫂用户 3
            permissions.add("Yuesao");
            roles.add("Yuesao");
        }
            if (users.getTYPE()==4){
                //月嫂用户 3
                permissions.add("Admin");
                roles.add("Admin");
                //不能实现收藏
            }

            simpleAuthorizationInfo.addRoles(roles);
            simpleAuthorizationInfo.addStringPermissions(permissions);
            return simpleAuthorizationInfo;
        }



       return null;
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

            return new SimpleAuthenticationInfo(users.getNAME(),users.getPASSWORD(),getName());
        }




    }
}
