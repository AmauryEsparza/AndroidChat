package com.example.amauryesparza.androidchat.login.ui;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.example.amauryesparza.androidchat.R;
import com.example.amauryesparza.androidchat.contactlist.ContactListActivity;
import com.example.amauryesparza.androidchat.login.LoginPresenter;
import com.example.amauryesparza.androidchat.login.LoginPresenterImpl;

import butterknife.ButterKnife;
import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity implements LoginView {

    @BindView(R.id.editEmail) EditText inputEmail;
    @BindView(R.id.editPassword) EditText inputPassword;
    @BindView(R.id.bttnLogin) Button bttnLogin;
    @BindView(R.id.bttnSignup) Button bttnSignup;
    @BindView(R.id.progressBar) ProgressBar progressBar;
    @BindView(R.id.layoutMainContainer) RelativeLayout container;

    private LoginPresenter loginPresenter;
    private final static String LOG_TAG = LoginActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        loginPresenter = new LoginPresenterImpl(this);
        loginPresenter.onCreate();
        loginPresenter.checkForAuthenticatedUser();
    }

    @Override
    protected void onDestroy() {
        loginPresenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void enableInputs() {
        enableInputs(true);
    }

    @Override
    public void disableInputs() {
        enableInputs(false);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @OnClick(R.id.bttnLogin)
    @Override
    public void handleLogin() {
        loginPresenter.validateLogin(inputEmail.getText().toString(), inputPassword.getText().toString());
    }

    @OnClick(R.id.bttnSignup)
    @Override
    public void handleSignUp() {
        loginPresenter.registerNewUser(inputEmail.getText().toString(), inputPassword.getText().toString());
    }

    @Override
    public void navigateToMainScreen() {
        startActivity(new Intent(this, ContactListActivity.class));
    }

    @Override
    public void loginError(String error) {
        Log.e(LOG_TAG, "Error trying to login ".concat(error));
        inputPassword.setText("");
        String msgError = String.format(getString(R.string.login_error_message_login), error);
        inputPassword.setError(msgError);
    }

    @Override
    public void signUpSuccess() {
        Snackbar.make(container, getString(R.string.login_notice_message_login), Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void signUpError(String error) {
        Log.e(LOG_TAG, "Error registering the user ".concat(error));
        inputPassword.setText("");
        String msgError = String.format(getString(R.string.login_error_message_signup), error);
        inputPassword.setError(msgError);
    }

    private void enableInputs(boolean enabled){
        inputEmail.setEnabled(enabled);
        inputPassword.setEnabled(enabled);
        bttnLogin.setEnabled(enabled);
        bttnSignup.setEnabled(enabled);
    }
}
