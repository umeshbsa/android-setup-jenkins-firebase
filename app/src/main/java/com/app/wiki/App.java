package com.app.wiki;


import android.app.Application;

/*
 * App : App class to initiate dagger2 component
 * */
public class App extends Application {


    public static App app;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
    }
}
