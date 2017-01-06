package com.maelook.View.db;
import android.app.Activity;
import android.app.LocalActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.maelook.R;

import java.util.ArrayList;
import java.util.List;

/*
*
* 主界面显示，单次测量，多次测量，连续测量，闪光测量
*
* */
public class MyDatabaseActivity extends Activity {

    Context context=null;
    //单次测量
    private TextView singleText;

    //多次测量
    private TextView manyText;

   //连续测量
    private TextView ContinueText;

    //闪光测量
    private TextView flashText;

    LocalActivityManager manager = null;
    ViewPager pager = null;
    TabHost tabHost = null;
    private int offset = 0;// 动画图片偏移量
    private int currIndex = 0;// 当前页卡编号
    private int bmpW;// 动画图片宽度
    private ImageView cursor;// 动画图片

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_database);

        context = MyDatabaseActivity.this;
        manager = new LocalActivityManager(this , true);
        manager.dispatchCreate(savedInstanceState);

        InitImageView();
        initTextView();
        initPagerViewer();

    }
    /**
     * 初始化标题
     */
    private void initTextView() {
        singleText = (TextView) findViewById(R.id.single_text);
        manyText = (TextView) findViewById(R.id.many_text);
        ContinueText= (TextView) findViewById(R.id.continue_text);
        flashText= (TextView) findViewById(R.id.flash_text);

        singleText.setOnClickListener(new MyOnClickListener(0));
        manyText.setOnClickListener(new MyOnClickListener(1));
        ContinueText.setOnClickListener(new MyOnClickListener(2));
        flashText.setOnClickListener(new MyOnClickListener(3));

    }
    /**
     * 初始化PageViewer
     */
    private void initPagerViewer() {
        pager = (ViewPager) findViewById(R.id.viewPager);
        final ArrayList<View> list = new ArrayList<View>();
        Intent intent1 = new Intent(context, SingDataActivity.class);
        list.add(getView("A", intent1));
        Intent intent2 = new Intent(context, ManyDataActivity.class);
        list.add(getView("B", intent2));
        Intent intent3 = new Intent(context, ContinueDataActivity.class);
        list.add(getView("C", intent3));
        Intent intent4 = new Intent(context, FlashDataActivity.class);
        list.add(getView("D", intent4));

        pager.setAdapter(new MyPagerAdapter(list));
        pager.setCurrentItem(0);
        pager.setOnPageChangeListener(new MyOnPageChangeListener());
    }
    /**
     * 初始化动画
     */
    private void InitImageView() {
        cursor = (ImageView) findViewById(R.id.cursor);
        bmpW = BitmapFactory.decodeResource(getResources(), R.drawable.roller)
                .getWidth();// 获取图片宽度
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screenW = dm.widthPixels;// 获取分辨率宽度
        offset = (screenW / 4 - bmpW) / 2;// 计算偏移量
        Matrix matrix = new Matrix();
        matrix.postTranslate(offset, 0);
        cursor.setImageMatrix(matrix);// 设置动画初始位置
    }

    /**
     * 通过activity获取视图
     * @param id
     * @param intent
     * @return
     */
    private View getView(String id, Intent intent) {
        return manager.startActivity(id, intent).getDecorView();
    }

    /**
     * Pager适配器
     */
    public class MyPagerAdapter extends PagerAdapter {
        List<View> list =  new ArrayList<View>();
        public MyPagerAdapter(ArrayList<View> list) {
            this.list = list;
        }

        @Override
        public void destroyItem(ViewGroup container, int position,
                                Object object) {
            ViewPager pViewPager = ((ViewPager) container);
            pViewPager.removeView(list.get(position));
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public int getCount() {
            return list.size();
        }
        @Override
        public Object instantiateItem(View arg0, int arg1) {
            ViewPager pViewPager = ((ViewPager) arg0);
            pViewPager.addView(list.get(arg1));
            return list.get(arg1);
        }

        @Override
        public void restoreState(Parcelable arg0, ClassLoader arg1) {

        }

        @Override
        public Parcelable saveState() {
            return null;
        }

        @Override
        public void startUpdate(View arg0) {
        }
    }
     /*
     *
     * 页卡切换监
     *
     */


    public class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {

        int one = offset * 2 + bmpW;
        int two = one * 2;
        int three=one*3;

        @Override
        public void onPageSelected(int arg0) {
            Animation animation = null;
            switch (arg0) {
                case 0:
                    if (currIndex == 1) {
                        animation = new TranslateAnimation(one, 0, 0, 0);
                    } else if (currIndex == 2) {
                        animation = new TranslateAnimation(two, 0, 0, 0);
                    }
                    else if (currIndex==3){
                        animation = new TranslateAnimation(three, 0, 0, 0);
                    }
                    break;
                case 1:
                    if (currIndex == 0) {
                        animation = new TranslateAnimation(offset, one, 0, 0);
                    } else if (currIndex == 2) {
                        animation = new TranslateAnimation(two, one, 0, 0);
                    }
                    else if (currIndex==3){
                        animation = new TranslateAnimation(three, one, 0, 0);

                    }
                    break;
                case 2:
                    if (currIndex == 0) {
                        animation = new TranslateAnimation(offset, two, 0, 0);
                    } else if (currIndex == 1) {
                        animation = new TranslateAnimation(one, two, 0, 0);
                    }
                    else if (currIndex==3){
                        animation = new TranslateAnimation(three, two, 0, 0);
                    }
                    break;
                case 3:
                    if (currIndex ==0) {
                        animation = new TranslateAnimation(0, three, 0, 0);
                    } else if (currIndex ==1) {
                        animation = new TranslateAnimation(one, three, 0, 0);
                    }else if (currIndex==2){
                        animation = new TranslateAnimation(two, three, 0, 0);
                    }
                    break;
            }
            currIndex = arg0;
            animation.setFillAfter(true);// True:图片停在动画结束位置
            animation.setDuration(300);
            cursor.startAnimation(animation);
        }

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }
    }
    /**
     * 头标点击监听
     */
    public class MyOnClickListener implements View.OnClickListener {
        private int index = 0;

        public MyOnClickListener(int i) {
            index = i;
        }

        @Override
        public void onClick(View v) {
            pager.setCurrentItem(index);
        }
    };
}
