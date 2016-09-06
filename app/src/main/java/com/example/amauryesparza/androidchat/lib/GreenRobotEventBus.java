package com.example.amauryesparza.androidchat.lib;

/**
 * Created by AmauryEsparza on 9/2/16.
 */

public class GreenRobotEventBus implements EventBus {

    private de.greenrobot.event.EventBus eventBus;

    private static class SingletonHolder{
        private static final GreenRobotEventBus INSTANCE = new GreenRobotEventBus();
    }

    public static GreenRobotEventBus getInstance(){
        return SingletonHolder.INSTANCE;
    }

    private GreenRobotEventBus(){
        this.eventBus = de.greenrobot.event.EventBus.getDefault();
    }

    @Override
    public void register(Object subscribe) {
        eventBus.register(subscribe);
    }

    @Override
    public void unregister(Object subscribe) {
        eventBus.unregister(subscribe);
    }

    @Override
    public void post(Object event) {
        eventBus.post(event);
    }
}
