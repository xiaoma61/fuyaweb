package com.fuya.shiro;


import com.fuya.Redis.Util.RedisUtil;
import com.fuya.fuyadao.entity.PERMISSION;
import com.fuya.fuyaservice.PERMISSIONService;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Configuration

public class ShiroConfiguration {

    @Autowired
    private PERMISSIONService permissionService;
  /*  @Autowired
    private USERSSolrservice usersSolrservice;*/
    @Autowired
    private RedisUtil redisUtil;

    //将自己的验证方式加入容器
    @Bean
    public MyShiroRealm myShiroRealm() {
        MyShiroRealm myShiroRealm = new MyShiroRealm();
      //  myShiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return myShiroRealm;
    }

    //权限管理，配置主要是Realm的管理认证
    @Bean
    public DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(myShiroRealm());
        return securityManager;
    }

    //Filter工厂，设置对应的过滤条件和跳转条件
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager securityManager) {
        //关联数据库权限

        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        Map<String,String> map = new HashMap<String, String>();
        //登出

        map.put("/index", "anon");
        map.put("/recruit/**", "anon");
        map.put("/static/js/**", "anon");
        map.put("/static/css/**", "anon");
        map.put("/static/fonts/**", "anon");
        map.put("/login/**", "anon");
        map.put("/knowledge/**", "anon");
        map.put("/CheckLogin", "anon");
        map.put("/logout","logout");
        map.put("/fuyayuesaoindex/**", "anon");

        //对所有用户认证authc ,不必认证anon
        map.put("/Register/**","anon");
        map.put("/login","anon");
        //需要拦截的资源
        map.put("/**",  "authc");//其他资源全部拦截
        //取得权限
        List<PERMISSION> permissionList=new ArrayList<>();

//        for (int i=1;i<5;i++){
//            if (!(redisUtil.scard(String.valueOf(i))>0)){
//                //没有添加
//                List<PERMISSION>permissionLists=permissionService.findall();
//                for (PERMISSION permission:permissionLists){
//                    JSONObject object=JSONObject.fromObject(permission);
//                    redisUtil.zSet(permission.getTYPE(), object.toString());
//                }
//            }else {
//            Set<String> smembers=redisUtil.smembers(String.valueOf(i));
//            for (String permisson:smembers){
//
//                JSONObject jsonObject= JSONObject.fromObject(permisson);
//                PERMISSION permissionString= (PERMISSION) JSONObject.toBean(jsonObject,PERMISSION.class);
//                System.out.println(permissionString.getURL()+"   0000000");
//                map.put(permissionString.getURL(),"perms["+permissionString.getNAME()+"]");
//
//            }
//            }
//
//        }
        map.put("/users/**","roles[users]");
        map.put("/fuyayuesaos/**","roles[yuesaos]");
        map.put("/companys/**","roles[companys]");
        map.put("/admin/**","roles[admin]");

        //登录
        shiroFilterFactoryBean.setLoginUrl("/Login");
        //首页
        shiroFilterFactoryBean.setSuccessUrl("/index");
        //错误页面，认证不通过跳转

        shiroFilterFactoryBean.setUnauthorizedUrl("/403");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        return shiroFilterFactoryBean;
    }

    //加入注解的使用，不加入这个注解不生效
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }
//    @Bean(value="ehCacheManager")
//    public EhCacheManager ehCacheManager(@Qualifier("ehCacheManagerFactoryBean") EhCacheManagerFactoryBean bean) {
////        log.info("ehCacheManager()");
//        EhCacheManager cacheManager = new EhCacheManager();
//        cacheManager.setCacheManagerConfigFile("classpath:ehcache-shiro.xml");
//        return cacheManager;
//    }
//    @Bean
//    public HashedCredentialsMatcher hashedCredentialsMatcher(){
//        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
//        hashedCredentialsMatcher.setHashAlgorithmName("md5");//散列算法:这里使用md5算法;
//        hashedCredentialsMatcher.setHashIterations(2);//散列的次数，比如散列两次，相当于 md5( md5(""));
//        return hashedCredentialsMatcher;
//    }

}
