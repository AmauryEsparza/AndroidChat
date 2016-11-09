package com.example.amauryesparza.androidchat.chatlist.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by AmauryEsparza on 10/13/16.
 */

public class ChatListActivity extends AppCompatActivity implements ChatListView {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void enableInputs() {

    }

    @Override
    public void disableInputs() {

    }

    @Override
    public void showProgressBar() {

    }

    @Override
    public void hideProgressBar() {

    }

    @Override
    public void sendMessage(String message) {

    }

    @Override
    public void cancelMessage(int idMessage) {

    }

    @Override
    public void loadMessages(int i, int j) {

    }

    @Override
    public void loadMessages(int x) {

    }
}
