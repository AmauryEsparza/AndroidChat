package com.example.amauryesparza.androidchat.login;

import com.example.amauryesparza.androidchat.login.events.LoginEvent;

/**
 * Created by AmauryEsparza on 8/29/16.
 */

public interface LoginPresenter {
    void onCreate();
    void onDestroy();
    void checkForAuthenticatedUser();
    void validateLogin(String email, String password);
    void registerNewUser(String email, String password);
    void onEventMainThread(LoginEvent event);
}
