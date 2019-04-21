package com.fuya.shiro;


import com.fuya.Redis.Util.RedisUtil;
import com.fuya.fuyaservice.PERMISSIONService;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.LinkedHashMap;
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
        LinkedHashMap<String,String> map = new LinkedHashMap<String, String>();
        //登出

        map.put("/index/**", "anon");
        map.put("/Image/**", "anon");
        map.put("/recruit/**", "anon");
        map.put("/js/**", "anon");
        map.put("/img/**", "anon");
        map.put("/css/**", "anon");
        map.put("/fonts/**", "anon");
        map.put("/html/**", "anon");
        map.put("/login/**", "anon");
        map.put("/knowledge/**", "anon");
        map.put("/CheckLogin", "anon");
        map.put("/company/**", "anon");
        map.put("/fileUpload", "anon");
        map.put("/fyys.html", "anon");
        map.put("/all_organization.html", "anon");
        map.put("/join.html", "anon");
        map.put("/all_recruit.html", "anon");
        map.put("/all_knowlage.html", "anon");
        map.put("/order/synCallBack","anon");
        map.put("/login.html", "anon");
        map.put("/logout","logout");


        map.put("/fuyayuesaoindex/**", "anon");

        //对所有用户认证authc ,不必认证anon
        map.put("/Register/**","anon");
        map.put("/login","anon");
        //需要拦截的资源
        map.put("/**",  "authc");//其他资源全部拦截
        //取得权限

        map.put("/html/u_personal/**","authc");
        map.put("/html/u_personal.html","authc");
        map.put("/html/u_personal/**","roles[users]");
        map.put("/html/u_personal.html","roles[users]");
        map.put("/users/**","roles[users]");
        map.put("/fuyayuesaos/**","roles[yuesaos]");
        map.put("/html/n_personal.html","roles[yuesaos]");
        map.put("/html/n_personal/**","roles[yuesaos]");
        map.put("/html/u_personal/**","authc");
        map.put("/html/u_personal.html","authc");

        map.put("/companys/**","roles[companys]");

        map.put("/html/c_personal.html","roles[companys]");
        map.put("/html/c_personal/**","roles[companys]");
        map.put("/html/c_personal/**","authc");
        map.put("/html/c_personal.html","authc");

        map.put("/admin/**","roles[admin]");
        map.put("/html/backstage/**","authc");
        map.put("/html/backstag.html","authc");

        map.put("/html/backstage/**","roles[admin]");
        map.put("/html/backstag.html","roles[admin]");
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
