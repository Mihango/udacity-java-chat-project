package edu.udacity.java.nano.chat;

/**
 * WebSocket message model
 */
public class Message {
    // TODO: add message model.
    String name;

    public Message(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
