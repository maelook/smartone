package com.maelook.View;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.graphics.Path;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.maelook.Bean.point;
import com.maelook.R;
import com.maelook.Utils.GuideUtil;
import com.maelook.Widget.maeChartFragment.DataMap;

import java.io.File;
import java.util.ArrayList;

import static com.maelook.app.maelookApp.appDocument;

public class DataMapActivity extends Activity {

    private static Path path = new Path();
    private static ArrayList<point> data = new ArrayList<>();
    private boolean already = false;
    private static DataMap dataMap;
    private Paint dataPaint = new Paint();
    private Button btn_back;
    private Button save_dataMap;
    private Button share_light_scene;
    private Uri imageUri; //图片路径
    private String filename; //图片名称
    private GuideUtil guideUtil = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.MyAppCompat);
        setContentView(R.layout.activity_data_map);
        guideUtil=GuideUtil.getInstance();
        guideUtil.initGuide(this,R.mipmap.newguide5);
        btn_back= (Button) findViewById(R.id.data_back);
        save_dataMap= (Button) findViewById(R.id.save_dataMap);
        share_light_scene= (Button) findViewById(R.id.share_light_scene);

        dataMap = (DataMap) findViewById(R.id.dataMap);
        dataMap.setBg_bitmap(BitmapFactory.decodeResource(getResources(), R.drawable.bgpic));

        dataPaint.setTextSize(200);
        dataPaint.setColor(getResources().getColor(R.color.pink));
        dataPaint.setStyle(Paint.Style.STROKE);

        Paint pathPaint = new Paint();
        pathPaint.setStrokeWidth(60);
        pathPaint.setStyle(Paint.Style.STROKE);
        pathPaint.setColor(getResources().getColor(R.color.red));

        dataMap.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        if (already == true){

                            data.add(new point(event.getX(),event.getY()).setDeclare("AAA"));
                            dataMap.setData(data);
                        }else{
                            path.moveTo(event.getX(),event.getY());
                            Log.e("path","init");
                        }
                        break;
                    case MotionEvent.ACTION_MOVE:
                        if (already == false) {
                            path.lineTo(event.getX(),event.getY());
                            Log.e("path","add");
                            dataMap.setAera(path);
                        }

                        break;
                    case MotionEvent.ACTION_UP:

                        if (already == false) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(DataMapActivity.this);
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
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(DataMapActivity.this).setTitle("系统提示")//设置对话框标题

                        .setMessage("请确认所有数据都保存后再返回上一级！")//设置显示的内容
                        .setIcon(R.mipmap.ic_launcher)

                        .setPositiveButton("确定",new DialogInterface.OnClickListener() {//添加确定按钮

                            @Override
                            public void onClick(DialogInterface dialog, int which) {//确定按钮的响应事件
                                path=new Path();
                                path.isEmpty();

                                finish();
                            }

                        }).setNegativeButton("返回",new DialogInterface.OnClickListener() {//添加返回按钮
                            @Override
                            public void onClick(DialogInterface dialog, int which) {//响应事件

                                Log.i("alertdialog"," 请保存数据！");
                            }
                        }).show();//在按键响应事件中显示此对话框

            }

        });
    }

    public void save(View view){

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            new AlertDialog.Builder(DataMapActivity.this).setTitle("系统提示")//设置对话框标题

                    .setMessage("请确认所有数据都保存后再返回上一级！")//设置显示的内容
                    .setIcon(R.mipmap.ic_launcher)

                    .setPositiveButton("确定",new DialogInterface.OnClickListener() {//添加确定按钮

                        @Override
                        public void onClick(DialogInterface dialog, int which) {//确定按钮的响应事件
                            path=new Path();
                            path.isEmpty();

                            finish();
                        }

                    }).setNegativeButton("返回",new DialogInterface.OnClickListener() {//添加返回按钮
                @Override
                public void onClick(DialogInterface dialog, int which) {//响应事件

                    Log.i("alertdialog"," 请保存数据！");
                }
            }).show();//在按键响应事件中显示此对话框

        }

        return false;
    }


    public void share(View view){
        File f = new File(appDocument+"/1.jpg");
        Log.e("f","aaa"+f);
        System.out.print(f);
        Uri uri = Uri.fromFile(f);
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
        shareIntent.putExtra(Intent.EXTRA_SUBJECT,"分享");
        shareIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        shareIntent.setType("image/*");
        startActivity(Intent.createChooser(shareIntent,getTitle()));
    }
    public void btn_home_datamap(View view){
        Intent intent=new Intent(DataMapActivity.this,FirstActivity.class);
        startActivity(intent);

    }
    public void btn_my_launcher(View view){
        path=new Path();
        path.isEmpty();
        finish();

    }
    public void Clear(View view){
        dataMap.setAera(null);
        dataMap.setData(null);
        path=new Path();
        path.isEmpty();
        data.size();

        
    }

}
