package com.maelook.Widget.maeChartFragment;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.maelook.bean.point;
import com.maelook.R;

import java.util.ArrayList;

/**
 * Created by Andrew on 2016/10/26.
 */

public abstract class BaseChartView extends View {

    private int padding;
    private int paddingtop;
    private int paddingleft;
    private int paddingright;
    private int paddingbottom;

    public BaseChartView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BaseChartView(Context context) {
        super(context);
    }

    public abstract void setData(ArrayList<point> list);

    public abstract void drawableShape(Canvas canvas);

    public abstract void drawCurve(Canvas canvas);

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        init();

        // 绘制坐标轴和网格
        int padding = 50;
        Paint x = new Paint();
        x.setStyle(Paint.Style.STROKE);
        x.setStrokeWidth(10);
        x.setColor(getResources().getColor(R.color.cyan));
        canvas.drawLine(padding-5,canvas.getHeight()-padding-5,canvas.getWidth()-padding,canvas.getHeight()-padding,x);
        Path xPath = new Path();
        xPath.moveTo(canvas.getWidth()-padding + 5,canvas.getHeight()-padding);
        xPath.moveTo(canvas.getWidth()-padding - 15 ,canvas.getHeight()-padding + 5);
        xPath.moveTo(canvas.getWidth()-padding - 15 ,canvas.getHeight()-padding - 20);
        xPath.moveTo(canvas.getWidth()-padding + 5,canvas.getHeight()-padding);
        xPath.close();
        canvas.drawPath(xPath,x);
        
        Paint y = new Paint();
        y.setStyle(Paint.Style.STROKE);
        y.setStrokeWidth(10);
        y.setColor(getResources().getColor(R.color.cyan));
        canvas.drawLine(padding,canvas.getHeight()-padding,padding,padding,y);
        Path yPath = new Path();
        yPath.moveTo(padding,padding);
        yPath.lineTo(padding - 10 ,padding);
        yPath.lineTo(padding ,padding - 10);
        yPath.lineTo(padding + 10,padding);
        yPath.close();
        canvas.drawPath(yPath,x);
        
        //网格

    }

    //dp to px
    private int dpToPx(float dp){
        float scale =getResources().getDisplayMetrics().density;
        return (int) (dp*scale + 0.5f);
    }

    public void init(){
        this.paddingleft = dpToPx(this.getPaddingLeft());
        this.paddingbottom = dpToPx(this.getPaddingBottom());
        this.paddingright = dpToPx(this.getPaddingRight());
        this.paddingtop = dpToPx(this.getPaddingTop());
        Log.e("padding","end:"+this.getPaddingEnd()+
                "\nstart:"+this.getPaddingStart()+
                "\nbottompaddingoffset"+this.getBottomPaddingOffset()+
                "\nleftpaddingoffset"+this.getLeftPaddingOffset()+
                "\nrightoffset"+this.getRightPaddingOffset()+
                "\ntopoffset"+this.getTopPaddingOffset()+
                "\ntop"+this.paddingtop+
                "\nbottom"+this.paddingbottom+
                "\nleft"+this.paddingleft+
                "\nright"+this.paddingright+
                "\npadding"+this.padding);
    }



}
