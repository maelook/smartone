package com.maelook.View;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.maelook.R;

public class UserFeedbackActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_feedback);
    }
    /*public void BackFeedback(View view ){
        Intent intent = new Intent (UserFeedbackActivity.this,SettingActivity.class);
        startActivity(intent);

    }*/
    public void PostEmail(View view){

        Uri uri = Uri.parse("http://user.qzone.qq.com/707554779");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);


    }
}
