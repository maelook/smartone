package com.maelook.View;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.maelook.R;

public class NewGuideActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_guide);
    }
    /*
    *
    * 如果有硬件设备跳转到蓝牙连接
    *
    * */
    public void have(View view){
        Intent intent=new Intent(NewGuideActivity.this, FirstActivity.class);
        startActivity(intent);


    }
    /*
    *
    * 无硬件直接进入主页
    *
    * */
    public void haveNot(View view){
        Uri uri = Uri.parse("http://www.maelook.cn");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);

    }
}
