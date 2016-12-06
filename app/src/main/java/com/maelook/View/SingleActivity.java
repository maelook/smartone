package com.maelook.View;

import android.app.Activity;
import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.maelook.R;
import com.maelook.View.TestData.MainActivity;
import com.maelook.Widget.maeChartFragment.testFragment.summuryPage;

/*
* 单次测量activity
* */
public class SingleActivity extends Activity implements View.OnClickListener {

    private Button btn_back;

    private Button btn_going_btn;

    private Button btn_home;
    private Button icon1,icon2,icon3,icon4,icon5,
                    icon6,icon7,icon8,icon9,icon10,
                    icon11,icon12,icon13,icon14,icon15;
    //静态常亮随着程序的关闭而消失，如果要永久的记住颜色可以存储在本地
    public static String seller_id = "0", available = "0",canorder="0";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.MyAppCompat);
        setContentView(R.layout.activity_single);
        initView();
    }
    /*
    *
    * 初始化组件
    * */
    public void initView(){
        btn_back = (Button) findViewById(R.id.btn_back);
        btn_going_btn = (Button) findViewById(R.id.btn_going_btn);
        btn_home= (Button) findViewById(R.id.btn_home);
        icon1= (Button) findViewById(R.id.icon1);
        icon2= (Button) findViewById(R.id.icon2);
        icon3= (Button) findViewById(R.id.icon3);
        icon4= (Button) findViewById(R.id.icon4);
        icon5= (Button) findViewById(R.id.icon5);
        icon6= (Button) findViewById(R.id.icon6);
        icon7= (Button) findViewById(R.id.icon7);
        icon8= (Button) findViewById(R.id.icon8);
        icon9= (Button) findViewById(R.id.icon9);
        icon10= (Button) findViewById(R.id.icon10);
        icon11= (Button) findViewById(R.id.icon11);
        icon12= (Button) findViewById(R.id.icon12);
        icon13= (Button) findViewById(R.id.icon13);
        icon14= (Button) findViewById(R.id.icon14);
        icon15= (Button) findViewById(R.id.icon15);


        //设置颜色
      /*  if (seller_id.equals("1")){
            icon1.setBackgroundResource(R.drawable.shape_col);
        }
        else if (seller_id.equals("0")|| seller_id.equals("")) {
            icon1.setBackgroundResource(R.drawable.shape_col1);

        }
        if (available.equals("1")){
            icon2.setBackgroundResource(R.drawable.shape_col);
        }
        else if (available.equals("0")|| available.equals("")) {
            icon2.setBackgroundResource(R.drawable.shape_col1);

        }
        if (canorder.equals("1")){
            icon3.setBackgroundResource(R.drawable.shape_col);
        }
        else if (canorder.equals("0")|| canorder.equals("")) {
            icon3.setBackgroundResource(R.drawable.shape_col1);

        }*/

        if (seller_id.equals("1")){
            icon1.setBackgroundResource(R.drawable.shape_col);
        }
        else if (seller_id.equals("0")|| seller_id.equals("")) {
            icon1.setBackgroundResource(R.drawable.shape_col1);

        }
        if (available.equals("1")){
            icon2.setBackgroundResource(R.drawable.shape_col);
        }
        else if (available.equals("0")|| available.equals("")) {
            icon2.setBackgroundResource(R.drawable.shape_col1);

        }
        if (canorder.equals("1")){
            icon3.setBackgroundResource(R.drawable.shape_col);
        }
        else if (canorder.equals("0")|| canorder.equals("")) {
            icon3.setBackgroundResource(R.drawable.shape_col1);

        }

        }

    public void btn_home(View view){
        Intent intent = new Intent();
        intent.setClass(SingleActivity.this, FirstActivity.class);
        SingleActivity.this.startActivity(intent);
        SingleActivity.this.finish();
    }

    public void btn_my_launcher(View back){
        Intent intent = new Intent();
        intent.setClass(SingleActivity.this, FirstActivity.class);
        SingleActivity.this.startActivity(intent);
        SingleActivity.this.finish();

    }


    public void Measure_Going(View going ){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setIcon(R.mipmap.my_launcher);
        builder.setTitle("请连接硬件");
        builder.setMessage("你确定要测量吗？");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                Intent intent=new Intent();
                intent.setClass(SingleActivity.this, MainActivity.class);
                startActivity(intent);
                Toast.makeText(SingleActivity.this, "对不起你没有连接硬件设备", Toast.LENGTH_SHORT).show();

            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {  //取消按钮

            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                Toast.makeText(SingleActivity.this, "精彩内容不要放过",Toast.LENGTH_SHORT).show();

            }
        });
        AlertDialog b=builder.create();
        b.show();

    }
    /*
    *
    * 十五参数点击事件
    *
    *
    * */

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.icon1:
                if (seller_id.equals("0") || seller_id.equals("")) {
                    icon1.setBackgroundResource(R.drawable.shape_col1);
                    seller_id = "1";
                }
                else if (seller_id.equals("1")) {

                    icon1.setBackgroundResource(R.drawable.shape_col);
                    seller_id = "0";
                }

                break;
            case R.id.icon2:
                if (available.equals("0") || available.equals("")) {
                    icon2.setBackgroundResource(R.drawable.shape_col1);
                    available = "1";
                }
                else if (available.equals("1")) {

                    icon2.setBackgroundResource(R.drawable.shape_col);
                    available = "0";
                }

                break;
            case R.id.icon3:
                if (canorder.equals("0") || canorder.equals("")) {
                    icon3.setBackgroundResource(R.drawable.shape_col1);
                    canorder = "1";
                }
                else if (canorder.equals("1")) {

                    icon3.setBackgroundResource(R.drawable.shape_col);
                    canorder = "0";
                }

                break;
            default:

                break;

        }


    }
}
