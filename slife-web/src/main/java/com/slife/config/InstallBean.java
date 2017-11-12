package com.slife.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Configuration;

/**
 * Created by chenjianan on 2016/12/16-11:25.
 * <p>
 * Describe:
 * 如果我们需要在Spring容器完成Bean的实例化，配置和其他的初始化后添加一些自己的逻辑处理，
 * 我们就可以定义一个或者多个BeanPostProcessor接口的实现。
 */
@Configuration
public class InstallBean implements BeanPostProcessor {
    private Logger logger = LoggerFactory.getLogger(getClass());
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        logger.info("对象" + beanName + "开始实例化");
        return bean;
    }

    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        logger.info("对象" + beanName + "实例化完成");
        return bean;
    }
}
