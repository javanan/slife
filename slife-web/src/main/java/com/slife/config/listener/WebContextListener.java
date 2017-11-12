package com.slife.config.listener;

import org.slf4j.Logger;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;

/**
 * Created by chenjianan on 2016/12/16-10:30.
 * <p>
 * Describe: 作用：在启动Web容器时，自动装配Spring applicationContext.xml的配置信息。
 * DispatcherServlet的上下文仅仅是Spring MVC的上下文，而Spring加载的上下文是通过ContextLoaderListener来加载的。
 * 一般spring web项目中同时会使用这两种上下文，前者仅负责MVC相关bean的配置管理（如ViewResolver、Controller、MultipartResolver等），
 * 后者则负责整个spring相关bean的配置管理（如相关Service、DAO等）。
 */
@WebListener
public class WebContextListener extends org.springframework.web.context.ContextLoaderListener {

    private Logger logger = org.slf4j.LoggerFactory.getLogger(getClass());

   // private static CacheManager cacheManager = SpringContextHolder.getBean(CacheManager.class);

    @Override
    public WebApplicationContext initWebApplicationContext(ServletContext servletContext) {
        logger.info("启动Web容器，在启动Web容器时，自动装配Spring applicationContext.xml的配置信息.....");


        WebApplicationContext webApplicationContext = super.initWebApplicationContext(servletContext);

        initSysParam(servletContext);

        return webApplicationContext;
    }

    /**
     * 初始化系统参数
     *
     * @param servletContext
     */
    private boolean initSysParam(ServletContext servletContext) {
        logger.info("在启动Web容器时，初始化系统参数.....");
        //TODO 系统配置信息--从缓存中获取。
        return true;
    }
}