package com.example.amauryesparza.androidchat.addcontact;

import com.example.amauryesparza.androidchat.addcontact.events.AddContactEvent;

/**
 * Created by AmauryEsparza on 9/26/16.
 */

public interface AddContactPresenter {

    void onShow();
    void onDestroy();

    void addContact(String email);
    void onEventMainThread(AddContactEvent event);
}
