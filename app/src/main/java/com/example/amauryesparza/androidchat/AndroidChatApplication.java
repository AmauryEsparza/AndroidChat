package com.example.amauryesparza.androidchat;

import android.app.Application;

/**
 * Created by AmauryEsparza on 7/12/16.
 */

public class AndroidChatApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        setupFirebase();
    }

    public void setupFirebase(){
//        FirebaseApp.getInstance().getOptions().
//        Firebase.getDefaultConfig().setPersistenceEnabled(true);
    }
}
