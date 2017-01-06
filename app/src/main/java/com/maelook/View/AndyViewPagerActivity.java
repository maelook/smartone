package com.maelook.View;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;

import com.maelook.R;

import java.util.ArrayList;

public class AndyViewPagerActivity extends Activity{

    private ViewPager viewPager;
    private ArrayList<View> pageViews;
    private ViewGroup main, group;
    private ImageView imageView;
    private ImageView[] imageViews;
    public int a = 0;
    public int i = 0;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        LayoutInflater inflater = getLayoutInflater();
        pageViews = new ArrayList<View>();
        pageViews.add(inflater.inflate(R.layout.guide1, null));
        pageViews.add(inflater.inflate(R.layout.guide2, null));
        pageViews.add(inflater.inflate(R.layout.guide3, null));
        pageViews.add(inflater.inflate(R.layout.guide4, null));

        imageViews = new ImageView[pageViews.size()];

        main = (ViewGroup)inflater.inflate(R.layout.activity_andy_view_pager, null);

        group = (ViewGroup)main.findViewById(R.id.viewGroup);


        viewPager = (ViewPager)main.findViewById(R.id.guidePages);


        for (i = 0; i < pageViews.size(); i++) {
            imageView = new ImageView(AndyViewPagerActivity.this);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(30,30));
            imageView.setPadding(20,20, 20, 20);
            imageViews[i] = imageView;


            if (i == 0) {
                //默认选中第一张图片
                imageViews[i].setBackgroundResource(R.drawable.dot);

            } else {
                imageViews[i].setBackgroundResource(R.drawable.undot);
            }
            imageViews[i].setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    int k = i;
                    viewPager.setCurrentItem(where(imageViews,(ImageView)v));
                    Log.d("-------", "-------------" + i);

                }
            });
            group.addView(imageViews[i]);
        }

        setContentView(main);

        viewPager.setAdapter(new GuidePageAdapter());
        viewPager.setOnPageChangeListener(new GuidePageChangeListener());

    }
    public void GOON(View view){
        Intent intent=new Intent();
        intent.setClass(AndyViewPagerActivity.this, NewGuideActivity.class);
        AndyViewPagerActivity.this.startActivity(intent);
        finish();
    }


    public int where(ImageView[] imageviews,ImageView imageview){
        for(int i = 0;i < imageviews.length;i++){
            if(imageviews[i] == imageview){
                return i;
            }
        }
        return -1;
    }

    /** 指引页面Adapter */
    class GuidePageAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return pageViews.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public int getItemPosition(Object object) {
            // TODO Auto-generated method stub
            return super.getItemPosition(object);
        }

        @Override
        public void destroyItem(View arg0, int arg1, Object arg2) {
            // TODO Auto-generated method stub
            ((ViewPager) arg0).removeView(pageViews.get(arg1));
        }

        @Override
        public Object instantiateItem(View arg0, int arg1) {
            // TODO Auto-generated method stub
            ((ViewPager) arg0).addView(pageViews.get(arg1));
            return pageViews.get(arg1) ;
        }

        @Override
        public void restoreState(Parcelable arg0, ClassLoader arg1) {
            // TODO Auto-generated method stub

        }

        @Override
        public Parcelable saveState() {
            return null;
        }

        @Override
        public void startUpdate(View arg0) {

        }

        @Override
        public void finishUpdate(View arg0) {

        }
    }

    /** 指引页面改监听器 */
    class GuidePageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrollStateChanged(int arg0) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onPageSelected(int arg0) {
            for (int i = 0; i < imageViews.length; i++) {
                imageViews[arg0]
                        .setBackgroundResource(R.drawable.dot);
                if (arg0 != i) {
                    imageViews[i]
                            .setBackgroundResource(R.drawable.undot);
                }
            }

        }

    }

}