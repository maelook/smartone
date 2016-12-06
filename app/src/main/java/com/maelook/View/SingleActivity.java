package com.maelook.View;

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
public class SingleActivity extends AppCompatActivity {

    private Button btn_back;

    private Button btn_going_btn;

    private Button btn_home;
    private Button icon1,icon2,icon3,icon4,icon5,
                    icon6,icon7,icon8,icon9,icon10,
                    icon11,icon12,icon13,icon14,icon15;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.MyAppCompat);
        setContentView(R.layout.activity_single);

        initView();



        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter
                .getDefaultAdapter();
        if (mBluetoothAdapter == null) {
            Toast.makeText(this, "本机没有找到蓝牙硬件或驱动！", Toast.LENGTH_SHORT).show();
            finish();
        }

        if (!mBluetoothAdapter.isEnabled()) {

            Intent mIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(mIntent, 1);

        }


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


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(this, "蓝牙已经开启", Toast.LENGTH_SHORT).show();
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "不允许蓝牙开启", Toast.LENGTH_SHORT).show();
            }
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
    public void btn_1(View v1){

        Intent intent=new Intent();
        intent.setClass(SingleActivity.this,summuryPage.class);
        startActivity(intent);

    }
    public void btn_2(View v2){


    }
}
