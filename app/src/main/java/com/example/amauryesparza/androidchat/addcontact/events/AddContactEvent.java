package com.example.amauryesparza.androidchat.addcontact.events;

/**
 * Created by AmauryEsparza on 9/26/16.
 */
public class AddContactEvent {

    private int eventType;
    private String errorMessage;

    public static final int onContactAdded = 0;
    public static final int onContactFailed = 1;

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public int getEventType() {
        return eventType;
    }

    public void setEventType(int eventType) {
        this.eventType = eventType;
    }
}
