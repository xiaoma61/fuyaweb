package com.fuya.shiro;

import com.fuya.Redis.Util.RedisUtil;
import com.fuya.fuyadao.entity.PERMISSION;
import com.fuya.fuyadao.entity.USERS;
import com.fuya.fuyaservice.PERMISSIONService;
import com.fuya.fuyaservice.USERService;
import com.fuya.fuyasolr.Solr.service.USERSSolrservice;


import net.sf.json.JSONObject;
import org.apache.shiro.authc.*;

import org.apache.shiro.authz.AuthorizationInfo;

import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class MyShiroRealm extends AuthorizingRealm {


    @Autowired
    private USERService userService;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private PERMISSIONService permissionService;
    @Autowired
    private USERSSolrservice usersSolrservice;


    //    执行授权逻辑
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

       // 获取登录名
        String name= (String) principalCollection.getPrimaryPrincipal();
        System.out.println("name: "+name);
        List<USERS>usersList=new ArrayList<>();
        if (name.equals("")||name==null){
            return null;
        }
        try {
            usersList=usersSolrservice.searchbyusername(name);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SolrServerException e) {
            e.printStackTrace();
        }
        if (usersList==null||usersList.size()<=0){
            System.out.println("null");
            return null;

        }
        USERS users =usersList.get(0);
        List<String> permissions=new ArrayList<>();
        if (!(redisUtil.scard(String.valueOf(users.getTYPE()))>0)){
            //没有添加
            System.out.println("per");
            List<PERMISSION>permissionList=permissionService.findall();
            for (PERMISSION permission:permissionList){
                JSONObject object=JSONObject.fromObject(permission);
                redisUtil.zSet(permission.getTYPE(), object.toString());
            }
        }else {
            Set<String>perssionsset=redisUtil.smembers(String.valueOf(users.getTYPE()));
            for (String  permssionString :perssionsset){
                //获取权限
                permissions.add(permssionString);
                JSONObject object=JSONObject.fromObject(permssionString);
                PERMISSION permissionObject= (PERMISSION) JSONObject.toBean(object,PERMISSION.class);
                System.out.println(permissionObject.getPERMISSIONS()+"   1111111111");
                permissions.add(permissionObject.getPERMISSIONS());

            }
        }
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        List<String>roles=new ArrayList<>();
        //添加角色和权限
        if (users.getTYPE()==1){
            //普通用户
            roles.add("users");

        }else if(users.getTYPE()==2){
            //企业用户
            roles.add("company");

        }else if(users.getTYPE()==3){
            //月嫂用户
            roles.add("yuesaos");

        }else if(users.getTYPE()==5){
            roles.add("admin");

        }
        simpleAuthorizationInfo.addRoles(roles);

        //将角色添加到数据库中
//            simpleAuthorizationInfo.addRoles(roles);
        simpleAuthorizationInfo.addStringPermissions(permissions);
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
            return new SimpleAuthenticationInfo(users.getNAME(),users.getPASSWORD(),getName());
        }




    }
}
