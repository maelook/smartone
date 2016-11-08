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
 * Created by Andrew on 2016/11/5.
 */

//TODO 设置表格适应规则
public class CQSCoordinateChart extends BaseChart {

    private int padding;
    private int margin;
    private int perHeihtLenth;
    private int perWidthLength;
    private int textSize = 30;
    private ArrayList<point> data;

    public CQSCoordinateChart(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CQSCoordinateChart(Context context) {
        super(context);
    }

    public void setData(ArrayList<point> list){
        this.data = list;
    }

    @Override
    public void drawableShape(Canvas canvas) {

    }

    @Override
    public void drawBackground(Canvas canvas) {
        this.padding = (int) getResources().getDimension(R.dimen.CQSBarchartPadding);
        this.margin = (int) getResources().getDimension(R.dimen.CQSBarchartMargin);

        //边框
        Paint border = new Paint();
        border.setStyle(Paint.Style.STROKE);
        border.setColor(getResources().getColor(R.color.gray));
        border.setStrokeWidth(2);
        canvas.drawRect(this.padding + this.margin , this.padding + this.margin , canvas.getWidth() - this.padding - this.margin , canvas.getHeight() - this.padding - this.margin , border);

        //浅色网格
        perHeihtLenth = ( canvas.getHeight() - this.padding*2 -this.margin*2 )/8;
        perWidthLength = ( canvas.getWidth() - this.padding*2 -this.margin*2 )/8;
        Paint Grid = new Paint();
        Grid.setStyle(Paint.Style.STROKE);
        Grid.setColor(getResources().getColor(R.color.gray_cc));
        Grid.setStrokeWidth(4);

        for (int i=1 ; i <= 7  ; i++) {
            //i == 4 时为x，y轴，避免覆盖，不去绘制
            if (i == 4){
                continue;
            }
            //横网格
            canvas.drawLine(this.padding + this.margin, this.padding + this.margin + perHeihtLenth * i,canvas.getWidth() - this.padding - this.margin ,this.padding + this.margin + perHeihtLenth * i ,Grid);
            //竖网格
            canvas.drawLine(this.padding + this.margin + perWidthLength*i,this.padding + this.margin,this.padding + this.margin + perWidthLength*i,canvas.getHeight() - this.padding -this.margin , Grid);
        }

        //x,y轴
        Paint xy = new Paint();
        xy.setColor(getResources().getColor(R.color.black));
        xy.setStrokeWidth(6);
        xy.setStyle(Paint.Style.STROKE);
        canvas.drawLine(this.padding + this.margin , canvas.getHeight()/2 ,  canvas.getWidth() -this.padding - this.margin, canvas.getHeight()/2 , xy);
        canvas.drawLine(canvas.getWidth()/2 , this.padding + this.margin ,canvas.getWidth()/2 , canvas.getHeight() -this.padding - this.margin, xy);

        //x轴文字
        Paint textPaint = new Paint();
        textPaint.setStyle(Paint.Style.STROKE);
        textPaint.setTextSize(textSize);
        textPaint.setColor(getResources().getColor(R.color.black));
        canvas.drawText("-80",this.padding + this.margin + perWidthLength * 0 - textSize/2, canvas.getHeight()/2,textPaint);
        canvas.drawText("-60",this.padding + this.margin + perWidthLength * 1 - textSize/2, canvas.getHeight()/2,textPaint);
        canvas.drawText("-40",this.padding + this.margin + perWidthLength * 2 - textSize/2, canvas.getHeight()/2,textPaint);
        canvas.drawText("-20",this.padding + this.margin + perWidthLength * 3 - textSize/2, canvas.getHeight()/2,textPaint);

        canvas.drawText(" 20",this.padding + this.margin + perWidthLength * 5 - textSize/2, canvas.getHeight()/2,textPaint);
        canvas.drawText(" 40",this.padding + this.margin + perWidthLength * 6 - textSize/2, canvas.getHeight()/2,textPaint);
        canvas.drawText(" 60",this.padding + this.margin + perWidthLength * 7 - textSize/2, canvas.getHeight()/2,textPaint);
        canvas.drawText(" 80",this.padding + this.margin + perWidthLength * 8 - textSize/2, canvas.getHeight()/2,textPaint);

        //y轴文字
        canvas.drawText("-80",canvas.getWidth()/2,this.padding + this.margin + perHeihtLenth * 0,textPaint);
        canvas.drawText("-60",canvas.getWidth()/2,this.padding + this.margin + perHeihtLenth * 1,textPaint);
        canvas.drawText("-40",canvas.getWidth()/2,this.padding + this.margin + perHeihtLenth * 2,textPaint);
        canvas.drawText("-20",canvas.getWidth()/2,this.padding + this.margin + perHeihtLenth * 3,textPaint);
        canvas.drawText("  0",canvas.getWidth()/2,this.padding + this.margin + perHeihtLenth * 4,textPaint);
        canvas.drawText(" 20",canvas.getWidth()/2,this.padding + this.margin + perHeihtLenth * 5,textPaint);
        canvas.drawText(" 40",canvas.getWidth()/2,this.padding + this.margin + perHeihtLenth * 6,textPaint);
        canvas.drawText(" 60",canvas.getWidth()/2,this.padding + this.margin + perHeihtLenth * 7,textPaint);
        canvas.drawText(" 80",canvas.getWidth()/2,this.padding + this.margin + perHeihtLenth * 8,textPaint);

    }

    @Override
    public void drawCurve(Canvas canvas) {

        this.data = new ArrayList<>();
        this.data.add(new point(230,444));
        this.data.add(new point(330,220));
        this.data.add(new point(500,200));
        this.data.add(new point(750,450));
        this.data.add(new point(800,700));
        this.data.add(new point(700,800));
        this.data.add(new point(490,750));
        this.data.add(new point(350,650));


        Paint dotPaint = new Paint();
        dotPaint.setStyle(Paint.Style.FILL);
        dotPaint.setColor(getResources().getColor(R.color.blue));

        for (point s:this.data){
            canvas.drawCircle(s.getX_pixs() , s.getY_pixs() , 12 ,dotPaint);
        }

        Path curve = new Path();
        curve.moveTo(230,444);
        for (point s:this.data) {
            curve.lineTo(s.getX_pixs(),s.getY_pixs());
        }
        curve.close();
        Paint curvePaint = new Paint();
        curvePaint.setStyle(Paint.Style.STROKE);
        curvePaint.setColor(getResources().getColor(R.color.purple));
        curvePaint.setStrokeWidth(8);
        canvas.drawPath(curve,curvePaint);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        drawBackground(canvas);
        drawCurve(canvas);

    }


    //dp to px
    private int dpToPx(float dp){
        float scale =getResources().getDisplayMetrics().density;
        return (int) (dp*scale + 0.5f);
    }
}
