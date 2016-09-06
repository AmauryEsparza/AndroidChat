package com.example.amauryesparza.androidchat.lib;

/**
 * Created by AmauryEsparza on 9/2/16.
 */

public interface EventBus {
    void register(Object subscribe);
    void unregister(Object subscribe);
    void post(Object event);
}
