package com.maelook.View.db;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.maelook.R;
import com.maelook.Widget.maeChartFragment.spectralCurveChart;

public class show extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.MyAppCompat);
        setContentView(R.layout.activity_show);
        spectralCurveChart spectralCurveChart = (com.maelook.Widget.maeChartFragment.spectralCurveChart) findViewById(R.id.show);

        Bundle bundle = getIntent().getExtras();
        double[] data = bundle.getDoubleArray("data");

        spectralCurveChart.setData(data);




    }
}
