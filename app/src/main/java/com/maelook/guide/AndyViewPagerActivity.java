package com.maelook.guide;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.maelook.R;
import com.maelook.activity.FirstActivity;

import java.util.ArrayList;
import java.util.List;

public class AndyViewPagerActivity extends Activity implements View.OnClickListener, ViewPager.OnPageChangeListener {

    private ViewPager vp;
    private ViewPagerAdapter vpAdapter;
    private List<View> views;
    private ImageView button;

    //引导图片资源
    private static final int[] pics = { R.mipmap.guide1,
            R.mipmap.guide2, R.mipmap.guide3,
            R.mipmap.guide4 };

    //底部小点的图片
    private ImageView[] dots ;

    //记录当前选中位置
    private int currentIndex;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_andy_view_pager);
        button=(ImageView)findViewById(R.id.GoOn);
        views = new ArrayList<View>();
        //定义一个布局并设置参数
        LinearLayout.LayoutParams mParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);

        //初始化引导图片列表
        for(int i=0; i<pics.length; i++) {
            ImageView iv = new ImageView(this);
            iv.setLayoutParams(mParams);
            iv.setImageResource(pics[i]);
            views.add(iv);
        }
        vp = (ViewPager) findViewById(R.id.viewpager);
        //设置数据
        vpAdapter = new ViewPagerAdapter(views);
        vp.setAdapter(vpAdapter);
        //设置监听
        vp.setOnPageChangeListener(this);
        //button = (Button) findViewById(R.id.button);
        //初始化底部小点
        initDots();
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent intent=new Intent();
                intent.setClass(AndyViewPagerActivity.this, FirstActivity.class);
                AndyViewPagerActivity.this.startActivity(intent);
                finish();
            }
        });

    }

    /**
     * 初始化底部小点
     */
    private void initDots() {
        LinearLayout ll = (LinearLayout) findViewById(R.id.ll);

        dots = new ImageView[pics.length];

        //循环取得小点图片
        for (int i = 0; i < pics.length; i++) {
            //得到一个LinearLayout下面的每一个子元素
            dots[i] = (ImageView) ll.getChildAt(i);
            dots[i].setEnabled(true);//默认都设为灰色
            dots[i].setOnClickListener(this);//给每个小点设置监听
            dots[i].setTag(i);//设置位置tag，方便取出与当前位置对应
        }

        currentIndex = 0;  //设置当面默认的位置
        dots[currentIndex].setEnabled(false); //设置为白色，即选中状态
    }

    /**
     * 设置当前页面的位置
     */
    private void setCurView(int position)
    {
        if (position < 0 || position >= pics.length) {
            return;
        }

        vp.setCurrentItem(position);
    }

    /**
     * 设置当前的小点的位置
     */
    private void setCurDot(int positon)
    {
        if (positon < 0 || positon > pics.length - 1 || currentIndex == positon) {
            return;
        }

        dots[positon].setEnabled(false);
        dots[currentIndex].setEnabled(true);

        currentIndex = positon;
    }
    /**
     * 当滑动状态改变时调用
     */
    @Override
    public void onPageScrollStateChanged(int arg0) {
        // TODO Auto-generated method stub

    }

    /**
     * 当当前页面被滑动时调用
     */
    @Override
    public void onPageScrolled(int arg0, float arg1, int arg2) {
        // TODO Auto-generated method stub

    }

    /**
     * 当新的页面被选中时调用
     */
    @Override
    public void onPageSelected(int arg0) {
        setCurDot(arg0);
        if(arg0 == 3){
            button.setVisibility(View.VISIBLE);

        }else{
            button.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View v) {
        int position = (Integer)v.getTag();
        setCurView(position);
        setCurDot(position);
    }

}