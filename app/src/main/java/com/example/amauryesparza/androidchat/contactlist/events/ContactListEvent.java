package com.example.amauryesparza.androidchat.contactlist.events;

import com.example.amauryesparza.androidchat.entities.User;

/**
 * Created by AmauryEsparza on 9/11/16.
 */
public class ContactListEvent {

    public static final int onContactAdded = 0;
    public static final int onContactChanged = 1;
    public static final int onContactRemoved= 2;

    private User user;
    private int eventType;

    public int getEventType() {
        return eventType;
    }

    public void setEventType(int eventType) {
        this.eventType = eventType;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
