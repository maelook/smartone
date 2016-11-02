package com.maelook.app;

import android.app.Application;
import android.os.Environment;

import com.maelook.Utils.dataBaseUtil;

import java.io.File;

/**
 * Created by Andrew on 2016/10/30.
 */

public class maelookApp extends Application {
    public static final String appDocument = Environment.getExternalStorageDirectory()+ File.separator+"com.maelook";

    @Override
    public void onCreate() {
        super.onCreate();

        dataBaseUtil a = new dataBaseUtil(this,"record");

    }
}
