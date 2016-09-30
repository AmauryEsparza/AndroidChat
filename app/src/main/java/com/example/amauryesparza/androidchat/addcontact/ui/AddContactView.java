package com.example.amauryesparza.androidchat.addcontact.ui;

/**
 * Created by AmauryEsparza on 9/26/16.
 */

public interface AddContactView {

    void showInput();
    void hideInput();
    void showProgressBar();
    void hideProgressBar();

    void contactAddedSuccessfuly();
    void contactAddedError();

}
