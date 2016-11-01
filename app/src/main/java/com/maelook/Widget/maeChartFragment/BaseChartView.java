package com.maelook.Widget.maeChartFragment;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import com.maelook.Bean.point;
import com.maelook.R;

import java.util.ArrayList;

/**
 * Created by Andrew on 2016/10/26.
 */

public abstract class BaseChartView extends View {

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
}
