package com.example.amauryesparza.androidchat.login;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.amauryesparza.androidchat.domain.FirebaseHelper;
import com.example.amauryesparza.androidchat.entities.User;
import com.example.amauryesparza.androidchat.lib.EventBus;
import com.example.amauryesparza.androidchat.lib.GreenRobotEventBus;
import com.example.amauryesparza.androidchat.login.events.LoginEvent;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by AmauryEsparza on 9/2/16.
 */

public class LoginRepositoryImpl implements LoginRepository {

    private FirebaseHelper helper;
    private FirebaseAuth authData;
    private DatabaseReference myUserReference;

    private static final String LOG_TAG = LoginRepositoryImpl.class.getSimpleName();

    public LoginRepositoryImpl() {
        helper = FirebaseHelper.getInstance();
        authData = FirebaseAuth.getInstance();
        myUserReference = helper.getMyUserReference();
    }

    @Override
    public void signUp(final String email, final String password) {
        Log.e(LOG_TAG, "singUp");
        authData.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    postEvent(LoginEvent.onSignUpSucces);
                    signIn(email, password);
                }else{
                    postEvent(LoginEvent.onSignUpError, "Sign Up failed");
                }
            }
        });
    }

    @Override
    public void signIn(String email, String password) {
        Log.e(LOG_TAG, "signIn");
        authData.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    initSignIn();
                } else {
                    postEvent(LoginEvent.onSignInError, "Sign In Error");
                }
            }
        });
    }

    @Override
    public void checkSession() {
        Log.e(LOG_TAG, "check Session");
        if(authData.getCurrentUser() != null){
            initSignIn();
        }else{
            postEvent(LoginEvent.onFailedToRecoverSession);
        }
    }

    private void initSignIn(){
        myUserReference = helper.getMyUserReference();
        myUserReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User currentUser = dataSnapshot.getValue(User.class);
                if(currentUser == null){
                    registerNewUser();
                }
                helper.changeUserConnectionStatus(User.ONLINE);
                postEvent(LoginEvent.onSignInSuccess);

            }
            @Override
            public void onCancelled(DatabaseError firebaseError) {

            }
        });
    }

    private void registerNewUser(){
        String email = helper.getAuthUserEmail();
        if(email != null){
            User currentUser = new User();
            currentUser.setEmail(email);
            myUserReference.setValue(currentUser);
        }
    }

    private void postEvent(int eventType, String errorMessage){
        LoginEvent loginEvent = new LoginEvent();
        loginEvent.setEventType(eventType);
        if(errorMessage != null){
            loginEvent.setErrorMessage(errorMessage);
        }

        EventBus eventBus = GreenRobotEventBus.getInstance();
        eventBus.post(loginEvent);
    }

    private void postEvent(int type){
        postEvent(type, null);
    }
}
