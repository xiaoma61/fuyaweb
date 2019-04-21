package com.fuya.shiro;

import com.fasterxml.jackson.core.JsonParser;
import com.fuya.Redis.Util.RedisUtil;
import com.fuya.fuyadao.entity.USERS;
import com.fuya.fuyadao.model.UserModel;
import com.fuya.fuyaservice.PERMISSIONService;
import com.fuya.fuyaservice.USERService;
import net.sf.json.JSONObject;
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

    private static  final String KEY="userMsgkey";
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
        int type= 0;
        if ( session.getAttribute("type")!=null){
             type= (int) session.getAttribute("type");
        }


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
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session=request.getSession();
        String password= (String) session.getAttribute("password");
        UsernamePasswordToken token= (UsernamePasswordToken) authenticationToken;
        String name= token.getUsername();
        UserModel userModel =new UserModel();
        String rpassword=null;
        USERS userss=null;
        if(redisUtil.hexists(KEY,name)){
            String user=redisUtil.hGet(KEY,name);
            JSONObject object=JSONObject.fromObject(user);
            userModel= (UserModel) JSONObject.toBean(object,userModel.getClass());
            if(!userModel.getPASSWORD().equals(password)){
                userss=userService.findUSERSByNAME(name);
                rpassword=userss.getPASSWORD();
                session.setAttribute("id",userss.getUSERSID());
                session.setAttribute("type",userss.getTYPE());
            }else {
                rpassword=userModel.getPASSWORD();
                session.setAttribute("id",userModel.getUSERSID());
                session.setAttribute("type",userModel.getTYPE());
            }
        }else {

            List<USERS>usersList=userService.findall();
            for (USERS users:usersList){
                if (users.getNAME().equals(name)){
                    rpassword=users.getPASSWORD();
                }
                redisUtil.hset(KEY, users.getNAME(),JSONObject.fromObject(users).toString());
            }


            return new SimpleAuthenticationInfo(name,rpassword,getName());
        }

        /*System.out.println(userss.getNAME());
        System.out.println(userss.getPASSWORD());*/


        return new SimpleAuthenticationInfo(name,rpassword,getName());





    }
}
