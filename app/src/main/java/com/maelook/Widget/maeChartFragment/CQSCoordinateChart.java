package com.maelook.Widget.maeChartFragment;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

import com.maelook.Bean.PrameterRef_vs;
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
    private float perHeihtLenth;
    private float perWidthLength;
    private float textSize = dpToPx(getResources().getDimension(R.dimen.maelookdimension3));      //30px
    private ArrayList<point> data = new ArrayList<>();
    private float perLength_x;
    private float perLength_y;

    public CQSCoordinateChart(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CQSCoordinateChart(Context context) {
        super(context);
    }

    public void setData(ArrayList<point> list){
        this.data = list;
        invalidate();
    }

    @Override
    public void drawableShape(Canvas canvas) {

    }

    @Override
    public void drawBackground(Canvas canvas) {
        this.padding = (int) getResources().getDimension(R.dimen.maelookdimension9);
        this.margin = (int) getResources().getDimension(R.dimen.maelookdimension9);

        //彩虹背景
        Drawable bg_rainbow = getResources().getDrawable(R.drawable.bg_rainbow);
        bg_rainbow.setBounds(this.padding + this.margin , this.padding + this.margin , canvas.getWidth() - this.padding - this.margin , canvas.getHeight() - this.padding - this.margin);
        bg_rainbow.draw(canvas);

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
        Grid.setStrokeWidth(getResources().getDisplayMetrics().density);

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
        xy.setStrokeWidth(getResources().getDisplayMetrics().density*2);
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
        canvas.drawText(" 80",canvas.getWidth()/2,this.padding + this.margin + perHeihtLenth * 0,textPaint);
        canvas.drawText(" 60",canvas.getWidth()/2,this.padding + this.margin + perHeihtLenth * 1,textPaint);
        canvas.drawText(" 40",canvas.getWidth()/2,this.padding + this.margin + perHeihtLenth * 2,textPaint);
        canvas.drawText(" 20",canvas.getWidth()/2,this.padding + this.margin + perHeihtLenth * 3,textPaint);
        canvas.drawText("  0",canvas.getWidth()/2,this.padding + this.margin + perHeihtLenth * 4,textPaint);
        canvas.drawText("-20",canvas.getWidth()/2,this.padding + this.margin + perHeihtLenth * 5,textPaint);
        canvas.drawText("-40",canvas.getWidth()/2,this.padding + this.margin + perHeihtLenth * 6,textPaint);
        canvas.drawText("-60",canvas.getWidth()/2,this.padding + this.margin + perHeihtLenth * 7,textPaint);
        canvas.drawText("-80",canvas.getWidth()/2,this.padding + this.margin + perHeihtLenth * 8,textPaint);


    }

    @Override
    public void drawCurve(Canvas canvas) {
        double[] ref_a_star = PrameterRef_vs.a_star;
        double[] ref_b_star = PrameterRef_vs.b_star;
        ArrayList<point> ref = new ArrayList<>();
        for (int i=0;i<ref_a_star.length;i++){
            ref.add(new point((float) ref_a_star[i],(float) ref_b_star[i]));
        }
        Paint refPaint = new Paint();
        refPaint.setColor(getResources().getColor(R.color.deep_blue));
        refPaint.setStrokeWidth(3*getResources().getDisplayMetrics().density);
        refPaint.setStyle(Paint.Style.FILL);
        for (point s:ref){
            canvas.drawCircle(canvas.getWidth()/2 + s.getX_pixs()*(this.perLength_x*2) , canvas.getHeight()/2 - s.getY_pixs()*this.perLength_y*2 , 12 ,refPaint);
        }
        Path refPath = new Path();
        byte begin = 0;
        for (point s:ref) {
            if (begin == 0){
                begin=1;
                refPath.moveTo(canvas.getWidth()/2 + s.getX_pixs()*(this.perLength_x*2),canvas.getHeight()/2 - s.getY_pixs()*this.perLength_y*2);
            }
            refPath.lineTo(canvas.getWidth()/2 + s.getX_pixs()*(this.perLength_x*2),canvas.getHeight()/2 - s.getY_pixs()*this.perLength_y*2);
        }
        refPath.close();
        Paint refPaintCurve = new Paint();
        refPaintCurve.setStyle(Paint.Style.STROKE);
        refPaintCurve.setColor(getResources().getColor(R.color.deep_blue));
        refPaintCurve.setStrokeWidth(getResources().getDisplayMetrics().density);
        canvas.drawPath(refPath,refPaintCurve);


        if (this.data == null){
            return;
        }
        Paint dotPaint = new Paint();
        dotPaint.setStyle(Paint.Style.FILL);
        dotPaint.setColor(getResources().getColor(R.color.indianred));

        for (point s:this.data){
            canvas.drawCircle(canvas.getWidth()/2 + s.getX_pixs()*(this.perLength_x*2) , canvas.getHeight()/2 - s.getY_pixs()*this.perLength_y*2 , 12 ,dotPaint);
        }

        Path curve = new Path();
        byte start = 0;
        for (point s:this.data) {
            if (start == 0){
                start=1;
                curve.moveTo(canvas.getWidth()/2 + s.getX_pixs()*(this.perLength_x*2),canvas.getHeight()/2 - s.getY_pixs()*this.perLength_y*2);
            }
            curve.lineTo(canvas.getWidth()/2 + s.getX_pixs()*(this.perLength_x*2),canvas.getHeight()/2 - s.getY_pixs()*this.perLength_y*2);
        }
        curve.close();
        Paint curvePaint = new Paint();
        curvePaint.setStyle(Paint.Style.STROKE);
        curvePaint.setColor(getResources().getColor(R.color.indianred));
        curvePaint.setStrokeWidth(8);
        canvas.drawPath(curve,curvePaint);

    }

    @Override
    protected void scale(Canvas canvas) {
        perLength_x = (canvas.getWidth()/2 - this.padding*2 - this.margin*2)/160;
        perLength_y = (canvas.getHeight()/2 - this.padding*2 - this.margin*2)/160;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        scale(canvas);
        drawBackground(canvas);
        drawCurve(canvas);

    }
}
