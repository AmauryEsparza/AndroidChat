package com.example.amauryesparza.androidchat.contactlist;

import com.example.amauryesparza.androidchat.contactlist.events.ContactListEvent;

/**
 * Created by AmauryEsparza on 9/11/16.
 */

public interface ContactListPresenter {
    void onPause();
    void onResume();
    void onCreate();
    void onDestroy();

    void signOff();
    String getCurrentUserEmail();
    void removeContact(String email);
    void onEventMainThread(ContactListEvent event);
}
