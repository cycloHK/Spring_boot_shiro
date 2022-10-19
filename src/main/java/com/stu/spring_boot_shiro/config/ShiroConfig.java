package com.stu.spring_boot_shiro.config;

import com.stu.spring_boot_shiro.cache.RedisCacheManager;
import com.stu.spring_boot_shiro.shiro.realms.CustomerRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    //1.创建shiroFilter 负责拦截所有请求的
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();

        //给filter设置安全管理器
        bean.setSecurityManager(defaultWebSecurityManager);

        //4.配置系统的受限资源
        Map<String, String> map = new HashMap<String,String>();
        map.put("/user/login","anon"); //anoc 设置为公共资源 路径写全
        map.put("/register.jsp","anon"); //anoc 设置为公共资源 路径写全
        map.put("/user/register","anon"); //anoc 设置为公共资源 路径写全
        map.put("/**","authc"); //authc 代表请求这资源需要请求和认证

        //默认的认证路径 就是login.jsp 此项可以自定义认证路径
        bean.setLoginUrl("/login.jsp");

        bean.setFilterChainDefinitionMap(map);


        return bean;
    }

    //2.创建安全管理器 web
    @Bean
    public DefaultWebSecurityManager getDefaultWebSecurityManager(Realm realm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //安全管理器 设置realm
        securityManager.setRealm(realm);
        return securityManager;
    }

    //3.创建自定义realm
    @Bean
    public Realm getRealm(){
        CustomerRealm customerRealm = new CustomerRealm();
        //修改凭证校验匹配器
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        //MD5 加密
        credentialsMatcher.setHashAlgorithmName("MD5");
        //散列次数
        credentialsMatcher.setHashIterations(1024);
        customerRealm.setCredentialsMatcher(credentialsMatcher);

        //开启缓存管理
        customerRealm.setCacheManager(new RedisCacheManager());
        customerRealm.setCachingEnabled(true);              //全局缓存
        customerRealm.setAuthenticationCachingEnabled(true);  //认证缓存
        customerRealm.setAuthenticationCacheName("authenticationCache");
        customerRealm.setAuthorizationCachingEnabled(true);   //授权缓存
        customerRealm.setAuthenticationCacheName("authorizationCache");
        return customerRealm;
    }

}