package com.maelook.Widget.maeChartFragment;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;

import com.maelook.Bean.point;
import com.maelook.R;

import java.util.Random;

/**
 * Created by Andrew on 2016/11/13.
 */

public class colorVectorChart extends BaseChart {


    private float percentWidth;
    private float percentHeight;
    private float borderSize = getResources().getDisplayMetrics().density*2;
    private float GridSize = getResources().getDisplayMetrics().density;
    private float textSize = dpToPx(getResources().getDimension(R.dimen.maelookdimension5));

    public colorVectorChart(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public colorVectorChart(Context context) {
        super(context);
    }

    @Override
    public void drawableShape(Canvas canvas) {

    }

    @Override
    public void drawBackground(Canvas canvas) {
        //边框
        Paint RectPaint = new Paint();
        RectPaint.setStyle(Paint.Style.STROKE);
        RectPaint.setColor(getResources().getColor(R.color.black));
        RectPaint.setStrokeWidth(borderSize);

        //彩虹背景
        Drawable drawable = getResources().getDrawable(R.drawable.bg_rainbow);
        drawable.setBounds(0,0,canvas.getWidth(),canvas.getWidth());
        drawable.draw(canvas);

        //网格
        percentWidth = canvas.getWidth()/6;
        percentHeight = canvas.getWidth()/6;
        Paint GridPaint = new Paint();
        GridPaint.setColor(getResources().getColor(R.color.white));
        GridPaint.setStrokeWidth(GridSize);
        for (int i=1;i<=5;i++) {
            //横线
            canvas.drawLine(0,percentHeight*i,canvas.getWidth(),percentHeight*i,GridPaint);
            //竖线
            canvas.drawLine(percentWidth*i,0,percentWidth*i,canvas.getWidth(),GridPaint);
        }
        //后画边框避免被覆盖
        canvas.drawRect(borderSize/2,borderSize/2,canvas.getWidth() - borderSize/2,canvas.getWidth() - borderSize/2 ,RectPaint);
        //标准中心圆
        canvas.drawCircle(canvas.getWidth()/2,canvas.getWidth()/2,canvas.getWidth()/3,RectPaint);

        //中间的文字
        float leaveHeight = canvas.getHeight() - percentHeight*6;
        float perLeaveHeight = leaveHeight/5;
        Paint textPaint = new Paint();
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setColor(getResources().getColor(R.color.black));
        textPaint.setTextSize(textSize);
        canvas.drawText("Rf=75 | Rg = 100 | CCT = 3500 K | Duv = 0.000",canvas.getWidth()/2, (float) (percentHeight*6 + perLeaveHeight*0.5),textPaint);

        //开始底部的网格图
        //网格边框
        Paint bottomBorder = new Paint();
        bottomBorder.setStyle(Paint.Style.STROKE);
        bottomBorder.setColor(getResources().getColor(R.color.white));
        bottomBorder.setStrokeWidth(GridSize);
        canvas.drawRect(GridSize/2,percentHeight*6 + perLeaveHeight*1,canvas.getWidth() - GridSize/2,canvas.getHeight() - GridSize/2 ,bottomBorder);

        //画条形图
        float[] data = new float[80];
        int datacount =0;
        Random random = new Random();
        Log.e("dp",""+getResources().getDisplayMetrics().density);
        while(datacount < data.length){
            data[datacount++] = random.nextFloat()*dpToPx(180);
        }
        //这里不能取消data.length的强制转换，否则丢失的小数部分累加起来就会丢失相当一部分区域
        float barWidth = canvas.getWidth()/(float)data.length;
        Integer colorLength = Integer.parseInt("ffffff",16);
        for (int i=0;i<data.length;i++) {
            Paint temp = new Paint();
            temp.setStyle(Paint.Style.FILL);
            String colorString = "#ff"+getColor((int) (colorLength*random.nextFloat()));
            temp.setColor(Color.parseColor(colorString));
            canvas.drawRect(barWidth*i,canvas.getHeight() - data[i],barWidth*(i+1),canvas.getHeight(),temp);
        }


    }

    private String getColor(Integer i) {
        String res = Integer.toHexString(i);
        if(res.length() < 6){
            int need = 6 - res.length();
            while (need > 0){
                res = "0"+res;
                need--;
            }
        }
        return res;
    }

    @Override
    public void drawCurve(Canvas canvas) {
        //TODO 箭头和曲线适应

    }

    @Override
    protected void scale(Canvas canvas) {

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        drawBackground(canvas);
        float scale =getResources().getDisplayMetrics().density;
        int aa = (int) ((int) getResources().getDimension(R.dimen.maelookdimension1)*scale + 0.5f);

        Log.e("aa",""+aa);
        Log.e("scale",""+scale);
        Log.e("width",""+canvas.getWidth());
        Log.e("height",""+canvas.getHeight());

//        drawArrow(canvas,new point(400,400),new point(650,650));
//        drawArrow(canvas,new point(400,400),new point(250,250));
//        drawArrow(canvas,new point(400,400),new point(250,450));
//        drawArrow(canvas,new point(400,400),new point(450,250));
    }

    public void drawArrow(Canvas canvas , point x ,point y){
        Path arrowPath = new Path();
        Paint arrowPaint = new Paint();
        Random random = new Random();
        Integer colorLength = Integer.parseInt("ffffff",16);
        String colorString = "#ff"+getColor((int) (colorLength*random.nextFloat()));
        arrowPaint.setColor(Color.parseColor(colorString));
        arrowPaint.setStyle(Paint.Style.FILL);
        //设置箭头路径规则
        float diff_x = y.getX_pixs() - x.getX_pixs();
        float diff_y = y.getY_pixs() - x.getY_pixs();
        Log.e("arrow","diff_x:"+diff_x+"  diff_y:"+diff_y);
        point temp = new point(x.getY_pixs() + diff_y/4*3,x.getX_pixs() + diff_x/4*3);
        Log.e("arrow","temp_x:"+temp.getX_pixs()+"  temp_y:"+temp.getY_pixs());
        point p1 = new point(x.getX_pixs() - diff_x/8, x.getX_pixs() + diff_x/8 );
        point p2 = new point(temp.getX_pixs() - diff_x/8, temp.getY_pixs() + diff_x/8);
        point p3 = new point(temp.getX_pixs() - diff_x/8*3, temp.getY_pixs() + diff_x/8*3);
        point p4 = y;
        point p5 = new point(temp.getX_pixs() + diff_x/8*3 , temp.getX_pixs() - diff_x/8*3);
        point p6 = new point(temp.getX_pixs() + diff_x/8 , temp.getX_pixs() - diff_x/8);
        point p7 = new point(x.getX_pixs() + diff_x/8 , x.getY_pixs() - diff_x/8);
        Log.e("arrow","p_x:"+p1.getX_pixs()+"  p_y:"+p1.getY_pixs());
        Log.e("arrow","p_x:"+p2.getX_pixs()+"  p_y:"+p2.getY_pixs());
        Log.e("arrow","p_x:"+p3.getX_pixs()+"  p_y:"+p3.getY_pixs());
        Log.e("arrow","p_x:"+p4.getX_pixs()+"  p_y:"+p4.getY_pixs());
        Log.e("arrow","p_x:"+p5.getX_pixs()+"  p_y:"+p5.getY_pixs());
        Log.e("arrow","p_x:"+p6.getX_pixs()+"  p_y:"+p6.getY_pixs());
        Log.e("arrow","p_x:"+p7.getX_pixs()+"  p_y:"+p7.getY_pixs());
        arrowPath.moveTo(p1.getX_pixs(),p1.getY_pixs());
        arrowPath.lineTo(p2.getX_pixs(),p2.getY_pixs());
        arrowPath.lineTo(p3.getX_pixs(),p3.getY_pixs());
        arrowPath.lineTo(p4.getX_pixs(),p4.getY_pixs());
        arrowPath.lineTo(p5.getX_pixs(),p5.getY_pixs());
        arrowPath.lineTo(p6.getX_pixs(),p6.getY_pixs());
        arrowPath.lineTo(p7.getX_pixs(),p7.getY_pixs());
        arrowPath.close();
        canvas.drawPath(arrowPath,arrowPaint);

    }

    public void drawArrow1(Canvas canvas , point x ,point y){
        Path arrowPath = new Path();
        Paint arrowPaint = new Paint();
        Random random = new Random();
        Integer colorLength = Integer.parseInt("ffffff",16);
        String colorString = "#ff"+getColor((int) (colorLength*random.nextFloat()));
        arrowPaint.setColor(Color.parseColor(colorString));
        arrowPaint.setStyle(Paint.Style.FILL);
        arrowPaint.setTextSize(30);
        //设置箭头路径规则
        //两点之间的连线的k
        float k = -(y.getY_pixs() - x.getY_pixs()) / (y.getX_pixs() - x.getX_pixs());
        //斜率的相反数和各自的c1,c2
        float c = x.getY_pixs() - k*x.getX_pixs();
        if(y.getY_pixs() == k*y.getX_pixs() + c){
            Log.e("K","K is right!");
        }

        float diff_x = y.getX_pixs() - x.getX_pixs();
        float diff_y = y.getY_pixs() - x.getY_pixs();
        float c1 = x.getY_pixs() - (-k)*x.getX_pixs();
        float c2 = y.getY_pixs() - (-k)*y.getX_pixs();
        point p1 = new point(x.getX_pixs() - diff_x/8 , -k*x.getX_pixs() + diff_x/8 + c1);
        point p2 = new point(y.getX_pixs() - diff_x/8 , -k*y.getX_pixs() + diff_x/8 + c2);
        point p3 = new point(y.getX_pixs() - diff_x/8*3 , -k*y.getX_pixs() + diff_x/8*3 + c2);
        point p4 = y;
        point p5 = new point(y.getX_pixs() + diff_x/8*3 , -k*y.getX_pixs() + diff_x/8*3 + c2);
        point p6 = new point(y.getX_pixs() + diff_x/8 , -k*y.getX_pixs() + diff_x/8 + c2);
        point p7 = new point(x.getX_pixs() + diff_x/8 , -k*x.getX_pixs() + diff_x/8 + c1);

        canvas.drawCircle(p1.getX_pixs(),p1.getY_pixs(),5,arrowPaint);
        canvas.drawText("p1",p1.getX_pixs(),p1.getY_pixs(),arrowPaint);
        canvas.drawCircle(p2.getX_pixs(),p2.getY_pixs(),5,arrowPaint);
        canvas.drawText("p2",p2.getX_pixs(),p2.getY_pixs(),arrowPaint);
        canvas.drawCircle(p3.getX_pixs(),p3.getY_pixs(),5,arrowPaint);
        canvas.drawText("p3",p3.getX_pixs(),p3.getY_pixs(),arrowPaint);
        canvas.drawCircle(p4.getX_pixs(),p4.getY_pixs(),5,arrowPaint);
        canvas.drawText("p4",p4.getX_pixs(),p4.getY_pixs(),arrowPaint);
        canvas.drawCircle(p5.getX_pixs(),p5.getY_pixs(),5,arrowPaint);
        canvas.drawText("p5",p5.getX_pixs(),p5.getY_pixs(),arrowPaint);
        canvas.drawCircle(p6.getX_pixs(),p6.getY_pixs(),5,arrowPaint);
        canvas.drawText("p6",p6.getX_pixs(),p6.getY_pixs(),arrowPaint);
        canvas.drawCircle(p7.getX_pixs(),p7.getY_pixs(),5,arrowPaint);
        canvas.drawText("p7",p7.getX_pixs(),p7.getY_pixs(),arrowPaint);
    }


}
