package com.maelook.Widget.maeChartFragment;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;

import com.maelook.Bean.point;
import com.maelook.R;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * Created by Andrew on 2016/11/13.
 */

public class GaiChart extends BaseChart {

    private float borderSize = getResources().getDisplayMetrics().density;
    private float percentHeight;
    private float percentWidth;
    private float PaddingLeft = dpToPx(getResources().getDimension(R.dimen.maelookdimension15));
    private float PaddingBottom = dpToPx(getResources().getDimension(R.dimen.maelookdimension10));
    private float numberSize = dpToPx(getResources().getDimension(R.dimen.maelookdimension2));
    private ArrayList<point> data = new ArrayList<>();

    public GaiChart(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GaiChart(Context context) {
        super(context);
    }

    @Override
    public void drawableShape(Canvas canvas) {

    }

    @Override
    public void drawBackground(Canvas canvas) {

        //边框
        Paint border = new Paint();
        border.setStyle(Paint.Style.STROKE);
        border.setColor(getResources().getColor(R.color.black));
        border.setStrokeWidth(borderSize);
        canvas.drawRect(this.PaddingLeft, borderSize , canvas.getWidth() - borderSize , canvas.getHeight() - this.PaddingBottom,border);
        //网格
        percentHeight =  (canvas.getHeight() - this.PaddingBottom )/8;
        percentWidth = (canvas.getWidth() -  this.PaddingLeft )/7;
        Paint GridPaint = new Paint();
        Path GridPath = new Path();
        GridPaint.setColor(getResources().getColor(R.color.deepskyblue));
        GridPaint.setStyle(Paint.Style.STROKE);
        GridPaint.setStrokeWidth(3);
        GridPaint.setPathEffect(new DashPathEffect(new float[]{10,2},1));

        for (int i = 1 ; i < 8; i++){
            //横虚线
            GridPath.moveTo(this.PaddingLeft ,percentHeight * i);
            GridPath.lineTo(canvas.getWidth() - borderSize , percentHeight * i);
            //竖虚线
            if (i <= 6) {
                GridPath.moveTo( this.PaddingLeft + percentWidth * i,borderSize );
                GridPath.lineTo( this.PaddingLeft + percentWidth * i ,canvas.getHeight() - this.PaddingBottom);
            }
        }
        canvas.drawPath(GridPath,GridPaint);

        //文字
        float verticalNumber = 0.56f;
        float horizontalNumber = 0.18f;
        float horizontalNumberStep = 0.02f;
        float verticalNumberStep = 0.01f;
        Paint numberPaint = new Paint();
        numberPaint.setColor(getResources().getColor(R.color.black));
        numberPaint.setTextSize(numberSize);
        numberPaint.setTextAlign(Paint.Align.CENTER);
        BigDecimal one = new BigDecimal("1");

        for(int i = 0; i < 9; i++){
            if (i==0) {
                BigDecimal b = new BigDecimal(""+verticalNumber);
                canvas.drawText(""+ (float) b.divide(one, 2, BigDecimal.ROUND_HALF_UP).doubleValue(),this.PaddingLeft/2, numberSize ,numberPaint);
            } else {
                BigDecimal b = new BigDecimal(""+verticalNumber);
                canvas.drawText(""+ (float) b.divide(one, 2, BigDecimal.ROUND_HALF_UP).doubleValue() ,this.PaddingLeft/2, percentHeight*i,numberPaint);
            }
            verticalNumber = verticalNumber - verticalNumberStep;
            if (i < 8) {
                if (i == 7) {
                    BigDecimal b = new BigDecimal(""+ horizontalNumber);
                    canvas.drawText(""+ (float) b.divide(one, 2, BigDecimal.ROUND_HALF_UP).doubleValue(),this.PaddingLeft + percentWidth * i - numberSize ,canvas.getHeight() - this.PaddingBottom/2 ,numberPaint);
                } else {
                    BigDecimal b = new BigDecimal(""+ horizontalNumber);
                    canvas.drawText(""+ (float) b.divide(one, 2, BigDecimal.ROUND_HALF_UP).doubleValue(),this.PaddingLeft + percentWidth * i ,canvas.getHeight()  - this.PaddingBottom/2 ,numberPaint);
                }
                horizontalNumber = horizontalNumber + horizontalNumberStep;
            }
        }

        //u and v
        canvas.drawText("v'",numberSize,canvas.getHeight()/2,numberPaint);
        canvas.drawText("u'",canvas.getWidth()/2,canvas.getHeight() - numberSize,numberPaint);


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

        drawBackground(canvas);
    }

    public void setData(ArrayList<point> data) {
        this.data = data;
        notify();
    }
}