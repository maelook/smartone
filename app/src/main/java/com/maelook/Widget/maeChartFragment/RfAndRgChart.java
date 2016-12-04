package com.maelook.Widget.maeChartFragment;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;

import com.maelook.R;

/**
 * Created by Andrew on 2016/11/13.
 */

public class RfAndRgChart extends BaseChart {

    private int borderLeft;
    private int borderRight;
    private int borderTop;
    private float borderBottom;
    private float padding;
    private float borderSize = dpToPx(getResources().getDimension(R.dimen.maelookdimension1));
    private float circleSzie = dpToPx(getResources().getDimension(R.dimen.maelookdimension2));
    private float rSize = dpToPx(getResources().getDimension(R.dimen.maelookdimension10));
    private float bottomTextSize = dpToPx(getResources().getDimension(R.dimen.maelookdimension6));
    private float numberSize = dpToPx(getResources().getDimension(R.dimen.maelookdimension5));

    public RfAndRgChart(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RfAndRgChart(Context context) {
        super(context);
    }

    @Override
    public void drawableShape(Canvas canvas) {

    }

    @Override
    public void drawBackground(Canvas canvas) {

        //border
        Paint border = new Paint();
        border.setStrokeWidth(borderSize);
        border.setStyle(Paint.Style.STROKE);
        border.setColor(getResources().getColor(R.color.black));

//        canvas.drawRect(canvas.getWidth()/3,borderSize,canvas.getWidth() - borderSize - circleSzie,canvas.getHeight()/3*2 - borderSize - circleSzie,border);
        canvas.drawRect(this.borderLeft,this.padding,canvas.getWidth() - this.padding, this.borderBottom,border);
        Paint border1 = new Paint();
        border1.setStrokeWidth(borderSize);
        border1.setStyle(Paint.Style.STROKE);
        border1.setColor(getResources().getColor(R.color.black));
        border1.setPathEffect(new DashPathEffect(new float[]{10,2},1));
        Path DashPath = new Path();
        DashPath.moveTo(this.borderLeft,this.padding);
        DashPath.lineTo(canvas.getWidth() - this.padding,this.borderBottom/2);
        DashPath.lineTo(this.borderLeft,this.borderBottom);
        canvas.drawPath(DashPath,border1);

        Paint circle = new Paint();
        circle.setStyle(Paint.Style.FILL);
        circle.setColor(getResources().getColor(R.color.white));
        circle.setStrokeWidth(8);
        canvas.drawCircle(canvas.getWidth() - this.padding,this.borderBottom/2,circleSzie,circle);
        canvas.drawCircle(canvas.getWidth() - this.padding,this.borderBottom/2,circleSzie,border);

        //character
        Paint rPaint = new Paint();
        rPaint.setColor(getResources().getColor(R.color.black));
        rPaint.setTextSize(rSize);

        Paint bottomTextPaint = new Paint();
        bottomTextPaint.setColor(getResources().getColor(R.color.black));
        bottomTextPaint.setTextSize(bottomTextSize);
        bottomTextPaint.setTextAlign(Paint.Align.CENTER);

        Paint numberPaint = new Paint();
        numberPaint.setColor(getResources().getColor(R.color.black));
        numberPaint.setTextSize(numberSize);

        canvas.drawText("R",0,this.borderBottom/2,rPaint);
        canvas.drawText("g", rSize/4*3,this.borderBottom/2,bottomTextPaint);
        canvas.drawText("R",this.borderLeft + (canvas.getWidth() - this.borderLeft)/2, (float) (canvas.getHeight()/6*5.3),rPaint);
        canvas.drawText("f",this.borderLeft + (canvas.getWidth() - this.borderLeft)/2 + rSize/3*2,(float) (canvas.getHeight()/6*5.35),bottomTextPaint);

        //left numbers
        float percentHeight = this.borderBottom/8;
        for (int i=140,j=0 ; i >= 60; ) {
            if(j==0){
                canvas.drawText(""+i ,rSize , (float) (this.padding+percentHeight*0.1),numberPaint);
                j++;
                i = i -10;
                continue;
            }
            canvas.drawText(""+i ,rSize ,percentHeight*j++,numberPaint);
            i = i - 10;
        }

        //bottom numbers
        float percentWidth = (canvas.getWidth() - this.borderLeft)/6;
        for (int i = 50 ,j=0; i <= 100;) {
            if(i == 100){
                canvas.drawText(""+i, (float) (this.borderLeft + (percentHeight*j++)*0.9),(float) (canvas.getHeight()/6*4.8),numberPaint);
            }
            canvas.drawText(""+i,this.borderLeft - numberSize/2 + percentHeight*j++,(float) (canvas.getHeight()/6*4.8),numberPaint);
            i = i + 10;
        }

        canvas.drawText("CCT = 3500 K, Rf = 100, Rg = 100",canvas.getWidth()/2,canvas.getHeight()-60,bottomTextPaint);
    }

    @Override
    public void drawCurve(Canvas canvas) {

    }

    @Override
    protected void scale(Canvas canvas) {

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        initSize(canvas);

        drawBackground(canvas);
    }

    private void initSize(Canvas canvas) {
        this.padding = dpToPx(getResources().getDimension(R.dimen.maelookdimension2));
        this.borderBottom = (float) (canvas.getHeight()/6*4.5);
        this.borderLeft = canvas.getWidth()/5;
    }

}
