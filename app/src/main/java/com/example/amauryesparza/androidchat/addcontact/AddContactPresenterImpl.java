package com.example.amauryesparza.androidchat.addcontact;

import android.util.Log;
import android.widget.Toast;

import com.example.amauryesparza.androidchat.addcontact.events.AddContactEvent;
import com.example.amauryesparza.androidchat.addcontact.ui.AddContactView;
import com.example.amauryesparza.androidchat.lib.EventBus;
import com.example.amauryesparza.androidchat.lib.GreenRobotEventBus;

/**
 * Created by AmauryEsparza on 9/26/16.
 */

public class AddContactPresenterImpl implements AddContactPresenter {

    private AddContactView view;
    private AddContactInteractor interactor;
    private EventBus eventBus;

    public AddContactPresenterImpl(AddContactView view){
        this.view = view;
        eventBus = GreenRobotEventBus.getInstance();
        interactor = new AddContactInteractorImpl();
    }

    @Override
    public void onShow() {
        eventBus.register(this);
        view.showInput();
    }

    @Override
    public void onDestroy() {
        eventBus.unregister(this);
    }

    @Override
    public void addContact(String email) {
        if(view != null){
            view.hideInput();
            view.showProgressBar();
        }
        interactor.execute(email);
    }

    @Override
    public void onEventMainThread(AddContactEvent event) {
        if(view != null){
            view.showInput();
            view.hideProgressBar();
        }
        switch (event.getEventType()){
            case AddContactEvent.onContactAdded:
                Log.e(this.getClass().getSimpleName(), "Contact added correctly");
                view.contactAddedSuccessfuly();
                //return to the list view contact
                break;
            case AddContactEvent.onContactFailed:
                //print a message contact not added correctly
                Log.e(this.getClass().getSimpleName(), "Could not add contact");
                view.contactAddedError();
                break;
        }
    }
}
