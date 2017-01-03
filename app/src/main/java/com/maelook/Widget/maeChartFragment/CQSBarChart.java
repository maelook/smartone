package com.maelook.Widget.maeChartFragment;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Typeface;
import android.util.AttributeSet;

import com.maelook.R;

import java.util.Random;

/**
 * Created by Andrew on 2016/11/5.
 */

public class CQSBarChart extends BaseChart{

    private float padding;
    private float margin;
    private float perHeihtLength;
    private float perWidthLength;
    private int[] colorArray = {R.color.r1,R.color.r2,R.color.r3,R.color.r4,R.color.r5,R.color.r6,R.color.r7,R.color.r8,R.color.r9,R.color.r10,R.color.r11,R.color.r12,R.color.r13,R.color.r14,R.color.r15,R.color.r16,};
    private float textSize = dpToPx(getResources().getDimension(R.dimen.maelookdimension3));   //30px
    private double[] data;

    public CQSBarChart(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CQSBarChart(Context context) {
        super(context);
    }

    public void setData(double[] data){
        this.data = data;
        invalidate();
    }

    @Override
    public void drawableShape(Canvas canvas) {

    }

    @Override
    public void drawBackground(Canvas canvas) {
        this.padding = dpToPx(getResources().getDimension(R.dimen.maelookdimension5));
        this.margin = dpToPx(getResources().getDimension(R.dimen.maelookdimension5));

        //顶部文字
        Paint titlePaint = new Paint();
        titlePaint.setTextSize(dpToPx(getResources().getDimension(R.dimen.maelookdimension5)));
        canvas.drawText("Individual CQS", canvas.getWidth()/2 - 50*3 , this.padding ,titlePaint);

        // 边框
        Paint borderPaint = new Paint();
        borderPaint.setColor(getResources().getColor(R.color.black));
        borderPaint.setStrokeWidth(getResources().getDisplayMetrics().density);     //3px
        borderPaint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(this.padding+this.margin ,this.padding+this.margin , canvas.getWidth() - this.padding - this.margin,canvas.getHeight() - this.padding - this.margin,borderPaint);

        //网格
        perHeihtLength = (float) ((canvas.getHeight() - this.padding*2 - this.margin*2 )/5.0);
        Path coordinatePath = new Path();

        for (int i=1 ; i <=5 ;i++) {
            coordinatePath.moveTo(this.padding + this.margin , this.padding + this.margin + perHeihtLength *i);
            coordinatePath.lineTo(canvas.getWidth() - this.padding - this.margin,this.padding + this.margin + perHeihtLength *i);
        }
        canvas.drawPath(coordinatePath,borderPaint);

        //底部文字
        perWidthLength = (float) ((canvas.getWidth() - this.padding*2 -this.margin*2)/34.0);

        Paint textPaint = new Paint();
        textPaint.setStyle(Paint.Style.STROKE);
        Typeface typeface = Typeface.create(Typeface.SANS_SERIF,Typeface.BOLD);
        textPaint.setTypeface(typeface);
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setTextSize(textSize);

        canvas.drawText(" Q1", (float) (this.padding + perWidthLength * 3), canvas.getHeight() - this.padding ,textPaint );
        canvas.drawText(" Q2", (float) (this.padding + perWidthLength * 5), canvas.getHeight() - this.padding ,textPaint );
        canvas.drawText(" Q3", (float) (this.padding + perWidthLength * 7), canvas.getHeight() - this.padding ,textPaint );
        canvas.drawText(" Q4", (float) (this.padding + perWidthLength * 9), canvas.getHeight() - this.padding ,textPaint );
        canvas.drawText(" Q5", (float) (this.padding + perWidthLength * 11), canvas.getHeight() - this.padding ,textPaint );
        canvas.drawText(" Q6", (float) (this.padding + perWidthLength * 13), canvas.getHeight() - this.padding ,textPaint );
        canvas.drawText(" Q7", (float) (this.padding + perWidthLength * 15), canvas.getHeight() - this.padding ,textPaint );
        canvas.drawText(" Q8", (float) (this.padding + perWidthLength * 17), canvas.getHeight() - this.padding ,textPaint );
        canvas.drawText(" Q9", (float) (this.padding + perWidthLength * 19), canvas.getHeight() - this.padding ,textPaint );
        canvas.drawText("Q10", (float) (this.padding + perWidthLength * 21), canvas.getHeight() - this.padding ,textPaint );
        canvas.drawText("Q11", (float) (this.padding + perWidthLength * 23), canvas.getHeight() - this.padding ,textPaint );
        canvas.drawText("Q12", (float) (this.padding + perWidthLength * 25), canvas.getHeight() - this.padding ,textPaint );
        canvas.drawText("Q13", (float) (this.padding + perWidthLength * 27), canvas.getHeight() - this.padding ,textPaint );
        canvas.drawText("Q14", (float) (this.padding + perWidthLength * 29), canvas.getHeight() - this.padding ,textPaint );
        canvas.drawText("Q15", (float) (this.padding + perWidthLength * 31), canvas.getHeight() - this.padding ,textPaint );
        canvas.drawText("Qa", (float) (this.padding + perWidthLength * 34), canvas.getHeight() - this.padding ,textPaint );

        //左侧文字
        canvas.drawText("100",   this.padding  , this.padding + this.margin + perHeihtLength *0, textPaint);
        canvas.drawText("80",    this.padding  , this.padding + this.margin + perHeihtLength *1, textPaint);
        canvas.drawText("60",    this.padding  , this.padding + this.margin + perHeihtLength *2, textPaint);
        canvas.drawText("40",    this.padding  , this.padding + this.margin + perHeihtLength *3, textPaint);
        canvas.drawText("20",    this.padding  , this.padding + this.margin + perHeihtLength *4, textPaint);
        canvas.drawText("0",     this.padding  , this.padding + this.margin + perHeihtLength *5, textPaint);

        canvas.clipRect(this.padding+this.margin ,this.padding+this.margin , canvas.getWidth() - this.padding - this.margin,canvas.getHeight() - this.padding - this.margin);
        canvas.drawColor(getResources().getColor(R.color.gray));
    }

    @Override
    public void drawCurve(Canvas canvas) {
        if (this.data == null){
            return;
        }
        int j = 1;
        for (int i = 0 ; i < this.data.length; i++) {
            Paint test = new Paint();
            test.setStyle(Paint.Style.FILL);
            test.setColor(getResources().getColor(colorArray[i]));
            if (i == 15){
                canvas.drawRect(this.padding + this.margin + perWidthLength*(j+1), (float) ((canvas.getHeight()-this.padding*2-this.margin*2)-data[i]*(canvas.getHeight()-this.padding*2-this.margin*2)/100.0), this.padding + this.margin + perWidthLength*(j+2),canvas.getHeight() - this.padding - this.margin , test);
                continue;
            }
            canvas.drawRect(this.padding + this.margin + perWidthLength*j++, (float) (  this.padding+this.margin +  (canvas.getHeight()-this.padding*2-this.margin*2)* (1-this.data[i]/100)), this.padding + this.margin + perWidthLength*j++,canvas.getHeight() - this.padding - this.margin , test);
        }


    }

    @Override
    protected void scale(Canvas canvas) {
    }


    @Override
    protected void onDraw(Canvas canvas) {

        drawBackground(canvas);
        drawCurve(canvas);

    }

}
