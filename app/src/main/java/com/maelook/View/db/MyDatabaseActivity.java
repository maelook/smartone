package com.maelook.View.db;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.maelook.R;

import java.util.ArrayList;

/*
*
* 主界面显示，单次测量，多次测量，连续测量，闪光测量
*
* */
public class MyDatabaseActivity extends FragmentActivity {

    //单次测量
    private TextView singleText;

    //多次测量
    private TextView manyText;

   //连续测量
    private TextView ContinueText;

    //闪光测量
    private TextView flashText;

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

    public static final String TAG = "MyDatabaseActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_database);
        context = this;

        //初始化TextView
        InitTextView();

        //初始化ImageView
        InitImageView();

        //初始化Fragment
        InitFragment();

        //初始化ViewPager
        InitViewPager();
    }




    @Override
    protected void onResume() {
        /**
         * 设置为竖屏
         */
        if(getRequestedOrientation()!= ActivityInfo.SCREEN_ORIENTATION_PORTRAIT){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }

        super.onResume();
    }
    /*
    *
    * 初始化投标
    *
    */

    private void InitTextView() {

        singleText= (TextView) findViewById(R.id.single_text);
        manyText= (TextView) findViewById(R.id.many_text);
        ContinueText= (TextView) findViewById(R.id.continue_text);
       /* flashText= (TextView) findViewById(R.id.flash_text);*/

        singleText.setOnClickListener(new MyOnClickListener(0));
        manyText.setOnClickListener(new MyOnClickListener(1));
        ContinueText.setOnClickListener(new MyOnClickListener(2));
      /*  flashText.setOnClickListener(new MyOnClickListener(3));*/

    }
    /*
    *
    * 初始化页卡内容区
    *
    * */

    private void InitImageView() {
        cursor = (ImageView) findViewById(R.id.cursor);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        // 获取分辨率宽度
        int screenW = dm.widthPixels;

        bmpW = (screenW/3);

        //设置动画图片宽度
        setBmpW(cursor, bmpW);
        offset = 0;

        //动画图片偏移量赋值
        position_one = (int) (screenW / 3.0);
        position_two = position_one * 2;
    }


    /**
     *
     * 初始化Fragment，并添加到ArrayList中
     *
     */
    private void InitFragment() {
        fragmentArrayList=new ArrayList<Fragment>();
        fragmentArrayList.add(new SingleFragement());
        fragmentArrayList.add(new ManyFragement());
        fragmentArrayList.add(new ContinueFragement());
       /* fragmentArrayList.add(new FlashFragement());*/

        fragmentManager=getSupportFragmentManager();
    }
    /*
    *
    * 初始化动画
    *
    * */

    private void InitViewPager() {
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.setAdapter(new MFragmentPagerAdapter(fragmentManager, fragmentArrayList));

        //让ViewPager缓存2个页面
        viewPager.setOffscreenPageLimit(2);

        //设置默认打开第一页
        viewPager.setCurrentItem(0);

        //将顶部文字恢复默认值
        resetTextViewTextColor();
        singleText.setTextColor(getResources().getColor(R.color.main_top_tab_color_2));

        //设置viewpager页面滑动监听事件
        viewPager.setOnPageChangeListener(new MyOnPageChangeListener());
    }

    /**
     * 设置动画图片宽度
     * @param mWidth
     */
    private void setBmpW(ImageView imageView,int mWidth){
        ViewGroup.LayoutParams para;
        para = imageView.getLayoutParams();
        para.width = mWidth;
        imageView.setLayoutParams(para);
    }

    /**
     * 将顶部文字恢复默认值
     */
    private void resetTextViewTextColor(){

        singleText.setTextColor(getResources().getColor(R.color.main_top_tab_color));
        manyText.setTextColor(getResources().getColor(R.color.main_top_tab_color));
        ContinueText.setTextColor(getResources().getColor(R.color.main_top_tab_color));
      /*  flashText.setTextColor(getResources().getColor(R.color.main_top_tab_color));*/
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
                        singleText.setTextColor(getResources().getColor(R.color.main_top_tab_color_2));
                    }else if(currIndex == 2){
                        //从页卡1跳转转到页卡3
                        animation = new TranslateAnimation(position_two, 0, 0, 0);
                        resetTextViewTextColor();
                        singleText.setTextColor(getResources().getColor(R.color.main_top_tab_color_2));
                    }
                    break;

                //当前为页卡2
                case 1:
                    //从页卡1跳转转到页卡2
                    if (currIndex == 0) {
                        animation = new TranslateAnimation(offset, position_one, 0, 0);
                        resetTextViewTextColor();
                        manyText.setTextColor(getResources().getColor(R.color.main_top_tab_color_2));
                    } else if (currIndex == 2) {
                        //从页卡1跳转转到页卡2
                        animation = new TranslateAnimation(position_two, position_one, 0, 0);
                        resetTextViewTextColor();
                        manyText.setTextColor(getResources().getColor(R.color.main_top_tab_color_2));
                    }
                    break;

                //当前为页卡3
                case 2:
                    //从页卡1跳转转到页卡2
                    if (currIndex == 0) {
                        animation = new TranslateAnimation(offset, position_two, 0, 0);
                        resetTextViewTextColor();
                        ContinueText.setTextColor(getResources().getColor(R.color.main_top_tab_color_2));
                    } else if (currIndex == 1) {
                        //从页卡1跳转转到页卡2
                        animation = new TranslateAnimation(position_one, position_two, 0, 0);
                        resetTextViewTextColor();
                        ContinueText.setTextColor(getResources().getColor(R.color.main_top_tab_color_2));
                    }
                    break;
              /*  case 3:
                    //当前卡页4
                    if (currIndex == 3) {
                        animation = new TranslateAnimation(offset, 0, 0, 0);
                        resetTextViewTextColor();
                        flashText.setTextColor(getResources().getColor(R.color.main_top_tab_color_2));
                    } else if (currIndex == 3) {
                        //从页卡1跳转转到页卡2
                        animation = new TranslateAnimation(0,position_two , 0, 0);
                        resetTextViewTextColor();
                        flashText.setTextColor(getResources().getColor(R.color.main_top_tab_color_2));
                    }
                    break;*/
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
    }
    /**
     * 头标点击监听
     */
    public class MyOnClickListener implements View.OnClickListener{
        private int index = 0 ;
        public MyOnClickListener(int i) {
            index = i;
        }

        @Override
        public void onClick(View v) {
            viewPager.setCurrentItem(index);
        }
    }
}
