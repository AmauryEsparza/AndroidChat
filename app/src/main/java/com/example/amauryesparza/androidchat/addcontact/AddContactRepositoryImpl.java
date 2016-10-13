package com.example.amauryesparza.androidchat.addcontact;

import android.util.Log;

import com.example.amauryesparza.androidchat.addcontact.events.AddContactEvent;
import com.example.amauryesparza.androidchat.domain.FirebaseHelper;
import com.example.amauryesparza.androidchat.entities.User;
import com.example.amauryesparza.androidchat.lib.EventBus;
import com.example.amauryesparza.androidchat.lib.GreenRobotEventBus;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by AmauryEsparza on 10/4/16.
 */

public class AddContactRepositoryImpl implements AddContactRepository {

    DatabaseReference mUserReference;
    FirebaseHelper firebase;

    public AddContactRepositoryImpl(){
        firebase = FirebaseHelper.getInstance();
        mUserReference = firebase.getMyUserReference();
        Log.i(AddContactRepositoryImpl.class.getSimpleName(), "mUserReference " + mUserReference);
    }

    @Override
    public void addContact(String email) {
        final String contactEmail = email.replace(".","_");

        mUserReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                if(user != null) {
                    saveContact(user, contactEmail);
                    postEvent(AddContactEvent.onContactAdded, null);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                postEvent(AddContactEvent.onContactFailed, "Could not add the contact");
            }
        });
    }

    private void saveContact(User user, String email){

        //TODO: Verify the @email exist in the registered users, if not do not add it.

        //First add the contact to my current auth user
        DatabaseReference myContactReference = firebase.getMyContactsReference(mUserReference.getKey());
        myContactReference.child(email).setValue(user.isOnline());

        //Then do the same but for my contact
        DatabaseReference reverseContactReference = firebase.getContactsReference(email);
        reverseContactReference.child(mUserReference.getKey()).setValue(User.ONLINE);
    }

    private void postEvent(int eventType, String errorMessage){
        AddContactEvent event = new AddContactEvent();
        event.setEventType(eventType);
        if(errorMessage != null){
            event.setErrorMessage(errorMessage);
        }

        EventBus eventBus = GreenRobotEventBus.getInstance();
        eventBus.post(event);
    }
}
