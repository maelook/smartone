package com.maelook.View;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.maelook.Bean.point;
import com.maelook.R;
import com.maelook.Widget.maeChartFragment.CQSBarChart;
import com.maelook.Widget.maeChartFragment.CQSCoordinateChart;
import com.maelook.Widget.maeChartFragment.colorRenderingBarChart;
import com.maelook.Widget.maeChartFragment.colorRenderingPieChart;
import com.maelook.Widget.maeChartFragment.spectralCurveChart;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class fogtestview extends Activity {

//    private spectralCurveChart fog;
//    private colorRenderingBarChart fog;
//    private colorRenderingPieChart fog;
//    private CQSBarChart fog;
    private CQSCoordinateChart fog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fogtestview);

        //光谱图
//        fog = (spectralCurveChart) findViewById(R.id.fog);
//        double[] data = new double[401];
//        Random random = new Random();
//        for(int i=0;i< data.length;i++){
//            data[i] = random.nextDouble();
//        }
//        fog.setData(data);


        //colorBar
//        fog = (colorRenderingBarChart) findViewById(R.id.fog);
//        fog.setData(new double[]{5,10,15,20,25,30,35,40,45,50,55,60,65,70,75,80,85});

        //colorPie
//        fog = (colorRenderingPieChart) findViewById(R.id.fog);
//        double[] data = new double[15];
//        for (int i=1;i<=15;i++) {
//            data[i-1]=(5*i);
//        }
//        data[0] = 30;
//        data[1] = 40;
//        data[2] = 60;
//        data[3] = 20;
//        data[4] = 70;
//        data[9] = 40;
//        data[12] = 60;
//        fog.setData(data);

//        //CQSBar
//        fog = (CQSBarChart) findViewById(R.id.fog);
//        Random random = new Random();
//        int[] data = new int[16];
//        for(int i=0;i< data.length;i++){
//            data[i] = random.nextInt(100);
//        }
//        fog.setData(data);

        //CQSCoordinate    在坐标系中，一定要用点！！！
        fog = (CQSCoordinateChart) findViewById(R.id.fog);
        ArrayList<point> data = new ArrayList<>();
        data.add(new point(-80,20));
        data.add(new point(-30,40));
        data.add(new point(10,-60));
        data.add(new point(40,-50));
        data.add(new point(40,50));
        data.add(new point(20,60));
        data.add(new point(-40,-50));
        data.add(new point(-50,-40));
        data.add(new point(30,-80));
        data.add(new point(-20,70));
        data.add(new point(-50,60));
        fog.setData(data);



    }
}
