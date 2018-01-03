package com.slife.config.session;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * Created by chen on 2018/1/3.
 * <p>
 * Email 122741482@qq.com
 * <p>
 * Describe:
 */
@Configuration
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 86400*30) //maxInactiveIntervalInSeconds: 设置Session失效时间，使用Redis Session之后，原Boot的server.session.timeout属性不再生效
public class SessionConfig {
}
