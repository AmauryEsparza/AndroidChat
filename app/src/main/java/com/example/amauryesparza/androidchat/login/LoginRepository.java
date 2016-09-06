package com.example.amauryesparza.androidchat.login;

/**
 * Created by AmauryEsparza on 8/29/16.
 */

public interface LoginRepository {
    void signUp(String email, String password);
    void signIn(String email, String password);
    void checkSession();
}
