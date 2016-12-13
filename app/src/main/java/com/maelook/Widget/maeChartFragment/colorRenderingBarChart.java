package com.maelook.Widget.maeChartFragment;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;

import com.maelook.Bean.point;
import com.maelook.R;

import java.util.ArrayList;

/**
 * Created by Andrew on 2016/11/4.
 */

//TODO 处理数据缩放问题
public class colorRenderingBarChart extends BaseChart {

    private int[] colorArray = {R.color.black,R.color.peru,R.color.peru,R.color.olivedrab,R.color.darkolivegreen,
            R.color.slateblue,R.color.darkturquoise,R.color.mediumpurple,R.color.mediumorchid,R.color.red,
            R.color.yellow,R.color.darkseagreen,R.color.blue,R.color.white,R.color.darkgoldenrod,R.color.pink,R.color.pink};
    private float padding;
    private float margin;
    private float textSize = dpToPx(getResources().getDimension(R.dimen.maelookdimension3_5));      //35px
    private double[] data;
    private float perUnitLengthOfHeight;
    private float perUnitLengthOfWidth;
    private float scale_x;


    public colorRenderingBarChart(Context context) {
        super(context);
    }

    public colorRenderingBarChart(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void drawableShape(Canvas canvas) {
        if (this.data == null){
            return;
        }
        for(int i=0;i<=16;i++){
            Paint colorPanit = new Paint();
            colorPanit.setStyle(Paint.Style.FILL);
            colorPanit.setColor(getResources().getColor(colorArray[i]));
            if(i==0){
                continue;
            }
            canvas.drawRect(this.padding+this.margin, this.padding+this.margin+perUnitLengthOfHeight*(i-1)+textSize/8, (float) (this.padding + this.margin+ (data[i-1])*this.scale_x), this.padding+this.margin+perUnitLengthOfHeight*i-textSize/8, colorPanit);
        }
    }

    @Override
    public void drawBackground(Canvas canvas) {
        this.padding = dpToPx(getResources().getDimension(R.dimen.maelookdimension5));
        this.margin = dpToPx(getResources().getDimension(R.dimen.maelookdimension5));

        //网格图
        Path backgroundGrid = new Path();
        Paint backgroundPaint = new Paint();
//        backgroundPaint.setPathEffect(new DashPathEffect(new float[]{5,2},0));
        backgroundPaint.setStyle(Paint.Style.STROKE);
        backgroundPaint.setStrokeWidth(getResources().getDisplayMetrics().density); //4px
        backgroundGrid.moveTo(this.padding+this.margin,this.padding+this.margin);
        backgroundGrid.lineTo(this.getWidth()-this.padding-this.margin,this.padding+this.margin);

        backgroundGrid.moveTo(this.padding+this.margin,this.padding+this.margin);
        backgroundGrid.lineTo(this.padding+this.margin,this.getHeight()-this.padding-this.margin);

        perUnitLengthOfWidth = (this.getWidth()-this.padding*2-this.margin*2)/5;
        for(int i=0;i<=5;i++){
            backgroundGrid.moveTo(this.padding+this.margin + perUnitLengthOfWidth*i,this.padding+this.margin);
            backgroundGrid.lineTo(this.padding+this.margin + perUnitLengthOfWidth*i,this.getHeight()-this.padding-this.margin);
        }
        backgroundGrid.moveTo(this.padding + this.margin ,this.getHeight()-this.padding-this.margin);
        backgroundGrid.lineTo(this.getWidth() - this.padding - this.margin ,this.getHeight()-this.padding-this.margin);


        canvas.drawPath(backgroundGrid,backgroundPaint);

        //顶部文字
        Paint  textPaint = new Paint();
        textPaint.setTextSize(textSize);
        canvas.drawText("0", this.padding+this.margin/2 + this.perUnitLengthOfWidth*0,this.padding,textPaint);
        canvas.drawText("20",this.padding+this.margin/2 + this.perUnitLengthOfWidth*1,this.padding,textPaint);
        canvas.drawText("40",this.padding+this.margin/2 + this.perUnitLengthOfWidth*2,this.padding,textPaint);
        canvas.drawText("60",this.padding+this.margin/2 + this.perUnitLengthOfWidth*3,this.padding,textPaint);
        canvas.drawText("80",this.padding+this.margin/2 + this.perUnitLengthOfWidth*4,this.padding,textPaint);
        canvas.drawText("100",this.padding+this.margin/2 + this.perUnitLengthOfWidth*5,this.padding,textPaint);

        //左侧文字
        this.perUnitLengthOfHeight = (this.getHeight() - this.padding*2 - this.margin*2 )/16;
        for (int i=0;i<=15;i++) {
            if(i==0){
                canvas.drawText("Ra",this.padding/2, dpToPx(getResources().getDimension(R.dimen.maelookdimension1)) + textSize+this.padding+this.margin+this.perUnitLengthOfHeight*i,textPaint);
                continue;
            }
            canvas.drawText("R"+i,  this.padding/2, dpToPx(getResources().getDimension(R.dimen.maelookdimension1)) + textSize+this.padding+this.margin+this.perUnitLengthOfHeight*i,textPaint);
        }


    }

    @Override
    public void drawCurve(Canvas canvas) {

    }

    @Override
    protected void scale(Canvas canvas) {
        scale_x = (canvas.getWidth() - this.padding*2 -this.margin*2)/100;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        drawBackground(canvas);
        scale(canvas);
        drawCurve(canvas);
        drawableShape(canvas);
    }

    public void setData(double[] data) {
        this.data = data;
        invalidate();
    }
}
