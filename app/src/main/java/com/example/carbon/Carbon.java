package com.example.carbon;

import android.app.Application;

import com.google.firebase.database.FirebaseDatabase;

public class Carbon extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }
}
