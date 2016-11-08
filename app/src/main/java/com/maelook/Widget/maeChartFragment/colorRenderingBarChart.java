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
    private int padding;
    private int margin;
    private int textSize = 30;
    private ArrayList<point> data;
    private int perUnitLengthOfHeight;
    private int perUnitLengthOfWidth;


    public colorRenderingBarChart(Context context) {
        super(context);
    }

    public colorRenderingBarChart(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setData(ArrayList list) {
        this.data = list;
    }

    @Override
    public void drawableShape(Canvas canvas) {
        for(int i=0;i<=16;i++){
            Paint colorPanit = new Paint();
            colorPanit.setStyle(Paint.Style.FILL);
            colorPanit.setColor(getResources().getColor(colorArray[i]));
            if(i==0){
                canvas.drawRect(this.padding+this.margin,   this.padding+this.margin+perUnitLengthOfHeight*i+textSize/8, i*80+400,   this.padding+this.margin+perUnitLengthOfHeight*1-textSize/8, colorPanit);
                continue;
            }
            canvas.drawRect(this.padding+this.margin,   this.padding+this.margin+perUnitLengthOfHeight*(i-1)+textSize/8,    i*80,    this.padding+this.margin+perUnitLengthOfHeight*i-textSize/8, colorPanit);
        }
    }

    @Override
    public void drawBackground(Canvas canvas) {
        this.padding = dpToPx(getResources().getDimension(R.dimen.colorRenderingPadding));
        this.margin = dpToPx(getResources().getDimension(R.dimen.colorRenderingMargin));

        //网格图
        Path backgroundGrid = new Path();
        Paint backgroundPaint = new Paint();
        backgroundPaint.setStyle(Paint.Style.STROKE);
        backgroundPaint.setStrokeWidth(4);
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
                canvas.drawText("Ra",this.padding/2,    textSize+this.padding+this.margin+this.perUnitLengthOfHeight*i,textPaint);
                continue;
            }
            canvas.drawText("R"+i,  this.padding/2,     textSize+this.padding+this.margin+this.perUnitLengthOfHeight*i,textPaint);
        }


    }

    @Override
    public void drawCurve(Canvas canvas) {

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        drawBackground(canvas);
        drawCurve(canvas);
        drawableShape(canvas);
    }

    //dp to px
    private int dpToPx(float dp){
        float scale =getResources().getDisplayMetrics().density;
        return (int) (dp*scale + 0.5f);
    }
}
