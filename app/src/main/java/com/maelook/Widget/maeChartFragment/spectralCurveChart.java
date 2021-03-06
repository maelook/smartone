package com.maelook.Widget.maeChartFragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;

import com.maelook.Bean.point;
import com.maelook.R;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

import static com.maelook.app.maelookApp.appDocument;

/**
 * Created by Andrew on 2016/10/26.
 */

/*
* 符合光谱色彩的曲线图
*
* */

//TODO 处理数据缩放问题
public class spectralCurveChart extends BaseChart {

    private double[] data;
    private Path shapePath;
    private float padding;
    private float margin;
    private float scale_x;
    private float scale_y;
    private float scale_sup;
    private ArrayList<double[]> multiData;


    public spectralCurveChart(Context context) {
        super(context);
    }

    public spectralCurveChart(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setData(double[] list) {
        this.data = list;
        this.shapePath = new Path();
        this.padding = this.getPaddingRight();
        invalidate();
        Log.e("test","tests!!!");
    }

    @Override
    public void drawableShape(Canvas canvas) {
        if(shapePath == null){
            return;
        }
        //TODO 原来所有的渐变操作，不能直接由资源文件得到，都需要使用graphics的相关方法操作
        LinearGradient shape = new LinearGradient(50,0,canvas.getWidth()-50,0,new int[]{Color.parseColor("#3e0287"),Color.BLUE,Color.CYAN,Color.GREEN,Color.parseColor("#fce704"),Color.parseColor("#f56c03"),Color.parseColor("#ed0303"),Color.parseColor("#380000")},new float[]{(float) 0.075,(float) 0.2125,(float) 0.28,(float) 0.425,(float) 0.5,(float) 0.55,(float) 0.725, (float) 0.95}, Shader.TileMode.REPEAT);
        canvas.save();
        Shader s = new Shader();
        Paint p = new Paint();
        p.setShader(shape);

        shapePath.lineTo(this.getWidth()-this.padding-this.margin,     this.getHeight()-this.padding-this.margin);
        shapePath.lineTo(this.padding+this.margin,  this.getHeight()-this.padding-this.margin);
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

        //x轴
        backgroundPath.moveTo(  this.padding+this.margin,                      this.getHeight()-this.padding-this.margin);
        backgroundPath.lineTo(  this.getWidth()-this.padding-this.margin,     this.getHeight()-this.padding-this.margin);

        //y轴
        backgroundPath.moveTo(  this.padding+this.margin,    this.getHeight()-this.padding-this.margin);
        backgroundPath.lineTo(  this.padding+this.margin,    this.padding+this.margin);
        canvas.drawPath(backgroundPath,backgroundPaint);

        //框
        canvas.drawRect(this.padding+this.margin,this.padding+this.margin,canvas.getWidth() - this.padding - this.margin,canvas.getHeight() - this.padding - this.margin,backgroundPaint);

        float widthLength = this.getWidth()   -  this.padding*2 - this.margin*2;
        float heightLength = this.getHeight() -  this.padding*2 - this.margin*2;
        float perUnitLengthOfWidth = widthLength / 4;
        float perUnitLengthOfheight = heightLength / 6;

        Paint textPaint = new Paint();
        textPaint.setStyle(Paint.Style.STROKE);
        Typeface typeface = Typeface.create(Typeface.SANS_SERIF,Typeface.BOLD);
        textPaint.setColor(getResources().getColor(R.color.black));
        textPaint.setTextSize(dpToPx(getResources().getDimension(R.dimen.maelookdimension4)));
        //X轴文字
        canvas.drawText("380",          this.padding+this.margin/2+perUnitLengthOfWidth*0,   this.getHeight()-this.padding,textPaint);
        canvas.drawText("480",          this.padding+this.margin/2+perUnitLengthOfWidth*1,   this.getHeight()-this.padding,textPaint);
        canvas.drawText("580",          this.padding+this.margin/2+perUnitLengthOfWidth*2,   this.getHeight()-this.padding,textPaint);
        canvas.drawText("波长（nm）",  this.getWidth()/2-this.padding,   this.getHeight() - 5,textPaint);
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
        canvas.drawText("相对光谱",  0,   this.getHeight()-this.padding-this.margin-perUnitLengthOfheight*6-dpToPx(getResources().getDimension(R.dimen.maelookdimension1)),textPaint);

    }

    @Override
    public void drawCurve(Canvas canvas) {
        if (this.multiData != null){
            Random colorRandom = new Random();
            Integer colorLength = Integer.parseInt("ffffff",16);
            for (int i=0;i<this.multiData.size();i++){
                Path Coordinate = new Path();
                double[] temp = this.multiData.get(i);
                Paint p = new Paint();
                p.setStyle(Paint.Style.STROKE);
                p.setAntiAlias(true);
                p.setColor(Integer.parseInt(getColor(colorLength)));
                p.setStrokeWidth(getResources().getDisplayMetrics().density);
                //移动到原点
                Coordinate.moveTo(this.padding+this.margin,this.getHeight()-this.padding-this.margin);
                for (int j=0; j < temp.length ; j++) {
                    if (j == 0){
                        float x = this.padding +this.margin;
                        float y = this.getHeight()-this.padding-this.margin;
                        Coordinate.moveTo(x,y);
                    }
                    Coordinate.lineTo(this.padding+this.margin+this.scale_x*i, (float) ((1.0-temp[j]) * this.scale_y + this.scale_sup + this.padding + this.margin));
                }
                canvas.drawPath(Coordinate,p);

            }
        }

        if (this.data == null){
            return;
        }
        Path Coordinate = new Path();
        Paint p = new Paint();
        p.setStyle(Paint.Style.STROKE);
        p.setAntiAlias(true);
        p.setStrokeWidth(getResources().getDisplayMetrics().density);
        float x = 0;
        float y = 0;
        //移动到原点
        Coordinate.moveTo(this.padding+this.margin,this.getHeight()-this.padding-this.margin);
        for (int i=0; i < this.data.length ; i++) {
            if (i == 0){
                x = this.padding +this.margin;
                y = this.getHeight()-this.padding-this.margin;
                Coordinate.moveTo(x,y);
            }

            Coordinate.lineTo(this.padding+this.margin+this.scale_x*i, (float) ((1.0-this.data[i]) * this.scale_y + this.scale_sup + this.padding + this.margin));

        }
        canvas.drawPath(Coordinate,p);
        this.shapePath = Coordinate;


    }


    @Override
    protected void scale(Canvas canvas) {

        this.padding = dpToPx(getResources().getDimension(R.dimen.maelookdimension5));
        this.margin = dpToPx(getResources().getDimension(R.dimen.maelookdimension5));

        //数据显示范围
        float Width = canvas.getWidth() - this.padding*2 - this.margin*2;
        float Height = canvas.getHeight() - this.padding*2 - this.margin*2;
        //缩放比例
        scale_x = (float) (Width/401.0);
        scale_y = (float) (Height*5.0/6.0);
        scale_sup = (float) ( Height*1.0/6.0);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        scale(canvas);
        drawBackground(canvas);
        drawCurve(canvas);
        drawableShape(canvas);

    }

    public ArrayList<double[]> getMultiData() {
        return multiData;
    }

    public void setMultiData(ArrayList<double[]> multiData) {
        this.multiData = multiData;
        invalidate();
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
}
