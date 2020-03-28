package com.dotplays.messaggio.model;

public class Chat {
    public String sendBy;
    public String text;
    public long time;

    public Chat() {


    }

    public Chat(String sendBy, String text, long time) {
        this.sendBy = sendBy;
        this.text = text;
        this.time = time;
    }

    public String getSendBy() {
        return sendBy;
    }

    public void setSendBy(String sendBy) {
        this.sendBy = sendBy;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
