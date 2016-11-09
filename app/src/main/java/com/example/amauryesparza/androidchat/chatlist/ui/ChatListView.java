package com.example.amauryesparza.androidchat.chatlist.ui;

/**
 * Created by AmauryEsparza on 10/13/16.
 */

public interface ChatListView {
    void enableInputs();
    void disableInputs();
    void showProgressBar();
    void hideProgressBar();

    void sendMessage(String message);
    void cancelMessage(int idMessage);

    void loadMessages(int i, int j);
    void loadMessages(int x);
}
