package com.example.emsd_app;

public class Message {
    private String dateStamp;
    private String timeStamp;
    private String messageText;
    private String classType;
    private String level;
    public Message(){

    }

    public Message(String dateStamp, String timeStamp, String messageText){
        this.dateStamp = dateStamp;
        this.timeStamp = timeStamp;
        this.messageText = messageText;
    }

    public Message(String dateStamp, String timeStamp, String messageText, String classType, String level) {
        this.dateStamp = dateStamp;
        this.timeStamp = timeStamp;
        this.messageText = messageText;
        this.classType = classType;
        this.level = level;
    }

    public String getDateStamp() {
        return dateStamp;
    }

    public void setDateStamp(String dateStamp) {
        this.dateStamp = dateStamp;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public String getClassType() {
        return classType;
    }

    public void setClassType(String classType) {
        this.classType = classType;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
