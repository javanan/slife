package com.slife.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.activiti.engine.IdentityService;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.activiti.spring.SpringProcessEngineConfiguration;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * @author : felixu
 * @createTime : 2017/11/23.
 */
@Configuration
public class ActivitiConfig {

    @Autowired
    PlatformTransactionManager transactionManager;

    @Autowired
    DruidDataSource druidDataSource;

    @Bean
    public SpringProcessEngineConfiguration getProcessEngineConfiguration(){
        SpringProcessEngineConfiguration config =
                new SpringProcessEngineConfiguration();
        config.setDataSource(druidDataSource);
        config.setTransactionManager(transactionManager);
        config.setDatabaseType("mysql");
        config.setDatabaseSchemaUpdate("true");
        return config;
    }

    @Primary
    @Bean
    public TaskExecutor primaryTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        return executor;
    }

//    @Bean
//    InitializingBean usersAndGroupsInitializer(final IdentityService identityService) {
//
//        return new InitializingBean() {
//            @Override
//            public void afterPropertiesSet() throws Exception {
//
//                Group group = identityService.newGroup("user");
//                group.setName("users");
//                group.setType("security-role");
//                identityService.saveGroup(group);
//
//                User admin = identityService.newUser("admin");
//                admin.setPassword("admin");
//                identityService.saveUser(admin);
//
//            }
//        };
//    }
}