package com.maelook.app;

import android.app.Application;
import android.content.Context;
import android.os.Environment;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import java.io.File;

/**
 * Created by Andrew on 2016/10/30.
 */

public class maelookApp extends MultiDexApplication {
    public static final String appDocument = Environment.getExternalStorageDirectory()+ File.separator+"com.maelook";

    @Override
    public void onCreate() {
        super.onCreate();
//        MultiDex.install(this);


        File app = new File(appDocument);
        if (!app.exists()){
            app.mkdirs();
        }


    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
