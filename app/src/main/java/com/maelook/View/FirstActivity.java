package com.maelook.View;
import android.app.Activity;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.maelook.R;
import com.maelook.View.TestData.MainActivity;

import java.util.ArrayList;

public class FirstActivity extends Activity {

    public static FirstActivity instance = null;
    private ViewPager mTabPager;
    private ImageView mTab1,mTab2,mTab3,mTab4;
    private int zero = 0;// 动画图片偏移量
    private int currIndex = 0;// 当前页卡编号
    private int one;//单个水平动画位移
    private int two;
    private int three;
    private LinearLayout mClose;
    private LinearLayout mCloseBtn;
    private View layout;
    private boolean menu_display = false;
    private PopupWindow menuWindow;
    private LayoutInflater inflater;
    /*
   *
   * 加载TextView
   *
   * */
    private TextView measure_Text,data_Text,scene_Text,setting_Text;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        //启动activity时不自动弹出软键盘
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        instance = this;

        /*
        * 获取TextView
        * */
        measure_Text= (TextView) findViewById(R.id.measure_Text);
        data_Text= (TextView) findViewById(R.id.data_Text);
        scene_Text= (TextView) findViewById(R.id.scene_Text);
        setting_Text= (TextView) findViewById(R.id.setting_Text);
        /*
        * 获取ViewPager
        * */
        mTabPager = (ViewPager)findViewById(R.id.tabpager);
        mTabPager.setOnPageChangeListener(new MyOnPageChangeListener());
        /*
        *
        * 获取ImageView底部图片
        * */
        mTab1 = (ImageView) findViewById(R.id.img_measure);
        mTab2 = (ImageView) findViewById(R.id.img_data);
        mTab3 = (ImageView) findViewById(R.id.img_scene);
        mTab4 = (ImageView) findViewById(R.id.img_settings);
        /*
        *
        * 设置监听器点击事件
        * */
        mTab1.setOnClickListener(new MyOnClickListener(0));
        mTab2.setOnClickListener(new MyOnClickListener(1));
        mTab3.setOnClickListener(new MyOnClickListener(2));
        mTab4.setOnClickListener(new MyOnClickListener(3));
        Display currDisplay = getWindowManager().getDefaultDisplay();//获取屏幕当前分辨率
        int displayWidth = currDisplay.getWidth();
        int displayHeight = currDisplay.getHeight();
        one = displayWidth/4; //设置水平动画平移大小
        two = one*2;
        three = one*3;
        //Log.i("info", "获取的屏幕分辨率为" + one + two + three + "X" + displayHeight);
        //InitImageView();//使用动画
        /*
        * 将要分页显示的View装入数组中
        *
        * */
        LayoutInflater mLi = LayoutInflater.from(this);
        View view1 = mLi.inflate(R.layout.activity_measure, null);
        View view2 = mLi.inflate(R.layout.activity_data, null);
        View view3 = mLi.inflate(R.layout.activity_scene, null);
        View view4 = mLi.inflate(R.layout.activity_setting, null);

        //每个页面的view数据
        final ArrayList<View> views = new ArrayList<View>();
        views.add(view1);
        views.add(view2);
        views.add(view3);
        views.add(view4);
        //填充ViewPager的数据适配器
        PagerAdapter mPagerAdapter = new PagerAdapter() {

            @Override
            public boolean isViewFromObject(View arg0, Object arg1) {
                return arg0 == arg1;
            }

            @Override
            public int getCount() {
                return views.size();
            }

            @Override
            public void destroyItem(View container, int position, Object object) {
                ((ViewPager)container).removeView(views.get(position));
            }

            @Override
            public Object instantiateItem(View container, int position) {
                ((ViewPager)container).addView(views.get(position));
                return views.get(position);
            }
        };

