package com.maelook.View;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;

import com.maelook.R;

public class UserFeedbackActivity extends Activity {
    private ModifyPasswordActivity modifyPasswordActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_feedback);
    }
    public void BackFeedback(View view ){
        finish();

    }
    /*public void PostEmail(View view){

       *//* Uri uri = Uri.parse("http://user.qzone.qq.com/707554779");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);*//*
        modifyPasswordActivity=new ModifyPasswordActivity(this,onClickListener);
        modifyPasswordActivity.showAtLocation(findViewById(R.id.create_user_dialog_view), Gravity.CENTER, 0, 0);



    }
    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            switch (v.getId()) {
                case R.id.btn_save_pop:

                    String name1 = modifyPasswordActivity.text_name.getText().toString().trim();
                    String mobile1 = modifyPasswordActivity.text_mobile.getText().toString().trim();
                    String info1 = modifyPasswordActivity.text_info.getText().toString().trim();

                    System.out.println(name1+"——"+mobile1+"——"+info1);

                    modifyPasswordActivity.dismiss();
                    break;
            }
        }
    };
*/
}
