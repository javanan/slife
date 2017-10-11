package com.slife.config.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by chen on 2017/5/22.
 * <p>
 * Email 122741482@qq.com
 * <p>
 * Describe:
 */
public class DataSourceContextHolder {
    private static final ThreadLocal<String> local = new ThreadLocal<String>();

    private static Logger logger = LoggerFactory.getLogger(DataSourceContextHolder.class);

    public static ThreadLocal<String> getLocal() {
        return local;
    }

    /**
     * 读可能是多个库
     */
    public static void read() {

        local.set(DataSourceType.read.getType());
    }

    /**
     * 写只有一个库
     */
    public static void write() {
        logger.debug("writewritewrite");
        local.set(DataSourceType.write.getType());
    }

    public static String getJdbcType() {
        return local.get();
    }
}
