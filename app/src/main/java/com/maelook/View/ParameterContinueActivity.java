package com.maelook.View;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.maelook.Bean.point;
import com.maelook.R;
import com.maelook.Utils.spactrumToParameterUtil;
import com.maelook.Widget.maeChartFragment.CQSBarChart;
import com.maelook.Widget.maeChartFragment.CQSCoordinateChart;
import com.maelook.Widget.maeChartFragment.GaiChart;
import com.maelook.Widget.maeChartFragment.cie1931Chart;
import com.maelook.Widget.maeChartFragment.cie1976Chart;
import com.maelook.Widget.maeChartFragment.colorRenderingBarChart;
import com.maelook.Widget.maeChartFragment.colorRenderingPieChart;
import com.maelook.Widget.maeChartFragment.colorVectorChart;
import com.maelook.Widget.maeChartFragment.spectralCurveChart;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import static com.maelook.app.maelookApp.appDocument;

public class ParameterContinueActivity extends Activity {
    private ViewPager viewPager;
    private ArrayList<View> pageViews;
    private ViewGroup main, group;
    private ImageView imageView;
    private ImageView[] imageViews;
    public int a = 0;
    public int i = 0;
    private Button parmback;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_parameter);

        parmback= (Button) findViewById(R.id.parmback);

        /*
        * 401个数据
        * */
        double[] data = new double[401];
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM+ File.separator+"data1.txt"))));
            int i=0;
            String line ="";
            while((line = reader.readLine()) != null){
                data[i++] = Double.parseDouble(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        spactrumToParameterUtil sp=new spactrumToParameterUtil(data);
        sp.initPrameters();

        LayoutInflater inflater = getLayoutInflater();
        pageViews = new ArrayList<View>();

        /******************************************************************/
        LinearLayout l1 = (LinearLayout) inflater.inflate(R.layout.item01, null);
        spectralCurveChart f1 = (spectralCurveChart) l1.findViewById(R.id.f1);
        f1.setData(sp.getSensorData());

        /******************************************************************/
        LinearLayout l2= (LinearLayout) inflater.inflate(R.layout.item02,null);
        colorRenderingBarChart cb= (colorRenderingBarChart) l2.findViewById(R.id.f2);
        double[] colorBar = new double[16];
        for (int i=0;i<colorBar.length;i++){
            colorBar[i] = 5*i;
        }
        cb.setData(sp.getCRI());


        colorRenderingPieChart crpc= (colorRenderingPieChart) l2.findViewById(R.id.f8);
        double[]  colorRenderingPie = new double[16];
        for (int i=0;i<colorRenderingPie.length;i++){
            colorRenderingPie[i] = 5*i;
        }
       /* crpc.setData(colorRenderingPie);*/
        crpc.setData(sp.getCRI());


        /******************************************************************/
        LinearLayout l3= (LinearLayout) inflater.inflate(R.layout.item03,null);
        CQSCoordinateChart cqdc= (CQSCoordinateChart) l3.findViewById(R.id.f3);
        double[]  cQSCoordinateChart = new double[16];
        ArrayList<point> Coordinate = new ArrayList<>();
        for (int i=0;i<cQSCoordinateChart.length;i++){
            Coordinate.add(new point(-50+5*i,-50+5*i));
        }
        cqdc.setData(Coordinate);

        CQSBarChart cqbc= (CQSBarChart) l3.findViewById(R.id.f4);
        double[]  cQChart = new double[16];
        for (int i=0;i<cQChart.length;i++){
            cQChart[i]=5*i;
        }
        for (double d:sp.getQi()) {
            Log.e("Q13", "  " + d);
        }
        cqbc.setData(sp.getQi());



      /*******************************************************************/

        LinearLayout l6= (LinearLayout) inflater.inflate(R.layout.item06,null);
        cie1931Chart cie1931c= (cie1931Chart) l6.findViewById(R.id.f6);
        /*cie1931c.setPoint(new point((float) 0.5,(float) 0.5));*/
        double[]  cie = new double[16];
        ArrayList<point> c1931 = new ArrayList<>();
       /* for (int i=0;i<cie.length;i++){
            c1931.add(new point((float)0.3,(float)0.3).setDeclare("hello world"));
        }*/
        point pt=new point((float )sp.getXY_x(),(float) sp.getXY_y());
        c1931.add(pt);


        cie1931c.setData(c1931);



        /*******************************************************************/
        LinearLayout l7= (LinearLayout) inflater.inflate(R.layout.item07,null);
        cie1976Chart cie1976c= (cie1976Chart) l7.findViewById(R.id.f7);
      /*  cie1976c.setDataPoint(new point((float) 0.5,(float) 0.5));*/
        double[]  cie76 = new double[16];
        ArrayList<point> cie1976 = new ArrayList<>();
        point p = new point( (float )sp.getUV_u_ci(),(float) sp.getUV_v_ci());
        cie1976.add(p);
        cie1976c.setData(cie1976);

       /*******************************************************************/


       /*******************************************************************/
        LinearLayout l9= (LinearLayout) inflater.inflate(R.layout.item09,null);
        colorVectorChart cvc= (colorVectorChart) l9.findViewById(R.id.f9);



       /*******************************************************************/

        LinearLayout l10= (LinearLayout) inflater.inflate(R.layout.item10,null);
        GaiChart gc= (GaiChart) l10.findViewById(R.id.f10);

        double[]  gai = new double[16];
        ArrayList<point> gaipoint = new ArrayList<>();
        point gaip = new point( (float )sp.getUV_u_ci(),(float) sp.getUV_v_ci());
        gaipoint.add(gaip);
        gc.setData(gaipoint);
        /*******************************************************************/
        /*LinearLayout l11=inflater.inflate(R.layout.item10)*/

        /*
        *
        * 绑定pageViews
        *
        * */
        pageViews.add(l1);
        pageViews.add(l2);
        pageViews.add(l3);
    /*    pageViews.add(l4);*/
        pageViews.add(l6);
        pageViews.add(l7);
        pageViews.add(l9);
        pageViews.add(l10);
       /* pageViews.add(l11);*/


        imageViews = new ImageView[pageViews.size()];

        main = (ViewGroup)inflater.inflate(R.layout.activity_parameter, null);
        group = (ViewGroup)main.findViewById(R.id.viewGroup);
        viewPager = (ViewPager)main.findViewById(R.id.guidePages);
        for (i = 0; i < pageViews.size(); i++) {
            imageView = new ImageView(ParameterContinueActivity.this);
            imageView.setLayoutParams(new LayoutParams(30,30));
            imageView.setPadding(20, 20, 20, 20);
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
    public void share1(View view){
        File f = new File(appDocument+"/2.png");
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
    public void share2(View view){
        File f = new File(appDocument+"/3.png");
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
    public void share3(View view){
        File f = new File(appDocument+"/4.png");
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
            return super.getItemPosition(object);
        }

        @Override
        public void destroyItem(View arg0, int arg1, Object arg2) {
            ((ViewPager) arg0).removeView(pageViews.get(arg1));
        }

        @Override
        public Object instantiateItem(View arg0, int arg1) {
            ((ViewPager) arg0).addView(pageViews.get(arg1));
            return pageViews.get(arg1) ;
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

        @Override
        public void finishUpdate(View arg0) {

        }
    }

    /** 指引页面改监听器 */
    class GuidePageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

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
    public void btn_my_launcher(View view){
        Intent intent=new Intent(ParameterContinueActivity.this,ContinuousActivity.class);
        startActivity(intent);

    }
    public void btn_home(View view){
        Intent intent=new Intent(ParameterContinueActivity.this,FirstActivity.class);
        startActivity(intent);
    }


}