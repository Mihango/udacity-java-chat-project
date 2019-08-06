package edu.udacity.java.nano.chat;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * WebSocket message model
 */
public class Message {
    // TODO: add message model.
    @JSONField(name = "username")
    private String username;

    @JSONField(name = "msg")
    private String msg;

    @JSONField(name = "type")
    private String type;

    public Message() {
    }

    public Message(String msg) {
        this.msg = msg;
    }

    public Message(String username, String msg, String type) {
        this.username = username;
        this.msg = msg;
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
