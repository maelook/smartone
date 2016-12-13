package com.maelook.View;

import android.app.Activity;
import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;

import com.maelook.R;
import com.maelook.Widget.maeChartFragment.ColorPickerDialog;
import com.maelook.Widget.maeChartFragment.drawLine;

public class testView extends Activity {

    private android.app.FragmentManager manager;
    private Fragment a,b,c;
    private Button btn1,btn2,btn3,btn4;
    private Button bg,show,save;
    private drawLine area;
    private Canvas canvas;
    private Path path = new Path();
    private long bofore;
    private long now;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.colorrenderinglayout);


//        bg = findViewById(R.id.)
        area = (drawLine) findViewById(R.id.area);
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
        area.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        path.moveTo(event.getX(),event.getY());
                        bofore = System.currentTimeMillis();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        now = System.currentTimeMillis();
                        Log.e("line",bofore+"");
                        Log.e("line",now+"");
                        if(now-bofore > 40000){
                            area.setBackground(new BitmapDrawable(area.getBitmap()));
                        }
                        path.lineTo(event.getX(),event.getY());
                        break;
                    case MotionEvent.ACTION_UP:
                        area.setPath(path);
                        area.drawPath();
                        now = System.currentTimeMillis();

                        break;
//                    case MotionEvent.Action
                }
                return true;
            }
        });

        canvas = new Canvas();
        this.area.setBitmap(getResources().getDrawable(R.drawable.bg_rainbow));
        this.area.initData();





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
