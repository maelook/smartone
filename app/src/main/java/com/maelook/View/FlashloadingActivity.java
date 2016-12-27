package com.maelook.View;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.maelook.R;

/*
* 闪光测量等待activity
* */
public class FlashloadingActivity extends Activity{
    //返回按钮
    private Button btn_back;
    //Home键
    private Button btn_home;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading);


        progressBar= (ProgressBar) findViewById(R.id.progressBar);
        // 这里你可以进行一些等待时的操作，我这里用3秒后显示Toast代理等待操作
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run(){

                FlashloadingActivity.this.finish();
                Intent intent = new Intent(FlashloadingActivity.this, ParameterActivity.class);

                startActivity(intent);

                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                Toast.makeText(getApplicationContext(), "测量完毕", Toast.LENGTH_SHORT).show();

            }
        }, 3000);

    }
}