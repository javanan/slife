package com.slife.config.propertie;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 只提供了常用的属性，如果有需要，自己添加
 *
 */
@Component
@ConfigurationProperties(prefix = "writedatasource")
public class WriteProperties extends DataProperties{

}