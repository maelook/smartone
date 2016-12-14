package com.maelook.View;

import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.maelook.Bean.point;
import com.maelook.R;
import com.maelook.Widget.maeChartFragment.CQSBarChart;
import com.maelook.Widget.maeChartFragment.CQSCoordinateChart;
import com.maelook.Widget.maeChartFragment.DataMap;
import com.maelook.Widget.maeChartFragment.LightSceneView;
import com.maelook.Widget.maeChartFragment.colorRenderingBarChart;
import com.maelook.Widget.maeChartFragment.colorRenderingPieChart;
import com.maelook.Widget.maeChartFragment.spectralCurveChart;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

import static com.maelook.app.maelookApp.appDocument;

public class fogtestview extends AppCompatActivity {


//    private double[] data;
    private static Path path = new Path();
    private static ArrayList<point> data = new ArrayList<>();
    private boolean already = false;
    private static DataMap fog;
    private Paint dataPaint = new Paint();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.MyAppCompat);
        setContentView(R.layout.activity_fogtestview);

        fog = (DataMap) findViewById(R.id.fog);
        fog.setBg_bitmap(BitmapFactory.decodeResource(getResources(), R.drawable.fog));
//        initData();

        dataPaint.setStrokeWidth(60);
        dataPaint.setColor(getResources().getColor(R.color.pink));
        dataPaint.setStyle(Paint.Style.STROKE);
        fog.setDataPaint(dataPaint);
        Paint pathPaint = new Paint();
        pathPaint.setStrokeWidth(30);
        pathPaint.setStyle(Paint.Style.STROKE);
        pathPaint.setColor(getResources().getColor(R.color.red));
        fog.setAeraPaint(pathPaint);
        fog.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        if (already == true){
                            data.add(new point(event.getX(),event.getY()).setDeclare("随机数值"));
                            fog.setData(data);
                        }else{
                            path.moveTo(event.getX(),event.getY());
                            Log.e("path","init");
                        }
                        break;
                    case MotionEvent.ACTION_MOVE:
                        if (already == false) {
                            path.lineTo(event.getX(),event.getY());
                            Log.e("path","add");
                            fog.setAera(path);
                        }

                        break;
                    case MotionEvent.ACTION_UP:

                        if (already == false) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(fogtestview.this);
                            builder.setTitle("确认：");
                            builder.setMessage("是否已经选好区域？");
                            builder.setPositiveButton("是", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    already = true;
                                }
                            });
                            builder.setNegativeButton("否", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });
                            builder.create().show();
                        }
                        break;
                }
                return true;
            }
        });

    }
//
//    public void initData() {
//        try {
//            this.data = new double[401];
//            FileInputStream in = new FileInputStream(appDocument + File.separator + "data.txt");
//            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
//            String line = "";
//            int i = 0;
//            while ((line = reader.readLine()) != null) {
//                this.data[i++] = Float.parseFloat(line);
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}
