package com.maelook.View;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.maelook.R;

public class testView extends Activity {

    private android.app.FragmentManager manager;
    private Fragment a,b,c;
    private Button btn1,btn2,btn3,btn4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.colorrenderinglayout);


//        btn1 = (Button) findViewById(R.id.btn1);
//        btn2 = (Button) findViewById(R.id.btn2);
//        btn3 = (Button) findViewById(R.id.btn3);
//        btn4 = (Button) findViewById(R.id.btn4);
//        btn1.setOnClickListener(this);
//        btn2.setOnClickListener(this);
//        btn3.setOnClickListener(this);
//        btn4.setOnClickListener(this);
//        manager = getFragmentManager();
//
//        a = new spactral();
//        b = new cqs();
//        c = new colorBar();


    }

//    @Override
//    public void onClick(View v) {
//        switch (v.getId()){
//            case R.id.btn1:
//                manager.beginTransaction().replace(R.id.activity_test_view,a).addToBackStack(null).commit();
//                break;
//            case R.id.btn2:
//                manager.beginTransaction().replace(R.id.activity_test_view,b).addToBackStack(null).commit();
//                break;
//            case R.id.btn3:
//                manager.beginTransaction().replace(R.id.activity_test_view,c).addToBackStack(null).commit();
//                break;
//            case R.id.btn4:
//                Intent i = new Intent(testView.this,summuryPage.class);
//                startActivity(i);
//                break;
//        }
//    }


}
