package com.slife.controller;

import com.slife.entity.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

/**
 * Created by chen on 2017/10/19.
 * <p>
 * Email 122741482@qq.com
 * <p>
 * Describe: web socket 消息推送
 */
@Controller
public class MessageController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    //接消息的地址
    @MessageMapping("/hello")
    //订阅的地址。广播的方式
    @SendTo("/topic/greetings")
    public Message greeting(Message message) throws Exception {
       // Thread.sleep(1000); // simulated delay
        return new Message("Hello, " + message.getContent() + "!");
    }

    @GetMapping("/chat")
    @ResponseBody
    public void handlerChat(String msg,Principal principal) {
        System.out.println(principal.getName());
        //点对点发
        messagingTemplate.convertAndSendToUser("001", "/topic/chat" , msg);
    }
}
