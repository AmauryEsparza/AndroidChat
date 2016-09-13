package com.example.amauryesparza.androidchat.contactlist;

/**
 * Created by AmauryEsparza on 9/11/16.
 */

public interface ContactListRepository {

    void signoff();
    String getCurrentUserEmail();

    void subscribeToContactListEvents();
    void unsubscribeToContactListEvents();
    void destroyListener();
    void removeContact(String email);
    void changeConnectionStatus(boolean isOnline);
}
