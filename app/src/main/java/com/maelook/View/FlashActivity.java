package com.maelook.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.maelook.R;
/*
*
* 闪光测量activity
*
* */
public class FlashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.MyAppCompat);
        setContentView(R.layout.activity_flash);
    }
}
