package com.example.amauryesparza.androidchat.login;

/**
 * Created by AmauryEsparza on 9/2/16.
 */

public class LoginInteractorImpl implements LoginInteractor {

    private LoginRepository loginRepository;

    public LoginInteractorImpl() {
        loginRepository = new LoginRepositoryImpl();
    }

    @Override
    public void checkSession() {
        loginRepository.checkSession();
    }

    @Override
    public void doSignIn(String email, String password) {
        loginRepository.signIn(email, password);
    }

    @Override
    public void doSingUp(String email, String password) {
        loginRepository.signUp(email, password);
    }
}
