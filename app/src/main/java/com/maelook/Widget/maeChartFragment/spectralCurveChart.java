package com.maelook.Widget.maeChartFragment;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.Log;

import com.maelook.Bean.point;
import com.maelook.R;

import java.util.ArrayList;

/**
 * Created by Andrew on 2016/10/26.
 */

/*
* 符合光谱色彩的曲线图
*
* */

//TODO 处理数据缩放问题
public class spectralCurveChart extends BaseChart {

    private ArrayList data;
    private Path shapePath;
    private int padding;
    private int margin;

    public spectralCurveChart(Context context) {
        super(context);
    }

    public spectralCurveChart(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setData(ArrayList list) {
        this.data = list;
        this.shapePath = new Path();
        this.padding = this.getPaddingRight();
    }

    @Override
    public void drawableShape(Canvas canvas) {
        //TODO 原来所有的渐变操作，不能直接由资源文件得到，都需要使用graphics的相关方法操作
        LinearGradient shape = new LinearGradient(50,0,canvas.getWidth()-50,0,new int[]{Color.parseColor("#7603fa"),Color.CYAN,Color.BLUE,Color.GREEN,Color.parseColor("#fceb00"),Color.parseColor("#f7a901"),Color.RED},null, Shader.TileMode.REPEAT);
        canvas.save();
        Shader s = new Shader();
        Paint p = new Paint();
        p.setShader(shape);

        shapePath.lineTo(this.getWidth()-this.padding-this.margin,     this.getHeight()-this.padding-this.margin);
        shapePath.lineTo(this.padding+this.margin,                      this.getHeight()-this.padding-this.margin);
        shapePath.close();

        canvas.drawPath(shapePath,p);
    }

    @Override
    public void drawBackground(Canvas canvas) {
        Path backgroundPath = new Path();
        Paint backgroundPaint = new Paint();
        backgroundPaint.setStrokeWidth(4);
        backgroundPaint.setColor(getResources().getColor(R.color.black));
        backgroundPaint.setStyle(Paint.Style.STROKE);

        this.padding = dpToPx(getResources().getDimension(R.dimen.spcatralpadding));
        this.margin = dpToPx(getResources().getDimension(R.dimen.spcatralmargin));
        Log.e("length",""+this.margin);
        //x轴
        backgroundPath.moveTo(  this.padding+this.margin,                      this.getHeight()-this.padding-this.margin);
        backgroundPath.lineTo(  this.getWidth()-this.padding-this.margin,     this.getHeight()-this.padding-this.margin);

        //y轴
        backgroundPath.moveTo(  this.padding+this.margin,    this.getHeight()-this.padding-this.margin);
        backgroundPath.lineTo(  this.padding+this.margin,    this.padding+this.margin);
        canvas.drawPath(backgroundPath,backgroundPaint);

        int widthLength = this.getWidth()   -  this.padding*2 - this.margin*2;
        int heightLength = this.getHeight() -  this.padding*2 - this.margin*2;
        int perUnitLengthOfWidth = widthLength / 4;
        int perUnitLengthOfheight = widthLength / 6;

        Paint textPaint = new Paint();
        textPaint.setStyle(Paint.Style.STROKE);
        textPaint.setColor(getResources().getColor(R.color.black));
        textPaint.setTextSize(30);
        //X轴文字
        canvas.drawText("380",          this.padding+this.margin/2+perUnitLengthOfWidth*0,   this.getHeight()-this.padding,textPaint);
        canvas.drawText("480",          this.padding+this.margin/2+perUnitLengthOfWidth*1,   this.getHeight()-this.padding,textPaint);
        canvas.drawText("580",          this.padding+this.margin/2+perUnitLengthOfWidth*2,   this.getHeight()-this.padding,textPaint);
        canvas.drawText("波长（nm）",  this.getWidth()/2-this.padding,   this.getHeight(),textPaint);
        canvas.drawText("680",          this.padding+this.margin/2+perUnitLengthOfWidth*3,   this.getHeight()-this.padding,textPaint);
        canvas.drawText("780",          this.padding+this.margin/2+perUnitLengthOfWidth*4,   this.getHeight()-this.padding,textPaint);

        //y轴文字
        canvas.drawText("0.0",  this.padding-10,   this.getHeight()-this.padding-this.margin/2-perUnitLengthOfheight*0,textPaint);
        canvas.drawText("0.2",  this.padding-10,   this.getHeight()-this.padding-this.margin/2-perUnitLengthOfheight*1,textPaint);
        canvas.drawText("0.4",  this.padding-10,   this.getHeight()-this.padding-this.margin/2-perUnitLengthOfheight*2,textPaint);
        canvas.drawText("0.6",  this.padding-10,   this.getHeight()-this.padding-this.margin/2-perUnitLengthOfheight*3,textPaint);
        canvas.drawText("0.8",  this.padding-10,   this.getHeight()-this.padding-this.margin/2-perUnitLengthOfheight*4,textPaint);
        canvas.drawText("1.0",  this.padding-10,   this.getHeight()-this.padding-this.margin/2-perUnitLengthOfheight*5,textPaint);
        canvas.drawText("1.2",  this.padding-10,   this.getHeight()-this.padding-this.margin/2-perUnitLengthOfheight*6,textPaint);
        canvas.drawText("相对光谱",  0,   this.getHeight()-this.padding-this.margin-perUnitLengthOfheight*6-10,textPaint);

    }

    @Override
    public void drawCurve(Canvas canvas) {
        point point = new point();
        Path Coordinate = new Path();
        this.data = new ArrayList();
        this.data.add(new point(200,300));
        this.data.add(new point(300,400));
        this.data.add(new point(400,500));
        this.data.add(new point(500,200));

        Paint p = new Paint();
        p.setStyle(Paint.Style.STROKE);
        p.setAntiAlias(true);
        p.setStrokeWidth(3);
        float x = 0;
        float y = 0;
        //移动到原点
        Coordinate.moveTo(this.padding+this.margin,this.getHeight()-this.padding-this.margin);
        for (int i=0; i < this.data.size() ; i++) {
            if (i == 0){
                x = this.padding +this.margin;
                y = this.getHeight()-this.padding-this.margin;
                Coordinate.moveTo(x,y);
            }

            point = (com.maelook.Bean.point) this.data.get(i);
            Coordinate.lineTo(point.getX_pixs(),point.getY_pixs());
        }
        canvas.drawPath(Coordinate,p);
        this.shapePath = Coordinate;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        drawBackground(canvas);
        drawCurve(canvas);
        drawableShape(canvas);

    }

    public void Refresh(ArrayList list){

        invalidate();
    }


    //dp to px
    private int dpToPx(float dp){
        float scale =getResources().getDisplayMetrics().density;
        return (int) (dp*scale + 0.5f);
    }
}
