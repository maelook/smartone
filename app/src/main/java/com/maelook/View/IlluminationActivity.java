package com.maelook.View;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.maelook.R;

public class IlluminationActivity extends Activity {
    private TextView displayill;
    private String str;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_illumination);
        displayill= (TextView) findViewById(R.id.displayill);

        /*
        *
        * 接受上个页面的参数
        *
        *
        * */
        Bundle list=getIntent().getExtras();
        Log.e("text","text-----"+list.getDouble("key"));
        str= String.valueOf(list.getDouble("key"));
        displayill.setGravity(Gravity.CENTER);
        displayill.setText(str);

    }
    public void GoCalculate(View view){

        finish();
    }
    public void illback(View view){
        finish();
    }
    public void ill_home(View view){
        Intent intent=new Intent(IlluminationActivity.this,FirstActivity.class);
        startActivity(intent);
    }

}
