package com.fuya.shiro;

import com.fuya.fuyadao.entity.USERS;
import com.fuya.fuyaservice.USERService;
import org.apache.shiro.authc.*;

import org.apache.shiro.authz.AuthorizationInfo;

import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

public class MyShiroRealm extends AuthorizingRealm {


    @Autowired
    private USERService userService;

    //    执行授权逻辑
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取登录名
//        String name= (String) principalCollection.getPrimaryPrincipal();
//        //添加角色和权限
//        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
//        //添加角色
//        simpleAuthorizationInfo.addRole("admin");
//        simpleAuthorizationInfo.addStringPermission("create");

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
