package com.slife.config.db;

import com.alibaba.druid.pool.DruidDataSource;
import com.slife.config.propertie.DataProperties;
import com.slife.config.propertie.ReadProperties1;
import com.slife.config.propertie.ReadProperties2;
import com.slife.config.propertie.WriteProperties;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@Configuration

/*
@ConditionalOnClass(DruidDataSource.class)
@EnableConfigurationProperties(DruidProperties.class)
@ConditionalOnProperty(prefix = "druid", name = "url")
@AutoConfigureBefore(DataSourceAutoConfiguration.class)*/


public class DruidAutoConfiguration {

    private Logger logger = LoggerFactory.getLogger(getClass());


    @Autowired
    private WriteProperties writeProperties;
    @Autowired
    private ReadProperties1 readProperties1;
    @Autowired
    private ReadProperties2 readProperties2;


    private DataSource dataSource(DataProperties properties) {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(properties.getUrl());
        dataSource.setUsername(properties.getUsername());
        dataSource.setPassword(properties.getPassword());
        if (properties.getInitialSize() > 0) {
            dataSource.setInitialSize(properties.getInitialSize());
        }
        if (properties.getMinIdle() > 0) {
            dataSource.setMinIdle(properties.getMinIdle());
        }
        if (properties.getMaxActive() > 0) {
            dataSource.setMaxActive(properties.getMaxActive());
        }
        dataSource.setTestOnBorrow(properties.isTestOnBorrow());
        dataSource.setTestOnReturn(properties.isTestOnReturn());
        dataSource.setTimeBetweenEvictionRunsMillis(properties.getTimeBetweenEvictionRunsMillis());
        dataSource.setValidationQuery(properties.getValidationQuery());
        dataSource.setTestWhileIdle(properties.isTestWhileIdle());
        dataSource.setMinEvictableIdleTimeMillis(properties.getMinEvictableIdleTimeMillis());
        dataSource.setPoolPreparedStatements(properties.isPoolPreparedStatements());
        dataSource.setMaxPoolPreparedStatementPerConnectionSize(properties.getMaxPoolPreparedStatementPerConnectionSize());

        try {
            dataSource.setFilters(properties.getFilters());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {

            dataSource.init();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return dataSource;
    }




    /**
     * 有多少个从库就要配置多少个
     *
     * @return
     */
    @Bean(name = "readDataSource1")
    // @ConfigurationProperties(prefix = "readdatasource01")
    public DataSource readDataSourceOne() {
        logger.info("-------------------- readDataSourceOne init ---------------------");
        return dataSource(readProperties1);
    }

    @Bean(name = "readDataSource2")
    // @ConfigurationProperties(prefix = "readdatasource02")
    public DataSource readDataSourceTwo() {
        logger.info("-------------------- readDataSourceTwo init ---------------------");
        return dataSource(readProperties2);
    }

    @Bean("readDataSources")
    public List<DataSource> readDataSources() {
        List<DataSource> dataSources = new ArrayList<>();
        dataSources.add(readDataSourceOne());
        dataSources.add(readDataSourceTwo());
        return dataSources;
    }

    @Bean(name = "writeDataSource1")
    @Primary
    public DataSource writeDataSource() {
        logger.info("-------------------- writeDataSource init ---------------------");
        return dataSource(writeProperties);
    }
}
