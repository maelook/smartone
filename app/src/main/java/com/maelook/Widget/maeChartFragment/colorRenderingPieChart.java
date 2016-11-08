package com.maelook.Widget.maeChartFragment;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;

import com.maelook.R;

import java.util.ArrayList;

import static java.lang.Math.PI;

/**
 * Created by Andrew on 2016/11/4.
 */

//TODO 处理缩放问题
public class colorRenderingPieChart extends BaseChart {


    private float defaultLength;
    private float[] data;
    private int textSize = 30;

    public colorRenderingPieChart(Context context) {
        super(context);
    }

    public colorRenderingPieChart(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    public void setData(ArrayList list) {
    }

    public void setCurveData(float[] array){
        this.data = array;
    }

    @Override
    public void drawableShape(Canvas canvas) {

    }

    @Override
    public void drawBackground(Canvas canvas) {

        Paint circlePaint = new Paint();
        circlePaint.setColor(getResources().getColor(R.color.black));
        circlePaint.setStyle(Paint.Style.STROKE);
        circlePaint.setStrokeWidth(3);

        defaultLength = canvas.getWidth()/2/7;
        Path Grid = new Path();
        Path linkPath = new Path();

        //画网格
        for (int i=1;i<=5;i++) {
            Grid.moveTo(canvas.getWidth()/2,canvas.getHeight()/2);
            Grid.lineTo(canvas.getWidth()/2,canvas.getHeight()/2-defaultLength*i);
            linkPath.moveTo(canvas.getWidth()/2,canvas.getHeight()/2-defaultLength*i);

            for (int j = 1 ; j < 15;j++) {
                Grid.moveTo(canvas.getWidth()/2,canvas.getHeight()/2);
                float thetaX = (float) (24*j*(PI/180));
                float thetaY = (float) (24*j*(PI/180));
                float xx = (float) (Math.sin(thetaX)*defaultLength*i);
                float yy = (float) (Math.cos(thetaY)*defaultLength*i);
                float positionX = canvas.getWidth()/2+xx;
                float positionY = canvas.getHeight()/2-yy;

                linkPath.lineTo(positionX,positionY);
                Grid.lineTo( positionX , positionY );
            }
            linkPath.lineTo(canvas.getWidth()/2,canvas.getHeight()/2-defaultLength*i);
        }

        canvas.drawPath(Grid,circlePaint);
        canvas.drawPath(linkPath,circlePaint);

        //边框文字
        Paint textPaint = new Paint();
        textPaint.setColor(getResources().getColor(R.color.black));
        textPaint.setTextSize(textSize);
        for (int i=1;i<=15;i++) {
            canvas.drawText("R"+i, (float) (canvas.getWidth()/2 + Math.sin(24*i*PI/180)*5.5*defaultLength) - textSize/2 , (float) (canvas.getHeight()/2 - Math.cos(24*i*PI/180)*5.5*defaultLength) + textSize/2 ,textPaint);
        }

    }

    @Override
    public void drawCurve(Canvas canvas) {
        this.data = new float[15];
        for (int i=1;i<=15;i++) {
            this.data[i-1]=(5*i);
        }
        this.data[0] = 30;
        this.data[1] = 40;
        this.data[2] = 60;
        this.data[3] = 20;
        this.data[4] = 70;
        this.data[9] = 40;
        this.data[12] = 60;

        Path curvePath = new Path();
        Paint curvePaint = new Paint();
        Paint circlePaint = new Paint();

        curvePaint.setColor(getResources().getColor(R.color.blue));
        curvePaint.setStrokeWidth(8);
        curvePaint.setStyle(Paint.Style.STROKE);

        circlePaint.setStyle(Paint.Style.FILL);
        circlePaint.setColor(getResources().getColor(R.color.blue));

        for (int i=1;i<=15;i++) {
            float percent = this.data[i-1]/100;
            int curveLength = (int) (percent*defaultLength*5);
            float x = (float) (canvas.getWidth()/2 + Math.sin(24*i*PI/180)*curveLength);
            float y = (float) (canvas.getHeight()/2 - Math.cos(24*i*PI/180)*curveLength);
            canvas.drawCircle( x , y ,15 ,circlePaint);
            if (i==1) {
                curvePath.moveTo(x,y);
            }
            curvePath.lineTo(x,y);
        }
        float endPointPersent = this.data[0]/100;
        float endLength = endPointPersent*defaultLength*5;
        float endX = (float) (canvas.getWidth()/2 + Math.sin(24*1*PI/180)*endLength);
        float endY = (float) (canvas.getHeight()/2 - Math.cos(24*1*PI/180)*endLength);
        curvePath.lineTo(endX,endY);
        canvas.drawPath(curvePath,curvePaint);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        drawBackground(canvas);
        drawCurve(canvas);
    }
}