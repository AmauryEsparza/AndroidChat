package com.example.amauryesparza.androidchat.domain;

import com.example.amauryesparza.androidchat.entities.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by AmauryEsparza on 7/13/16.
 */

public class FirebaseHelper {

    private DatabaseReference dataBaseReference;
    private FirebaseAuth authData;

    private static final String SEPARATOR = "__";
    private static final String FIREBASE_URL = "https://androidchat-15493.firebaseio.com/";
    private static final String USERS_PATH = "users";
    private static final String CHATS_PATH = "chats";
    private static final String CONTACTS_PATH = "contacts";

    private static class SingletonHolder{
        private static final FirebaseHelper INSTANCE = new FirebaseHelper();
    }
    private FirebaseHelper(){
        this.dataBaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl(FIREBASE_URL);
        this.authData = FirebaseAuth.getInstance();
    }

    public DatabaseReference getDataReference(){
        return this.dataBaseReference;
    }

    public static FirebaseHelper getInstance(){
        return SingletonHolder.INSTANCE;
    }

    public String getAuthUserEmail(){
        String email = null;
        if(authData != null){
            FirebaseUser user = authData.getCurrentUser();
            if(user != null) {
                email =user.getEmail();
            }
        }
        return email;
    }

    public DatabaseReference getUserReference(String email){
        DatabaseReference userReference = null;
        if(email != null){
            String emailKey = email.replace('.', '_');
            userReference = dataBaseReference.getRoot().child(USERS_PATH).child(emailKey);
        }
        return userReference;
    }

    public DatabaseReference getMyUserReference(){
        return getUserReference(getAuthUserEmail());
    }

    public DatabaseReference getContactsReference(String email){
        return getUserReference(email).child(CONTACTS_PATH);
    }

    public DatabaseReference getMyContactsReference(String email){
        return getContactsReference(email);
    }

    public DatabaseReference getOneContactReference(String mainEmail, String childEmail){
        String childKey = childEmail.replace('.', '_');
        return getUserReference(mainEmail).child(CONTACTS_PATH).child(childKey);
    }

    public DatabaseReference getChatsReference(String receiver){
        String keySender = getAuthUserEmail().replace(".", "_");
        String keyReceiver = receiver.replace(".", "_");

        String keyChat = keySender + SEPARATOR + keyReceiver;
        if(keySender.compareTo(keyReceiver) > 0){
            keyChat = keyReceiver + SEPARATOR + keySender;
        }
        return dataBaseReference.getRoot().child(CHATS_PATH).child(keyChat);
    }

    public void changeUserConnectionStatus(boolean isOnline){
        if(getMyUserReference() != null){
            Map<String, Object> updatedValues = new HashMap<String, Object>();
            updatedValues.put("isOnline", isOnline);
            getMyUserReference().updateChildren(updatedValues);
            notifyConnectedStatusChange(isOnline);
        }
    }

    public void notifyConnectedStatusChange(boolean isOnline){
        notifyConnectedStatusChange(isOnline, false);
    }

    public void notifyConnectedStatusChange(final boolean isOnline, final boolean signOff){
        final String myEmail = getAuthUserEmail();
        getMyContactsReference(myEmail).addListenerForSingleValueEvent(new ValueEventListener(){
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot child : dataSnapshot.getChildren()){
                    String email = child.getKey();
                    DatabaseReference reference = getOneContactReference(email, myEmail);
                    reference.setValue(isOnline);
                }
                if(signOff){
                    authData.signOut();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void signOff(){
        notifyConnectedStatusChange(User.OFFLINE, true);
    }

}
