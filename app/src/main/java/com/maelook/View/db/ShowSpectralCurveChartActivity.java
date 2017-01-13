package com.maelook.View.db;

import android.app.Activity;
import android.os.Bundle;

import com.maelook.R;
import com.maelook.Utils.spactrumToParameterUtil;
import com.maelook.Widget.maeChartFragment.spectralCurveChart;

public class ShowSpectralCurveChartActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.MyAppCompat);
        setContentView(R.layout.activity_show_spectral_curve_chart);
        spectralCurveChart spectralCurveChart = (com.maelook.Widget.maeChartFragment.spectralCurveChart) findViewById(R.id.show);

        Bundle bundle = getIntent().getExtras();
        double[] data = bundle.getDoubleArray("data");
        spactrumToParameterUtil sp=new spactrumToParameterUtil(data);
        sp.initPrameters();

        spectralCurveChart.setData(sp.getTestDataBeOne());




    }
}
