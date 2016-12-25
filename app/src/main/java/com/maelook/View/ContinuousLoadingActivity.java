package com.maelook.View;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.maelook.R;

public class ContinuousLoadingActivity extends Activity {
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_continuous_loading);

        progressBar= (ProgressBar) findViewById(R.id.progressBar);
        // 这里你可以进行一些等待时的操作，我这里用3秒后显示Toast代理等待操作
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run(){

                ContinuousLoadingActivity.this.finish();
                Intent intent = new Intent(ContinuousLoadingActivity.this, ParameterActivity.class);

                startActivity(intent);

                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                Toast.makeText(getApplicationContext(), "测量完毕", Toast.LENGTH_SHORT).show();

            }
        }, 5000);

    }
}