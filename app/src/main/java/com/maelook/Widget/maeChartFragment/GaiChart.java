package com.maelook.Widget.maeChartFragment;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;

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
    private ArrayList<point> data;
    private float totalW;
    private float totalH;

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

        //bot
        Paint dot = new Paint();
        dot.setColor(getResources().getColor(R.color.deep_blue));
        dot.setStyle(Paint.Style.FILL);
        Path curve = new Path();

        double[] x = new double[]{0.4137,0.4151,0.3952,0.3118,0.2859,0.2707,0.3234,0.3695,0.5979,0.4575,0.2755,0.1619,0.4077,0.3784};
        double[] y = new double[]{0.3640,0.4202,0.4826,0.4376,0.3535,0.2912,0.2869,0.3043,0.3185,0.4686,0.4514,0.1820,0.3838,0.4588};
        double[] u_ci = new double[14];
        double[] v_ci = new double[14];
//        for(int i=0;i<x.length;i++){
//            u_ci[i] =
//        }



        for (int i=0;i<v_ci.length;i++){
            if (u_ci[i]>0.32 || u_ci[i]<0.18 || v_ci[i] < 0.48 || v_ci[i] > 0.56){
                continue;
            }
            point p = transTo(new point((float) u_ci[i],(float) v_ci[i]));
            if (i == 0){
                curve.moveTo(this.PaddingLeft+ totalW*p.getX_pixs(),totalH*(1-p.getY_pixs()));
            }
            curve.lineTo(this.PaddingLeft+ totalW*p.getX_pixs(),totalH*(1-p.getY_pixs()));
            canvas.drawCircle(this.PaddingLeft+ totalW*p.getX_pixs(),totalH*(1-p.getY_pixs()),10*getResources().getDisplayMetrics().density,dot);
        }
        //curve
        Paint dotCurve = new Paint();
        dotCurve.setColor(getResources().getColor(R.color.deep_blue));
        dotCurve.setStyle(Paint.Style.STROKE);
        dotCurve.setStrokeWidth(3*getResources().getDisplayMetrics().density);
        curve.close();
        canvas.drawPath(curve,dotCurve);

        if (this.data == null){
            return;
        }
        Paint dotData = new Paint();
        dotData.setColor(getResources().getColor(R.color.indianred));
        dotData.setStyle(Paint.Style.FILL);
        dotData.setStrokeWidth(2*getResources().getDisplayMetrics().density);
        Path curveData = new Path();
        for (int i=0;i<this.data.size();i++){
            point pp = transTo(this.data.get(i));
            if (i==0){
                curveData.moveTo(this.PaddingLeft+ totalW*pp.getX_pixs(),totalH*(1-pp.getY_pixs()));
            }
            canvas.drawCircle(this.PaddingLeft+ totalW*pp.getX_pixs(),totalH*(1-pp.getY_pixs()),4*getResources().getDisplayMetrics().density,dotData);
            curveData.lineTo(this.PaddingLeft+ totalW*pp.getX_pixs(),totalH*(1-pp.getY_pixs()));
        }
        canvas.drawPath(curveData,dotData);


    }

    private point transTo(point p) {
        float x = (float) ((p.getX_pixs() - 0.18 )/0.14);
        float y = (float) ((p.getY_pixs()-0.48) / 0.08);
        return new point(x,y);
    }

    @Override
    protected void scale(Canvas canvas) {
//        totalW = (canvas.getWidth() - borderSize) - this.PaddingLeft;
        totalW = canvas.getWidth() -  this.PaddingLeft;
        totalH = (canvas.getHeight() - this.PaddingBottom) - this.borderSize;
    }

    @Override
    protected void onDraw(Canvas canvas) {

        scale(canvas);
        drawBackground(canvas);
        drawCurve(canvas);

    }

    public void setData(ArrayList<point> data) {
        this.data = data;
        invalidate();
    }
}
