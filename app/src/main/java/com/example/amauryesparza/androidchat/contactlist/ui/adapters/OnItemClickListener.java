package com.example.amauryesparza.androidchat.contactlist.ui.adapters;

import com.example.amauryesparza.androidchat.entities.User;

/**
 * Created by AmauryEsparza on 9/12/16.
 */

public interface OnItemClickListener {
    void onItemClick(User user);
    void onItemLongClick(User user);
}
