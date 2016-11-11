package edu.bklawsonbsu.huh;

public class Message {
    private String username;
    private String text;
    private String time;

    public Message() {

    }

    public Message(String username, String text, String time) {
        this.username = username;
        this.text = text;
        this.time = time;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}