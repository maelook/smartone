package com.maelook.View;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.maelook.R;

public class IlluminationActivity extends Activity {
    private ImageView displayill;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_illumination);
        displayill= (ImageView) findViewById(R.id.displayill);

        /*
        *
        * 接受上个页面的参数
        *
        *
        * */
        Intent intent = getIntent();
        String NUM = intent.getStringExtra("A");
    }
    public void GoCalculate(View view){
        Intent intent=new Intent();
        intent.setClass(IlluminationActivity.this,IlluminationCalculationActivity.class);
        startActivity(intent);
    }
    public void illback(View view){
        finish();
    }
    public void ill_home(View view){
        Intent intent=new Intent(IlluminationActivity.this,FirstActivity.class);
        startActivity(intent);
    }

}
