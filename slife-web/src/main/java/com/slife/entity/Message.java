package com.slife.entity;

/**
 * Created by chen on 2017/10/19.
 * <p>
 * Email 122741482@qq.com
 * <p>
 * Describe: web socket 返回消息内容
 */
public class Message {
    private String content;

    public Message() {
    }

    public Message(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
