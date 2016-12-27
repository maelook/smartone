package com.maelook.View;

import android.app.Fragment;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Path;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.maelook.Bean.point;
import com.maelook.R;
import com.maelook.Utils.spactrumToParameterUtil;
import com.maelook.Widget.maeChartFragment.CQSBarChart;
import com.maelook.Widget.maeChartFragment.CQSCoordinateChart;
import com.maelook.Widget.maeChartFragment.GaiChart;
import com.maelook.Widget.maeChartFragment.cie1931Chart;
import com.maelook.Widget.maeChartFragment.cie1976Chart;
import com.maelook.Widget.maeChartFragment.colorMixView;
import com.maelook.Widget.maeChartFragment.colorRenderingPieChart;
import com.maelook.Widget.maeChartFragment.colorVectorChart;
import com.maelook.Widget.maeChartFragment.drawLine;
import com.maelook.Widget.maeChartFragment.spectralCurveChart;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

import static com.maelook.app.maelookApp.appDocument;

public class testView extends AppCompatActivity {

    private android.app.FragmentManager manager;
    private Fragment a,b,c;
    private Button btn1,btn2,btn3,btn4;
    private Button bg,show,save;
    private drawLine area;
    private Path path = new Path();
    private long bofore;
    private long now;
    private boolean already = false;
//    private spectralCurveChart fog;
    private colorMixView fog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.MyAppCompat);
        setContentView(R.layout.colorrenderinglayout);

        fog = (colorMixView) findViewById(R.id.fog);
        double[] data = new double[401];

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(appDocument+File.separator+"data1.txt")));
            String line = "";
            int i= 0;
            while((line = reader.readLine()) != null){
                data[i] = Float.parseFloat(line);
//                Log.e("data","data:"+data[i]);
                i++;
            }
            spactrumToParameterUtil p = new spactrumToParameterUtil(data);
            p.initPrameters();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        fog.setData(data);

//        bg = (Button) findViewById(R.id.bg);
//        show = (Button) findViewById(R.id.show);
//        save = (Button) findViewById(R.id.save);
//        area = (drawLine) findViewById(R.id.area);


//        bg.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                area.setDrawable(getResources().getDrawable(R.drawable.bg_rainbow));
//            }
//        });
//        show.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                area.initData();
//            }
//        });
//        save.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                area.drawFog();
//
//                try {
//                    FileOutputStream out = new FileOutputStream(appDocument+ File.separator+"aaa.jpg");
//                    Bitmap bitmap = area.save();
//
//                    bitmap.compress(Bitmap.CompressFormat.JPEG,100,out);
//                    out.flush();
//
//                } catch (FileNotFoundException e) {
//                    e.printStackTrace();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//            }
//        });


//        btn1 = (Button) findViewById(R.id.btn1);
//        btn2 = (Button) findViewById(R.id.btn2);
//        btn3 = (Button) findViewById(R.id.btn3);
////        btn1.setBackgroundColor(getResources().getColor(R.color.red));
//        btn1.setBackgroundColor(Color.parseColor("#FF0000"));
////        btn2.setBackgroundColor(getResources().getColor(R.color.blue));
//        btn2.setBackgroundColor(Color.parseColor("#0000FF"));
//        Integer color = blendColor(0x0000FF,0xff0000);
//        Log.e("color","int:"+color);
//        btn3.setBackgroundColor(Color.parseColor("#"+color));
//
//
//        ColorPickerDialog dialog = new ColorPickerDialog(this, "color picker", new ColorPickerDialog.OnColorChangedListener() {
//            @Override
//            public void colorChanged(int color) {
//
//            }
//        });
//        dialog.show();

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
//        area.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                switch (event.getAction()){
//                    case MotionEvent.ACTION_DOWN:
//                        if (already == true){
//                            area.addPoint(new point(event.getX(),event.getY()).setDeclare("随机数值"));
//                        }else{
//                            path.moveTo(event.getX(),event.getY());
//                            bofore = System.currentTimeMillis();
//                        }
//                        break;
//                    case MotionEvent.ACTION_MOVE:
//                        now = System.currentTimeMillis();
//                        if (already == false) {
//                            path.lineTo(event.getX(),event.getY());
//                            area.setPath(path);
//                            area.drawPath();
//                        }
//
//
//                        break;
//                    case MotionEvent.ACTION_UP:
//
//                        if (already == false) {
//                            AlertDialog.Builder builder = new AlertDialog.Builder(testView.this);
//                            builder.setTitle("确认：");
//                            builder.setMessage("是否已经选好区域？");
//                            builder.setPositiveButton("是", new DialogInterface.OnClickListener() {
//                                @Override
//                                public void onClick(DialogInterface dialog, int which) {
//                                    already = true;
//                                    area.initData();
//                                }
//                            });
//                            builder.setNegativeButton("否", new DialogInterface.OnClickListener() {
//                                @Override
//                                public void onClick(DialogInterface dialog, int which) {
//                                    dialog.cancel();
//                                }
//                            });
//                            builder.create().show();
//                        } else {
//
//                        }
//
//
//
//
//                        break;
//                }
//                return true;
//            }
//        });


//        Random random = new Random();
//        int[] data = new int[16];
//        for (int i=0;i<data.length;i++){
//            data[i] = (int) (random.nextDouble()*100);
//        }
//        data[0] = 100;
//        data[1] = 80;
//        data[2] = 60;
//        data[3] = 40;
//        data[4] = 20;
//        data[5] = 0;
//
//        fog.setData(data);
//        Button b = (Button) findViewById(R.id.b);
//        b.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Bitmap bitmap = fog.save();
//                try {
//                    FileOutputStream fos = new FileOutputStream(new File(appDocument+File.separator+"test.jpeg"));
//                    bitmap.compress(Bitmap.CompressFormat.JPEG,100,fos);
//                    fos.flush();
//
//                    fos.close();
//                } catch (FileNotFoundException e) {
//                    e.printStackTrace();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        });



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

    public int blendColor(int fg, int bg) {
        int scr = Color.red(fg);
        int scg = Color.green(fg);
        int scb = Color.blue(fg);
        int sa = fg >>> 24;
        int dcr = Color.red(bg);
        int dcg = Color.green(bg);
        int dcb = Color.blue(bg);
        int color_r = dcr * (0xff - sa) / 0xff + scr * sa / 0xff;
        int color_g = dcg * (0xff - sa) / 0xff + scg * sa / 0xff;
        int color_b = dcb * (0xff - sa) / 0xff + scb * sa / 0xff;
        return ((color_r << 16) + (color_g << 8) + color_b) | (0xff000000);
    }

    public void setBitmap(Bitmap b) {



    }
}
