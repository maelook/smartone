package com.maelook.View;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;
import com.maelook.R;
import com.maelook.fragment.MeasureFragment;

/*
* 单次测量activity
* */
public class SingleActivity extends Activity implements View.OnClickListener {
    //返回按钮
    private Button btn_back;
    //立即测量
    private Button btn_going_btn;
    //Home键
    private Button btn_home;
    //15个参数按钮
    private Button icon1,icon2,icon3,icon4,icon5,
                    icon6,icon7,icon8,icon9,icon10,
                    icon11,icon12;
    private FragmentTransaction transaction;
    //静态常亮随着程序的关闭而消失，如果要永久的记住颜色可以存储在本地
    public static String icon1_id = "0", icon2_id = "0",icon3_id="0",
            icon4_id="0",icon5_id="0",icon6_id="0",icon7_id="0",icon8_id="0",
            icon9_id="0",icon10_id="0",icon11_id="0",icon12_id="0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single);
        initView();
    }
    /*
    *
    * 初始化组件
    * */
    public void initView(){
        btn_back = (Button) findViewById(R.id.singleback);
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

        /*
        * 点击事件监听器
        * */
        icon1.setOnClickListener(this);
        icon2.setOnClickListener(this);
        icon3.setOnClickListener(this);
        icon4.setOnClickListener(this);
        icon5.setOnClickListener(this);
        icon6.setOnClickListener(this);
        icon7.setOnClickListener(this);
        icon8.setOnClickListener(this);
        icon9.setOnClickListener(this);
        icon10.setOnClickListener(this);
        icon11.setOnClickListener(this);
        icon12.setOnClickListener(this);



        //设置颜色


        if (icon1_id.equals("1") ) {
            icon1.setBackgroundResource(R.mipmap.icon1);
            icon1_id = "0";
        } else if (icon1_id.equals("0")|| icon1_id.equals("")) {
            icon1.setBackgroundResource(R.mipmap.preicon1);
            icon1_id = "1";
        }

        if (icon2_id.equals("1") ) {
            icon2.setBackgroundResource(R.mipmap.icon2);
            icon2_id = "0";
        } else if (icon2_id.equals("0")|| icon2_id.equals("")) {
            icon2.setBackgroundResource(R.mipmap.preicon2);
            icon2_id = "1";
        }

        if (icon3_id.equals("1") ) {
            icon3.setBackgroundResource(R.mipmap.icon3);
            icon3_id="0";

        } else if (icon3_id.equals("0")|| icon3_id.equals("")) {
            icon3.setBackgroundResource(R.mipmap.preicon3);
            icon3_id="1";
        }
        if (icon4_id.equals("1") ) {
            icon4.setBackgroundResource(R.mipmap.icon4);
            icon4_id="0";

        } else if (icon4_id.equals("0")|| icon4_id.equals("")) {
            icon4.setBackgroundResource(R.mipmap.preicon4);
            icon4_id="1";
        }
        if (icon5_id.equals("1") ) {
            icon5.setBackgroundResource(R.mipmap.icon5);
            icon5_id="0";

        } else if (icon5_id.equals("0")|| icon5_id.equals("")) {
            icon5.setBackgroundResource(R.mipmap.preicon5);
            icon5_id="1";
        }
        if (icon6_id.equals("1") ) {
            icon6.setBackgroundResource(R.mipmap.icon6);
            icon6_id="0";

        } else if (icon6_id.equals("0")|| icon6_id.equals("")) {
            icon6.setBackgroundResource(R.mipmap.preicon6);
            icon6_id="1";
        }
        if (icon7_id.equals("1") ) {
            icon7.setBackgroundResource(R.mipmap.icon7);
            icon7_id="0";

        } else if (icon7_id.equals("0")|| icon7_id.equals("")) {
            icon7.setBackgroundResource(R.mipmap.preicon7);
            icon7_id="1";
        }
        if (icon8_id.equals("1") ) {
            icon8.setBackgroundResource(R.mipmap.icon8);
            icon8_id="0";

        } else if (icon8_id.equals("0")|| icon8_id.equals("")) {
            icon8.setBackgroundResource(R.mipmap.preicon8);
            icon8_id="1";
        }
        if (icon9_id.equals("1") ) {
            icon9.setBackgroundResource(R.mipmap.icon9);
            icon9_id="0";

        } else if (icon9_id.equals("0")|| icon9_id.equals("")) {
            icon9.setBackgroundResource(R.mipmap.preicon9);
            icon9_id="1";
        }
        if (icon10_id.equals("1") ) {
            icon10.setBackgroundResource(R.mipmap.icon10);
            icon10_id="0";

        } else if (icon10_id.equals("0")|| icon10_id.equals("")) {
            icon10.setBackgroundResource(R.mipmap.preicon10);
            icon10_id="1";
        }
        if (icon11_id.equals("1") ) {
            icon11.setBackgroundResource(R.mipmap.icon11);
            icon11_id="0";

        } else if (icon11_id.equals("0")|| icon11_id.equals("")) {
            icon11.setBackgroundResource(R.mipmap.preicon11);
            icon11_id="1";
        }
        if (icon12_id.equals("1") ) {
            icon12.setBackgroundResource(R.mipmap.icon12);
            icon12_id="0";

        } else if (icon12_id.equals("0")|| icon12_id.equals("")) {
            icon12.setBackgroundResource(R.mipmap.preicon12);
            icon12_id="1";
        }

    }

    public void btn_home(View view){
        Intent intent = new Intent();
        intent.setClass(SingleActivity.this, FirstActivity.class);
        SingleActivity.this.startActivity(intent);
        SingleActivity.this.finish();
    }

    public void singleback(View view){
        Intent intent=new Intent(SingleActivity.this,FirstActivity.class);
        intent.putExtra("id",1);
        startActivity(intent);


    }


    /*
    *
    * 跳转到参数展示
    *
    * */
    public void Measure_Going(View going ){

                Intent intent=new Intent();
                intent.setClass(SingleActivity.this, ParameterActivity.class);
                startActivity(intent);


    }
    /*
    *
    * 十五参数点击事件
    *
    *
    * */

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.icon1:
                if (icon1_id.equals("0") || icon1_id.equals("")) {
                    icon1.setBackgroundResource(R.mipmap.preicon1);
                    icon1_id = "1";
                } else {
                  icon1.setBackgroundResource(R.mipmap.icon1);
                    icon1_id = "0";
                }
                break;
            case R.id.icon2:
                if (icon2_id.equals("0") || icon2_id.equals("")) {
                    icon2.setBackgroundResource(R.mipmap.preicon2);
                    icon2_id = "1";
                } else if (icon2_id.equals("1")) {
                    icon2.setBackgroundResource(R.mipmap.icon2);
                    icon2_id = "0";
                }
                break;
            case R.id.icon3:
                if (icon3_id.equals("0") || icon3_id.equals("")) {
                    icon3.setBackgroundResource(R.mipmap.preicon3);
                    icon3_id = "1";
                } else if (icon3_id.equals("1")) {
                    icon3.setBackgroundResource(R.mipmap.icon3);
                    icon3_id = "0";
                }
                break;
            case R.id.icon4:
                if (icon4_id.equals("0") || icon4_id.equals("")) {
                    icon4.setBackgroundResource(R.mipmap.preicon4);
                    icon4_id = "1";
                } else if (icon4_id.equals("1")) {
                    icon4.setBackgroundResource(R.mipmap.icon4);
                    icon4_id = "0";
                }
                break;
            case R.id.icon5:
                if (icon5_id.equals("0") || icon5_id.equals("")) {
                    icon5.setBackgroundResource(R.mipmap.preicon5);
                    icon5_id = "1";
                } else if (icon5_id.equals("1")) {
                    icon5.setBackgroundResource(R.mipmap.icon5);
                    icon5_id = "0";
                }
                break;
            case R.id.icon6:
                if (icon6_id.equals("0") || icon6_id.equals("")) {
                    icon6.setBackgroundResource(R.mipmap.preicon6);
                    icon6_id = "1";
                } else if (icon6_id.equals("1")) {
                    icon6.setBackgroundResource(R.mipmap.icon6);
                    icon6_id = "0";
                }
                break;
            case R.id.icon7:
                if (icon7_id.equals("0") || icon7_id.equals("")) {
                    icon7.setBackgroundResource(R.mipmap.preicon7);
                    icon7_id = "1";
                } else if (icon7_id.equals("1")) {
                    icon7.setBackgroundResource(R.mipmap.icon7);
                    icon7_id = "0";
                }
                break;
            case R.id.icon8:
                if (icon8_id.equals("0") || icon8_id.equals("")) {
                    icon8.setBackgroundResource(R.mipmap.preicon8);
                    icon8_id = "1";
                } else if (icon8_id.equals("1")) {
                    icon8.setBackgroundResource(R.mipmap.icon8);
                    icon8_id = "0";
                }
                break;
            case R.id.icon9:
                if (icon9_id.equals("0") || icon9_id.equals("")) {
                    icon9.setBackgroundResource(R.mipmap.preicon9);
                    icon9_id = "1";
                } else if (icon9_id.equals("1")) {
                    icon9.setBackgroundResource(R.mipmap.icon9);
                    icon9_id = "0";
                }
                break;
            case R.id.icon10:
                if (icon10_id.equals("0") || icon10_id.equals("")) {
                    icon10.setBackgroundResource(R.mipmap.preicon10);
                    icon10_id = "1";
                } else if (icon10_id.equals("1")) {
                    icon10.setBackgroundResource(R.mipmap.icon10);
                    icon10_id = "0";
                }
                break;
            case R.id.icon11:
                if (icon11_id.equals("0") || icon11_id.equals("")) {
                    icon11.setBackgroundResource(R.mipmap.preicon11);
                    icon11_id = "1";
                } else if (icon11_id.equals("1")) {
                    icon11.setBackgroundResource(R.mipmap.icon11);
                    icon11_id = "0";
                }
                break;
            case R.id.icon12:
                if (icon12_id.equals("0") || icon12_id.equals("")) {
                    icon12.setBackgroundResource(R.mipmap.preicon12);
                    icon12_id = "1";
                } else if (icon12_id.equals("1")) {
                    icon12.setBackgroundResource(R.mipmap.icon12);
                    icon12_id = "0";
                }
                break;

            default:
                break;
        }

    }
}