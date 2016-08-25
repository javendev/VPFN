package com.javen.vpfn.ui;

import android.app.Application;

import com.orhanobut.logger.Logger;

/**
 * Created by Javen on 16/8/25.
 */
public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Logger.init("Javen205");
    }
}
