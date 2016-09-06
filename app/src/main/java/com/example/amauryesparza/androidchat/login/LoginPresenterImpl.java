package com.example.amauryesparza.androidchat.login;

import android.util.Log;

import com.example.amauryesparza.androidchat.lib.EventBus;
import com.example.amauryesparza.androidchat.lib.GreenRobotEventBus;
import com.example.amauryesparza.androidchat.login.events.LoginEvent;
import com.example.amauryesparza.androidchat.login.ui.LoginView;

/**
 * Created by AmauryEsparza on 9/1/16.
 */

public class LoginPresenterImpl implements LoginPresenter {

    private LoginView loginView;
    private LoginInteractor loginInteractor;
    private EventBus eventBus;

    private static final String LOG_TAG = LoginPresenterImpl.class.getSimpleName();

    public LoginPresenterImpl(LoginView loginView){
        this.loginView = loginView;
        loginInteractor = new LoginInteractorImpl();
        this.eventBus = GreenRobotEventBus.getInstance();
    }

    @Override
    public void onCreate() {
        eventBus.register(this);
    }

    @Override
    public void onDestroy() {
        loginView = null;
        eventBus.unregister(this);
    }

    @Override
    public void checkForAuthenticatedUser() {
        if(loginView != null){
            loginView.disableInputs();
            loginView.showProgress();
        }
        loginInteractor.checkSession();
    }

    @Override
    public void validateLogin(String email, String password) {
        if(loginView != null){
            loginView.disableInputs();
            loginView.showProgress();
        }
        loginInteractor.doSignIn(email, password);
    }

    @Override
    public void registerNewUser(String email, String password) {
        if(loginView != null){
            loginView.disableInputs();
            loginView.showProgress();
        }
        loginInteractor.doSingUp(email, password);
    }

    @Override
    public void onEventMainThread(LoginEvent event) {
        switch(event.getEventType()){
            case LoginEvent.onSignInSuccess:
                onSignInSuccess();
                break;
            case LoginEvent.onSignInError:
                onSignInError(event.getErrorMessage());
                break;
            case LoginEvent.onSignUpSucces:
                onSignUpSuccess();
                break;
            case LoginEvent.onSignUpError:
                onSignUpError(event.getErrorMessage());
                break;
            case LoginEvent.onFailedToRecoverSession:
                onFailedToRecoverSession();
                break;
        }

    }

    private void onFailedToRecoverSession() {
        if(loginView != null){
            loginView.enableInputs();
            loginView.hideProgress();
        }
        Log.e(LOG_TAG, "Fail to recover session");

    }

    private void onSignInSuccess(){
        if(loginView != null){
            loginView.navigateToMainScreen();
        }
    }

    private void onSignUpSuccess(){
        if(loginView != null){
            loginView.signUpSuccess();
        }
    }

    private void onSignInError(String error){
        if(loginView != null){
            loginView.enableInputs();
            loginView.hideProgress();
            loginView.loginError(error);
        }
    }

    private void onSignUpError(String error){
        if(loginView != null){
            loginView.enableInputs();
            loginView.hideProgress();
            loginView.signUpError(error);
        }

    }
}
