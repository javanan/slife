package com.slife.config.websocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

/**
 * Created by chen on 2017/10/19.
 * <p>
 * Email 122741482@qq.com
 * <p>
 * Describe: websocker stomp 配置
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        //用来配置消息代理，由于我们是实现推送功能，这里的消息代理是/topic
        config.enableSimpleBroker("/topic");
        config.setApplicationDestinationPrefixes("/app"); //发送前缀
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        //用来注册STOMP协议节点，同时指定使用SockJS协议。
        registry.addEndpoint("/gs-guide-websocket").withSockJS();
    }

}
