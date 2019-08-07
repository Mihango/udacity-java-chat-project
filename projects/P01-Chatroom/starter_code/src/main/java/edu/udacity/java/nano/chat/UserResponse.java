package edu.udacity.java.nano.chat;

import com.alibaba.fastjson.annotation.JSONField;

public class UserResponse {

    @JSONField(name = "username")
    private String username;

    @JSONField(name = "msg")
    private String msg;

    @JSONField(name = "type")
    private String type;

    @JSONField(name = "onlineCount")
    private int onlineUsers;

    public UserResponse() {
    }

    public UserResponse(String username, String msg, String type, int onlineUsers) {
        this.username = username;
        this.msg = msg;
        this.type = type;
        this.onlineUsers = onlineUsers;
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

    public int getOnlineUsers() {
        return onlineUsers;
    }

    public void setOnlineUsers(int onlineUsers) {
        this.onlineUsers = onlineUsers;
    }
}