        mTabPager.setAdapter(mPagerAdapter);
    }
    /**
     *
     * 头标点击监听
     */
    public class MyOnClickListener implements View.OnClickListener {
        private int index = 0;

        public MyOnClickListener(int i) {
            index = i;
        }
        @Override
        public void onClick(View v) {
            mTabPager.setCurrentItem(index);
        }
    };

    /*
    *
    * *页卡切换监听
    */
    public class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageSelected(int arg0) {
            Animation animation = null;
            switch (arg0) {
                case 0:
                    mTab1.setImageResource(R.mipmap.framenu2);
                    measure_Text.setTextColor(getResources().getColor(R.color.deep_blue));
                    if (currIndex == 1) {
                        animation = new TranslateAnimation(one, 0, 0, 0);
                        mTab2.setImageResource(R.mipmap.framenu3);
                       data_Text.setTextColor(getResources().getColor(R.color.black));

                    } else if (currIndex == 2) {
                        animation = new TranslateAnimation(two, 0, 0, 0);
                        mTab3.setImageResource(R.mipmap.framenu5);
                        scene_Text.setTextColor(getResources().getColor(R.color.black));
                    }
                    else if (currIndex == 3) {
                        animation = new TranslateAnimation(three, 0, 0, 0);
                        mTab4.setImageResource(R.mipmap.framenu7);
                        setting_Text.setTextColor(getResources().getColor(R.color.black));
                    }
                    break;
                case 1:
                    mTab2.setImageResource(R.mipmap.framenu4);
                    data_Text.setTextColor(getResources().getColor(R.color.deep_blue));
                    if (currIndex == 0) {
                        animation = new TranslateAnimation(zero, one, 0, 0);
                        mTab1.setImageResource(R.mipmap.framenu1);
                        measure_Text.setTextColor(getResources().getColor(R.color.black));
                    } else if (currIndex == 2) {
                        animation = new TranslateAnimation(two, one, 0, 0);
                        mTab3.setImageResource(R.mipmap.framenu5);
                        scene_Text.setTextColor(getResources().getColor(R.color.black));
                    }
                    else if (currIndex == 3) {
                        animation = new TranslateAnimation(three, one, 0, 0);
                        mTab4.setImageResource(R.mipmap.framenu7);
                        setting_Text.setTextColor(getResources().getColor(R.color.black));
                    }
                    break;
                case 2:
                    mTab3.setImageResource(R.mipmap.framenu6);
                    scene_Text.setTextColor(getResources().getColor(R.color.deep_blue));
                    if (currIndex == 0) {
                        animation = new TranslateAnimation(zero, two, 0, 0);
                        mTab1.setImageResource(R.mipmap.framenu1);
                        measure_Text.setTextColor(getResources().getColor(R.color.black));
                    } else if (currIndex == 1) {
                        animation = new TranslateAnimation(one, two, 0, 0);
                        mTab2.setImageResource(R.mipmap.framenu3);
                       data_Text.setTextColor(getResources().getColor(R.color.black));
                    }
                    else if (currIndex == 3) {
                        animation = new TranslateAnimation(three, two, 0, 0);
                        mTab4.setImageResource(R.mipmap.framenu7);
                       setting_Text.setTextColor(getResources().getColor(R.color.black));
                    }
                    break;
                case 3:
                    mTab4.setImageResource(R.mipmap.framenu8);
                    setting_Text.setTextColor(getResources().getColor(R.color.deep_blue));
                    if (currIndex == 0) {
                        animation = new TranslateAnimation(zero, three, 0, 0);
                        mTab1.setImageResource(R.mipmap.framenu1);
                        measure_Text.setTextColor(getResources().getColor(R.color.black));
                    } else if (currIndex == 1) {
                        animation = new TranslateAnimation(one, three, 0, 0);
                        mTab2.setImageResource(R.mipmap.framenu3);
                        data_Text.setTextColor(getResources().getColor(R.color.black));
                    }
                    else if (currIndex == 2) {
                        animation = new TranslateAnimation(two, three, 0, 0);
                        mTab3.setImageResource(R.mipmap.framenu5);
                        scene_Text.setTextColor(getResources().getColor(R.color.black));
                    }
                    break;
            }
            currIndex = arg0;
            animation.setFillAfter(true);// True:图片停在动画结束位置
            animation.setDuration(150);
          /*  mTabImg.startAnimation(animation);*/
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        @Override
        public void onPageScrollStateChanged(int arg0) {
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {  //获取 back键

            if(menu_display){         //如果 Menu已经打开 ，先关闭Menu
                menuWindow.dismiss();
                menu_display = false;
            }
            else {
                Intent intent = new Intent();
                intent.setClass(FirstActivity.this,ExitActivity.class);
                startActivity(intent);
            }
        }

        else if(keyCode == KeyEvent.KEYCODE_MENU){   //获取 Menu键
            if(!menu_display){
                //获取LayoutInflater实例
                inflater = (LayoutInflater)this.getSystemService(LAYOUT_INFLATER_SERVICE);
                layout = inflater.inflate(R.layout.main_menu, null);

                menuWindow = new PopupWindow(layout, WindowManager.LayoutParams.FILL_PARENT, WindowManager.LayoutParams.WRAP_CONTENT); //后两个参数是width和height
                //menuWindow.showAsDropDown(layout); //设置弹出效果
                //menuWindow.showAsDropDown(null, 0, layout.getHeight());
                menuWindow.showAtLocation(this.findViewById(R.id.first_page), Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0); //设置layout在PopupWindow中显示的位置

                mClose = (LinearLayout)layout.findViewById(R.id.menu_close);
                mCloseBtn = (LinearLayout)layout.findViewById(R.id.menu_close_btn);



                //比如单击某个MenuItem的时候，他的背景色改变
                //事先准备好一些背景图片或者颜色
                mCloseBtn.setOnClickListener (new View.OnClickListener() {
                    @Override
                    public void onClick(View arg0) {
                        //Toast.makeText(Main.this, "退出", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent();
                        intent.setClass(FirstActivity.this,ExitActivity.class);
                        startActivity(intent);
                        menuWindow.dismiss(); //响应点击事件之后关闭Menu
                    }
                });
                menu_display = true;
            }else{
                //如果当前已经为显示状态，则隐藏起来
                menuWindow.dismiss();
                menu_display = false;
            }

            return false;
        }
        return false;
    }

    /**
     *
     * 单次测量
     * @param view
     */
    public void Single_measure(View  view){
      Intent intent = new Intent (FirstActivity.this,SingleActivity.class);
      startActivity(intent);

  }
/*
*
* 多次测量
*
* */
    public void Many_measure(View  view){
        Intent intent = new Intent (FirstActivity.this,ManyActivity.class);
        startActivity(intent);

    }
/**
 *
 * 连续测量
 *
 * */
    public void Continuous_measure(View  view){
        Intent intent = new Intent (FirstActivity.this,ContinuousActivity.class);
        startActivity(intent);

    }
    /*
    *
    * 闪光测量
    *
     *  */
    public void Flash_measure(View  view){
        Intent intent = new Intent (FirstActivity.this,FlashActivity.class);
        startActivity(intent);

    }
    /*
    * 我的数据库
    *
    * */
    public void MyData(View  view){
        Intent intent = new Intent (FirstActivity.this,MainActivity.class);
        startActivity(intent);

    }
}



