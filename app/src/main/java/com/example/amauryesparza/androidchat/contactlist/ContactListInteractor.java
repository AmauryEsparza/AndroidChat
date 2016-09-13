package com.example.amauryesparza.androidchat.contactlist;

/**
 * Created by AmauryEsparza on 9/11/16.
 */

public interface ContactListInteractor {
    void subscribe();
    void unsubscribe();
    void destroyListener();
    void removeContact(String email);


}
