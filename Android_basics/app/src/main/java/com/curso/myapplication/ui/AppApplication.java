package com.curso.myapplication.ui;

import android.app.Application;
import android.content.Context;

import com.curso.myapplication.database.AppDatabase;

public class AppApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();


        getDataBaseInstance(this);
    }

    private void getDataBaseInstance(Context context) {
        AppDatabase.getDataBaseInstance(context);
    }
}
