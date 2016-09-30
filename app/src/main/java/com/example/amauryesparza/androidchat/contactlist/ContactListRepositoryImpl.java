package com.example.amauryesparza.androidchat.contactlist;

import com.example.amauryesparza.androidchat.contactlist.events.ContactListEvent;
import com.example.amauryesparza.androidchat.domain.FirebaseHelper;
import com.example.amauryesparza.androidchat.entities.User;
import com.example.amauryesparza.androidchat.lib.EventBus;
import com.example.amauryesparza.androidchat.lib.GreenRobotEventBus;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

/**
 * Created by AmauryEsparza on 9/19/16.
 */

public class ContactListRepositoryImpl implements ContactListRepository {

    private FirebaseHelper helper;
    private ChildEventListener contactEventListener;
    private EventBus eventBus;

    public ContactListRepositoryImpl() {
        this.helper = FirebaseHelper.getInstance();
        eventBus = GreenRobotEventBus.getInstance();
    }

    @Override
    public void signoff() {
        helper.signOff();
    }

    @Override
    public String getCurrentUserEmail() {
        return helper.getAuthUserEmail();
    }

    @Override
    public void removeContact(String email) {
        String currentUserEmail = helper.getAuthUserEmail();
        helper.getOneContactReference(currentUserEmail, email).removeValue();
        helper.getOneContactReference(email, currentUserEmail).removeValue();
    }

    @Override
    public void destroyListener() {
        contactEventListener = null;
    }

    @Override
    public void subscribeToContactListEvents() {
        if(contactEventListener == null){
            contactEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    handleContactEvents(dataSnapshot, ContactListEvent.onContactAdded);
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                    handleContactEvents(dataSnapshot, ContactListEvent.onContactChanged);
                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {
                    handleContactEvents(dataSnapshot, ContactListEvent.onContactRemoved);
                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {}

                @Override
                public void onCancelled(DatabaseError databaseError) {}
            };
        }
        helper.getMyContactsReference(getCurrentUserEmail()).addChildEventListener(contactEventListener);
    }

    private void post(int eventType, User user) {
        ContactListEvent event = new ContactListEvent();
        event.setEventType(eventType);
        event.setUser(user);
        eventBus.post(event);
    }

    private void handleContactEvents(DataSnapshot dataSnapshot, int eventType){
        String email = dataSnapshot.getKey();
        email = email.replace("_", ".");
        boolean online = (Boolean) dataSnapshot.getValue();
        User user = new User();
        user.setEmail(email);
        user.setOnline(online);
        post(eventType, user);
    }

    @Override
    public void unsubscribeToContactListEvents() {
        if(contactEventListener != null){
            helper.getMyContactsReference(getCurrentUserEmail()).removeEventListener(contactEventListener);
        }
    }

    @Override
    public void changeConnectionStatus(boolean isOnline) {

    }
}
