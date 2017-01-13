package com.maelook.Widget.maeChartFragment.testFragment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.maelook.R;
import com.maelook.Widget.maeChartFragment.cie1960Chart;

public class fog extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fog);

        cie1960Chart f = (cie1960Chart) findViewById(R.id.cie1960);




    }
}
