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
    private double[] data;
    private float textSize = dpToPx(getResources().getDimension(R.dimen.maelookdimension3_5));      //35px

    public colorRenderingPieChart(Context context) {
        super(context);
    }

    public colorRenderingPieChart(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setData(double[] array){
        this.data = array;
        invalidate();
    }

    @Override
    public void drawableShape(Canvas canvas) {

    }

    @Override
    public void drawBackground(Canvas canvas) {

        Paint circlePaint = new Paint();
        circlePaint.setColor(getResources().getColor(R.color.black));
        circlePaint.setStyle(Paint.Style.STROKE);
        circlePaint.setStrokeWidth(getResources().getDisplayMetrics().density);     //3px

        defaultLength = canvas.getHeight()>canvas.getWidth()?canvas.getWidth()/12:canvas.getHeight()/12;
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
            canvas.drawText("R"+i, (float) (canvas.getWidth()/2 + Math.sin(24*i*PI/180)*5.5*defaultLength) - textSize , (float) (canvas.getHeight()/2 - Math.cos(24*i*PI/180)*5.5*defaultLength) + textSize/2 ,textPaint);
        }

    }

    @Override
    public void drawCurve(Canvas canvas) {
        if (this.data == null){
            return;
        }
        Path curvePath = new Path();
        Paint curvePaint = new Paint();
        Paint circlePaint = new Paint();

        curvePaint.setColor(getResources().getColor(R.color.blue));
        curvePaint.setStrokeWidth(dpToPx(getResources().getDimension(R.dimen.maelookdimension1)));
        curvePaint.setStyle(Paint.Style.STROKE);

        circlePaint.setStyle(Paint.Style.FILL);
        circlePaint.setColor(getResources().getColor(R.color.blue));

        for (int i=1;i<=15;i++) {
            //获取数据的百分比
            float percent = (float) (this.data[i-1]/100);
            //根据数据的百分比计算对应的长度
            int curveLength = (int) (percent*defaultLength*5);
            float x = (float) (canvas.getWidth()/2 + Math.sin(24*i*PI/180)*curveLength);
            float y = (float) (canvas.getHeight()/2 - Math.cos(24*i*PI/180)*curveLength);
            canvas.drawCircle( x , y ,dpToPx(getResources().getDimension(R.dimen.maelookdimension2)) ,circlePaint);
            if (i==1) {
                curvePath.moveTo(x,y);
            }
            curvePath.lineTo(x,y);
        }
        double endPointPersent = this.data[0]/100;
        double endLength = endPointPersent*defaultLength*5;
        float endX = (float) (canvas.getWidth()/2 + Math.sin(24*1*PI/180)*endLength);
        float endY = (float) (canvas.getHeight()/2 - Math.cos(24*1*PI/180)*endLength);
        curvePath.lineTo(endX,endY);
        canvas.drawPath(curvePath,curvePaint);

    }

    @Override
    protected void scale(Canvas canvas) {

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        drawBackground(canvas);
        drawCurve(canvas);
    }


}
