package com.example.amauryesparza.androidchat.login.events;

/**
 * Created by AmauryEsparza on 9/2/16.
 */

public class LoginEvent {

    private int eventType;
    private String errorMessage;

    public static final int onSignInError = 0;
    public static final int onSignUpError = 1;
    public static final int onSignInSuccess = 2;
    public static final int onSignUpSucces = 3;
    public static final int onFailedToRecoverSession = 4;

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
