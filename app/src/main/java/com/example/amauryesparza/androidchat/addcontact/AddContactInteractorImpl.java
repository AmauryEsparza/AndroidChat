package com.example.amauryesparza.androidchat.addcontact;

/**
 * Created by AmauryEsparza on 9/26/16.
 */

public class AddContactInteractorImpl implements AddContactInteractor{

    //Repository
    private AddContactRepository repository;

    public AddContactInteractorImpl(){
        repository = new AddContactRepositoryImpl();
    }

    @Override
    public void execute(String email) {
        repository.addContact(email);
    }
}
