package com.maelook.Widget.maeChartFragment;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.maelook.R;

/**
 * Created by andrew on 2016/12/25.
 */

public class colorMixView extends BaseChart {

    private int a;
    private int r;
    private int g;
    private int b;

    private float padding = dpToPx(getResources().getDimension(R.dimen.maelookdimension4));
    private float gap;
    private float perLength;

    public colorMixView(Context context) {
        super(context);
    }

    public colorMixView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }



    @Override
    public void drawableShape(Canvas canvas) {

    }

    @Override
    public void drawBackground(Canvas canvas) {
        Paint redFill = new Paint();
        redFill.setStyle(Paint.Style.FILL);
        redFill.setARGB(255,255,0,0);
        Paint greenFill = new Paint();
        greenFill.setStyle(Paint.Style.FILL);
        greenFill.setARGB(255,0,255,0);
        Paint blueFill = new Paint();
        blueFill.setStyle(Paint.Style.FILL);
        blueFill.setARGB(255,0,0,255);
        Paint whiteFill = new Paint();
        whiteFill.setStyle(Paint.Style.FILL);
        whiteFill.setARGB(255,0,0,0);

        Paint redStroke = new Paint();
        redStroke.setStyle(Paint.Style.STROKE);
        redStroke.setARGB(255,255,0,0);
        Paint greenStroke = new Paint();
        greenStroke.setStyle(Paint.Style.STROKE);
        greenStroke.setARGB(255,0,255,0);
        Paint blueStroke = new Paint();
        blueStroke.setStyle(Paint.Style.STROKE);
        blueStroke.setARGB(255,0,0,255);;
        Paint whiteStroke = new Paint();
        whiteStroke.setStyle(Paint.Style.STROKE);
        whiteStroke.setARGB(0,0,0,0);

        Paint text = new Paint();
        text.setStyle(Paint.Style.FILL);
        text.setColor(Color.WHITE);
        text.setTextAlign(Paint.Align.CENTER);
        text.setTextSize(35);
        //框
        float x = this.padding;
        for (int i=1;i<=11;i++){
                canvas.drawRect( x + perLength*(i-1) ,this.padding, x + perLength*i,this.padding + perLength,redStroke);
                canvas.drawRect( x + perLength*(i-1) ,this.padding + perLength + gap , x + perLength*i,this.padding + perLength*2 +gap ,greenStroke);
                canvas.drawRect( x + perLength*(i-1) ,this.padding  + perLength*3 +gap , x + perLength*i,this.padding + perLength * 4 + gap,blueStroke);
                canvas.drawRect( x + perLength*(i-1) ,this.padding  + perLength*4 +gap , x + perLength*i,this.padding + perLength * 5 + gap,whiteStroke);
                x +=  gap;

        }

        //实心

        float xx = this.padding;
        for (int i=1;i<=5;i++){

            canvas.drawRect( xx + perLength*(i-1) , this.padding, xx + perLength*i   ,  this.padding + perLength,redFill);
            canvas.drawRect( xx + perLength*(i-1) , this.padding + perLength + gap   ,  xx + perLength*i , this.padding + perLength*2 +gap ,greenFill);
            canvas.drawRect( xx + perLength*(i-1) , this.padding  + perLength*3 +gap ,  xx + perLength*i , this.padding + perLength * 4 + gap,blueFill);
            canvas.drawRect( xx + perLength*(i-1) , this.padding  + perLength*5 +gap ,  xx + perLength*i , this.padding + perLength * 6 + gap,whiteFill);
            if (i==1){
                canvas.drawText("红", (float) (xx + perLength*(i-1) + perLength/2.0) , (float) (this.padding + (perLength+gap)/2.0 ) , text);
                canvas.drawText("绿", (float) (xx + perLength*(i-1) + perLength/2.0) , (float) (this.padding + (perLength+gap)*3.0/2.0 ), text);
                canvas.drawText("蓝", (float) (xx + perLength*(i-1) + perLength/2.0) , (float) (this.padding + perLength*7.0/2.0 + gap*3.0/2.0), text);
                canvas.drawText("白", (float) (xx + perLength*(i-1) + perLength/2.0) , (float) (this.padding + perLength*11.0/2.0 + gap*3.0/2.0), text);
            }
            xx +=  gap;

        }

    }

    @Override
    public void drawCurve(Canvas canvas) {

    }

    @Override
    protected void scale(Canvas canvas) {
        //间距
        gap =  dpToPx(getResources().getDimension(R.dimen.maelookdimension4));
        //单位长
        perLength = (float) (canvas.getWidth() < canvas.getHeight() ? ((canvas.getWidth() - this.padding*2 - gap * 10.0 ) /11.0):((canvas.getHeight() - this.padding*2 - gap * 10.0 ) /11.0));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        scale(canvas);
        drawBackground(canvas);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                //选择响应区域

                break;
            case MotionEvent.ACTION_MOVE:

                break;
            case MotionEvent.ACTION_UP:

                break;

        }

        return true;
    }



}
