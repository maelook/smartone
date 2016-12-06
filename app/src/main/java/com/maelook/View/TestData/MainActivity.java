package com.maelook.View.TestData;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.maelook.R;

import java.util.ArrayList;

public class MainActivity extends FragmentActivity {
    private TextView fra1_txt;
    private TextView fra2_txt;
    private TextView fra3_txt;

    //实现Tab滑动效果
    private ViewPager viewPager;

    //动画图片
    private ImageView cursor;

    //动画图片偏移量
    private int offset = 0;
    private int position_one;
    private int position_two;

    //动画图片宽度
    private int bmpW;

    //当前页卡编号
    private int currIndex = 0;

    //存放Fragment
    private ArrayList<Fragment> fragmentArrayList;

    //管理Fragment
    private FragmentManager fragmentManager;

    public Context context;

    public static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.MyAppCompat);
        setContentView(R.layout.activity_main);
        context = this;
        //初始化方法
        InitTextView();
        InitImageView();
        InitFragment();
        InitViewPager();

    }

    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        return super.onCreateView(name, context, attrs);
    }

    @Override
    protected void onResume() {

/**
         * 设置为竖屏
         */

        if (getRequestedOrientation() != ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }

        super.onResume();
    }


/**
     * 初始化头标
     */


    private void InitTextView() {
        fra1_txt = (TextView) findViewById(R.id.fra1_text);
        fra2_txt = (TextView) findViewById(R.id.fra2_text);
        fra3_txt = (TextView) findViewById(R.id.fra3_text);

        fra1_txt.setOnClickListener(new MyOnClickListener(0));
        fra2_txt.setOnClickListener(new MyOnClickListener(1));
        fra3_txt.setOnClickListener(new MyOnClickListener(2));

    }


/**
     * 初始化动画
     */

    private void InitImageView() {
        cursor = (ImageView) findViewById(R.id.cursor);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        // 获取分辨率宽度
        int screenW = dm.widthPixels;

        bmpW = (screenW / 3);

        //设置动画图片宽度
        setBmpW(cursor, bmpW);
        offset = 0;

        //动画图片偏移量赋值
        position_one = (int) (screenW / 3.0);
        position_two = position_one * 2;

    }



/**
     * 初始化Fragment，并添加到ArrayList中
     */

    private void InitFragment() {
        fragmentArrayList = new ArrayList<Fragment>();
        fragmentArrayList.add(new Fragment1());
        fragmentArrayList.add(new Fragment2());
        fragmentArrayList.add(new Fragment3());

        fragmentManager = getSupportFragmentManager();

    }


/**
     * 初始化页卡内容区
     */

    private void InitViewPager() {
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.setAdapter(new MFragmentPagerAdapter(fragmentManager, fragmentArrayList));

        //让ViewPager缓存2个页面
        viewPager.setOffscreenPageLimit(2);

        //设置默认打开第一页
        viewPager.setCurrentItem(0);

        //将顶部文字恢复默认值
        resetTextViewTextColor();
        fra1_txt.setTextColor(getResources().getColor(R.color.colorAccent));

        //设置viewpager页面滑动监听事件
        viewPager.setOnPageChangeListener(new MyOnPageChangeListener());


    }

    private class MyOnClickListener implements View.OnClickListener {
        private int index = 0;

        public MyOnClickListener(int i) {
            index = i;
        }

        @Override
        public void onClick(View v) {
            viewPager.setCurrentItem(index);
        }
    }
    private class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageSelected(int position) {
            Animation animation = null ;
            switch (position){

                //当前为页卡1
                case 0:
                    //从页卡1跳转转到页卡2
                    if(currIndex == 1){
                        animation = new TranslateAnimation(position_one, 0, 0, 0);
                        resetTextViewTextColor();
                        fra1_txt.setTextColor(getResources().getColor(R.color.colorAccent));
                    }else if(currIndex == 2){
                        //从页卡1跳转转到页卡3
                        animation = new TranslateAnimation(position_two, 0, 0, 0);
                        resetTextViewTextColor();
                        fra1_txt.setTextColor(getResources().getColor(R.color.colorAccent));
                    }
                    break;

                     //当前为页卡2
                case 1:
                    //从页卡1跳转转到页卡2
                    if (currIndex == 0) {
                        animation = new TranslateAnimation(offset, position_one, 0, 0);
                        resetTextViewTextColor();
                        fra2_txt.setTextColor(getResources().getColor(R.color.colorAccent));
                    } else if (currIndex == 2) {
                        //从页卡1跳转转到页卡2
                        animation = new TranslateAnimation(position_two, position_one, 0, 0);
                        resetTextViewTextColor();
                        fra2_txt.setTextColor(getResources().getColor(R.color.colorAccent));
                    }
                    break;

                //当前为页卡3
                case 2:
                    //从页卡1跳转转到页卡2
                    if (currIndex == 0) {
                        animation = new TranslateAnimation(offset, position_two, 0, 0);
                        resetTextViewTextColor();
                        fra3_txt.setTextColor(getResources().getColor(R.color.colorAccent));
                    } else if (currIndex == 1) {
                        //从页卡1跳转转到页卡2
                        animation = new TranslateAnimation(position_one, position_two, 0, 0);
                        resetTextViewTextColor();
                        fra3_txt.setTextColor(getResources().getColor(R.color.colorAccent));
                    }
                    break;
            }
            currIndex = position;

            animation.setFillAfter(true);
            // true:图片停在动画结束位置
            animation.setDuration(300);
            cursor.startAnimation(animation);

        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }


    };
    private void setBmpW(ImageView imageView, int mWidth) {
        ViewGroup.LayoutParams para;
        para = imageView.getLayoutParams();
        para.width = mWidth;
        imageView.setLayoutParams(para);
    }

    private void resetTextViewTextColor() {

        fra1_txt.setTextColor(getResources().getColor(R.color.gray));
        fra2_txt.setTextColor(getResources().getColor(R.color.gray));
        fra3_txt.setTextColor(getResources().getColor(R.color.gray));
    }

}
