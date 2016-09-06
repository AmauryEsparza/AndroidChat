package com.example.amauryesparza.androidchat.login;

/**
 * Created by AmauryEsparza on 8/29/16.
 */

public interface LoginInteractor {
    void checkSession();
    void doSignIn(String email, String password);
    void doSingUp(String email, String password);
}
