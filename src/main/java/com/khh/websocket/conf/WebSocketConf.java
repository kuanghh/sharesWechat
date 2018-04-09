package com.khh.websocket.conf;

import com.khh.websocket.handler.StompMessageHandshakeHandler;
import com.khh.websocket.interceptor.WebSocketHandshakeInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

/**
 * Created by 951087952@qq.com on 2018/4/9.
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConf extends AbstractWebSocketMessageBrokerConfigurer{

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        //为"/stomp"路径启用SockJS功能
        registry.addEndpoint("/stomp")
                .setHandshakeHandler(new StompMessageHandshakeHandler())
                .addInterceptors(new WebSocketHandshakeInterceptor())
                .withSockJS();
    }


    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        //消息代理将会处理前缀为"/queue"和"/topic"的消息，除此之外，发往应用程序的消息将会带有"/app"前缀

        //理解为：在客户端上主题和个人的消息订阅,在topic和queue这两个域上可以向客户端发消息；
        registry.enableSimpleBroker("/queue","/topic");
        //理解为：客户端向系统发送消息
        registry.setApplicationDestinationPrefixes("/app");
        //这句表示给指定用户发送（一对一）的主题前缀是“/user”;
        registry.setUserDestinationPrefix("/user");
    }
}
