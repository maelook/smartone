package com.maelook.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.maelook.R;
/*
* 多次测量activity
* */
public class ManyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.MyAppCompat);
        setContentView(R.layout.activity_many);
    }
}
