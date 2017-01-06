package com.maelook.View;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.maelook.R;

public class ContinuousLoadingActivity extends Activity {
    private ProgressBar progressBar;
    private Button StopMeasure;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_continuous_loading);

        progressBar= (ProgressBar) findViewById(R.id.progressBar);

    }
    public void StopMeasure(View view){
        Intent intent=new Intent(ContinuousLoadingActivity.this,ParameterContinueActivity.class);
        startActivity(intent);

    }
}