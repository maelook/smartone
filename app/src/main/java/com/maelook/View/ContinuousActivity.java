package com.maelook.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.maelook.R;
import com.maelook.fragment.MeasureFragment;

/*
* 连续测量activity
* */
public class ContinuousActivity extends AppCompatActivity {
    private Button back_btn;
    private Button home_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.MyAppCompat);
        setContentView(R.layout.activity_continuous);
        back_btn= (Button) findViewById(R.id.back_btn);
        home_btn= (Button) findViewById(R.id.home_btn);



        home_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(ContinuousActivity.this,FirstActivity.class);
                ContinuousActivity.this.startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
