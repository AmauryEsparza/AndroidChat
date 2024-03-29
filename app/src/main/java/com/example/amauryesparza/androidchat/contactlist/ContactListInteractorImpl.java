package com.example.amauryesparza.androidchat.contactlist;

/**
 * Created by AmauryEsparza on 9/14/16.
 */

public class ContactListInteractorImpl implements ContactListInteractor {

    private ContactListRepository repository;

    public ContactListInteractorImpl() {
        repository = new ContactListRepositoryImpl();
    }

    @Override
    public void subscribe() {
        repository.subscribeToContactListEvents();
    }

    @Override
    public void unsubscribe() {
        repository.unsubscribeToContactListEvents();
    }

    @Override
    public void destroyListener() {
        repository.destroyListener();
    }

    @Override
    public void removeContact(String email) {
        repository.removeContact(email);
    }
}
