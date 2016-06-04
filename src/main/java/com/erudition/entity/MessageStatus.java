package com.erudition.entity;

/**
 * Created by sl on 16-6-4.
 */
public class MessageStatus {

    public MessageStatus(String message , int status){
        this.message = message;
        this.status = status;
    }

    private String message;
    private int status;


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
