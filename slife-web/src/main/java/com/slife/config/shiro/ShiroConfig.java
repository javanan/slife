package com.slife.config.shiro;

/**
 * Created by chen on 2017/7/14.
 * <p>
 * Email 122741482@qq.com
 * <p>
 * Describe:
 */

import com.slife.shiro.AuthRealm;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.slf4j.Logger;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;

/**
 * shiro的配置类
 * @author Administrator
 *
 */

@Configuration
public class ShiroConfig {


   private Logger logger = org.slf4j.LoggerFactory.getLogger(getClass());

    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        //SecurityManager manager= securityManager(authRealm);
        ShiroFilterFactoryBean bean=new ShiroFilterFactoryBean();
        bean.setSecurityManager(securityManager);
        //配置登录的url和登录成功的url
        bean.setLoginUrl("/login");
        bean.setSuccessUrl("/index");
        //配置访问权限
        LinkedHashMap<String, String> filterChainDefinitionMap=new LinkedHashMap<>();

        filterChainDefinitionMap.put("/logout","logout");


        //开放的静态资源
        filterChainDefinitionMap.put("/favicon.ico", "anon");//网站图标
        filterChainDefinitionMap.put("/css/**","anon");
        filterChainDefinitionMap.put("/js/**","anon");
        filterChainDefinitionMap.put("/img/**","anon");
        filterChainDefinitionMap.put("/fonts/**","anon");

        filterChainDefinitionMap.put("/health/**","anon");
        filterChainDefinitionMap.put("/env/**","anon");
        filterChainDefinitionMap.put("/metrics/**","anon");
        filterChainDefinitionMap.put("/trace/**","anon");
        filterChainDefinitionMap.put("/dump/**","anon");
        filterChainDefinitionMap.put("/jolokia/**","anon");
        filterChainDefinitionMap.put("/info/**","anon");
        filterChainDefinitionMap.put("/logfile/**","anon");
        filterChainDefinitionMap.put("/refresh/**","anon");
        filterChainDefinitionMap.put("/flyway/**","anon");
        filterChainDefinitionMap.put("/liquibase/**","anon");
        filterChainDefinitionMap.put("/heapdump/**","anon");
        filterChainDefinitionMap.put("/loggers/**","anon");
        filterChainDefinitionMap.put("/auditevents/**","anon");


        filterChainDefinitionMap.put("/layouts/**","anon");
        filterChainDefinitionMap.put("/attach/**","anon");


        filterChainDefinitionMap.put("/*", "authc");//表示需要认证才可以访问
        filterChainDefinitionMap.put("/**", "authc");//表示需要认证才可以访问
        filterChainDefinitionMap.put("/*.*", "authc");
        bean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return bean;
    }


    //配置核心安全事务管理器
    @Bean
    public SecurityManager securityManager(@Qualifier("authRealm")AuthRealm authRealm,@Qualifier("redisCacheManager")CacheManager
            cacheManager) {

       logger.info("--------------shiro已经加载----------------");
        DefaultWebSecurityManager manager=new DefaultWebSecurityManager();
        // 设置realm.
        manager.setRealm(authRealm);

        //注入缓存管理器;
        //注意:开发时请先关闭，如不关闭热启动会报错
        manager.setCacheManager(cacheManager);//这个如果执行多次，也是同样的一个对象;
        //注入记住我管理器;
        manager.setRememberMeManager(rememberMeManager());


        return manager;
    }

    /**
     * shiro缓存管理器;
     * 需要注入对应的其它的实体类中：
     * 1、安全管理器：securityManager
     * 可见securityManager是整个shiro的核心；
     *
     * @return
     */





    /**
     * cookie对象;
     * @return
     * */
    @Bean
    public SimpleCookie rememberMeCookie(){
        //这个参数是cookie的名称，对应前端的checkbox的name = rememberMe
        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
        //<!-- 记住我cookie生效时间30天 ,单位秒;-->
       /* simpleCookie.setMaxAge(259200);*/
        simpleCookie.setMaxAge(20);
        logger.info("--------------rememberMeCookie init---------------"+simpleCookie);
        return simpleCookie;
    }
    /**
     * cookie管理对象;
     * @return
     */
    @Bean
    public CookieRememberMeManager rememberMeManager(){
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookie());

       logger.info("--------------rememberMeManager init---------------"+cookieRememberMeManager);
        return cookieRememberMeManager;
    }


    /**
     * 保证实现了Shiro内部lifecycle函数的bean执行
     * @return
     */
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor(){
        return new LifecycleBeanPostProcessor();
    }

    /**
     * AOP式方法级权限检查
     * @return
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator creator=new DefaultAdvisorAutoProxyCreator();
        creator.setProxyTargetClass(true);
        return creator;
    }
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
      //  SecurityManager manager= securityManager(authRealm);
        AuthorizationAttributeSourceAdvisor advisor=new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }
}
