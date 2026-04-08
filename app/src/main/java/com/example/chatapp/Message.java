package com.example.chatapp;

public class Message {
    String text;
    String time;
    boolean isSent;

    public Message(String text, String time, boolean isSent) {
        this.text = text;
        this.time = time;
        this.isSent = isSent;
    }
}