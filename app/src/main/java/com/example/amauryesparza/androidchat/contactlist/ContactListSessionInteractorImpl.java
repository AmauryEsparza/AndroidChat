package com.example.amauryesparza.androidchat.contactlist;

/**
 * Created by AmauryEsparza on 9/14/16.
 */
public class ContactListSessionInteractorImpl implements ContactListSessionInteractor {
    ContactListRepository repository;

    public ContactListSessionInteractorImpl() {
        repository = new ContactListRepositoryImpl(); //Inject here
    }

    @Override
    public void signOff() {
        repository.signoff();
    }

    @Override
    public String getCurrentUserEmail() {
        return repository.getCurrentUserEmail();
    }

    @Override
    public void changeConnectionStatus(boolean isOnline) {
        repository.changeConnectionStatus(isOnline);
    }
}
