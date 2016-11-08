package com.maelook.Utils.temperature;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.maelook.R;


public class Temperature extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.temperature);

        Button buttonSearch = (Button)findViewById(R.id.search);
        Button buttonUpdate = (Button)findViewById(R.id.update);
        Button buttonBack = (Button)findViewById(R.id.back);

        final TextView textView = (TextView)findViewById(R.id.outcome);

        buttonSearch.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                double n=Math.random()*100;
                textView.setText((int)n+"摄氏度");
            }
        });

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                double n=Math.random()*100;
                textView.setText((int)n+"摄氏度");
            }
        });
        buttonBack.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                finish();
            }
        });
    }



}