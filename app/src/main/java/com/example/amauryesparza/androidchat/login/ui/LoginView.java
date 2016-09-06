package com.example.amauryesparza.androidchat.login.ui;

/**
 * Created by AmauryEsparza on 8/29/16.
 */

public interface LoginView {
    void enableInputs();
    void disableInputs();
    void showProgress();
    void hideProgress();

    void handleLogin();
    void handleSignUp();

    void navigateToMainScreen();
    void loginError(String error);

    void signUpSuccess();
    void signUpError(String error);
}
