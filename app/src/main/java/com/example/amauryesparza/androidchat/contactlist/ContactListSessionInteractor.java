package com.example.amauryesparza.androidchat.contactlist;

/**
 * Created by AmauryEsparza on 9/11/16.
 */

public interface ContactListSessionInteractor {
    void signOff();
    String getCurrentUserEmail();
    void changeConnectionStatus(boolean isOnline);
}
