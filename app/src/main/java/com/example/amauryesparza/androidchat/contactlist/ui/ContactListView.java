package com.example.amauryesparza.androidchat.contactlist.ui;

import com.example.amauryesparza.androidchat.entities.User;

/**
 * Created by AmauryEsparza on 9/11/16.
 */

public interface ContactListView {

    void onContactAdded(User user);
    void onContactChanged(User user);
    void onContactRemoved(User user);
}
