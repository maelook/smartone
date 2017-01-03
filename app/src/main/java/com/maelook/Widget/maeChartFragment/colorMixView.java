package com.maelook.Widget.maeChartFragment;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
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


    private float padding = dpToPx(getResources().getDimension(R.dimen.maelookdimension8));
    private float gap;
    private float perLength;
    private Paint redFill;
    private Paint greenFill;
    private Paint blueFill;
    private Paint whiteFill;
    private Paint redStroke;
    private Paint greenStroke;
    private Paint blueStroke;
    private Paint whiteStroke;
    private Paint blackStroke;
    private Paint text;
    private int rnum = 0;
    private int gnum = 0;
    private int bnum = 0;
    private int wnum = 0;
    private int pre_rnum = 0;
    private int pre_gnum = 0;
    private int pre_bnum = 0;
    private int pre_wnum = 0;
    private Paint blackFill;
    private float show_pre;
    private float show_next;
    private double start;
    private double length;
    private float pre;
    private float next;
    private double left_width;
    private double left_height;

    public colorMixView(Context context) {
        super(context);
        initPaint();
    }

    private void initPaint() {
        redFill = new Paint();
        redFill.setStyle(Paint.Style.FILL);
        redFill.setARGB(255, 255, 0, 0);
        blackStroke = new Paint();
        blackStroke.setStyle(Paint.Style.STROKE);
        blackStroke.setARGB(255, 0, 0, 0);
        greenFill = new Paint();
        greenFill.setStyle(Paint.Style.FILL);
        greenFill.setARGB(255, 0, 255, 0);
        blueFill = new Paint();
        blueFill.setStyle(Paint.Style.FILL);
        blueFill.setARGB(255, 0, 0, 255);
        blackFill = new Paint();
        blackFill.setStyle(Paint.Style.FILL);
        blackFill.setARGB(255, 0, 0, 0);
        blackFill.setTextSize(dpToPx(getResources().getDimension(R.dimen.maelookdimension10)));
        blackFill.setTextAlign(Paint.Align.CENTER);

        redStroke = new Paint();
        redStroke.setStyle(Paint.Style.STROKE);
        redStroke.setStrokeWidth(dpToPx(2));
        redStroke.setARGB(255, 255, 0, 0);
        greenStroke = new Paint();
        greenStroke.setStrokeWidth(dpToPx(2));
        greenStroke.setStyle(Paint.Style.STROKE);
        greenStroke.setARGB(255, 0, 255, 0);
        blueStroke = new Paint();
        blueStroke.setStrokeWidth(dpToPx(2));
        blueStroke.setStyle(Paint.Style.STROKE);
        blueStroke.setARGB(255, 0, 0, 255);

        whiteStroke = new Paint();
        whiteStroke.setStrokeWidth(dpToPx(3));
        whiteStroke.setStyle(Paint.Style.STROKE);
        whiteStroke.setARGB(255, 255, 255, 255);

        whiteFill = new Paint();
        whiteFill.setStyle(Paint.Style.FILL);
        whiteFill.setARGB(255, 255, 255, 255);

        text = new Paint();
        text.setStyle(Paint.Style.FILL);
        text.setColor(Color.WHITE);
        text.setTextAlign(Paint.Align.CENTER);
        text.setTextSize(dpToPx(getResources().getDimension(R.dimen.maelookdimension10)));

    }

    public colorMixView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }


    @Override
    public void drawableShape(Canvas canvas) {

    }

    @Override
    public void drawBackground(Canvas canvas) {

        //左侧大方块
        left_width = (canvas.getWidth() - this.padding * 2) / 3.0 * 2.0 / 3.0;
        left_height = (canvas.getHeight() - this.padding * 2) / 11.0;

        canvas.drawRect(this.padding, this.padding, (float) (this.padding + left_width), (float) (this.padding + left_height * 2), redFill);
        canvas.drawRect(this.padding, (float) (this.padding + left_height * 3.0), (float) (this.padding + left_width), (float) (this.padding + left_height * 5), greenFill);
        canvas.drawRect(this.padding, (float) (this.padding + left_height * 6.0), (float) (this.padding + left_width), (float) (this.padding + left_height * 8), blueFill);
        canvas.drawRect(this.padding, (float) (this.padding + left_height * 9.0), (float) (this.padding + left_width), (float) (this.padding + left_height * 11), whiteFill);

        canvas.drawText("红", (float) (this.padding + left_width / 2.0), (float) (this.padding + left_height * 1.4), text);
        canvas.drawText("绿", (float) (this.padding + left_width / 2.0), (float) (this.padding + left_height * 4.4), text);
        canvas.drawText("蓝", (float) (this.padding + left_width / 2.0), (float) (this.padding + left_height * 7.4), text);
        canvas.drawText("白", (float) (this.padding + left_width / 2.0), (float) (this.padding + left_height * 10.4), blackFill);

        //右侧小方块

        start = (canvas.getWidth() - this.padding * 2) / 3.0;
        length = start * 2.0 / 21.0;
        //小方块方框
        pre = (float) (start + length * 1);
        next = (float) (start + length * 4);

        for (int i = 0; i < 5; i++) {
            canvas.drawRect(pre, this.padding, next, (float) (this.padding + left_height * 0.8), redStroke);
            canvas.drawRect(pre, (float) (this.padding + left_height * 3.0), next, (float) (this.padding + left_height * 3.8), greenStroke);
            canvas.drawRect(pre, (float) (this.padding + left_height * 6.0), next, (float) (this.padding + left_height * 6.8), blueStroke);
            canvas.drawRect(pre + 2 , (float) (this.padding + left_height * 9.0) + 2, next - 2, (float) (this.padding + left_height * 9.8) - 2, blackStroke);

            canvas.drawRect(pre, (float) (this.padding + left_height * 1.0), next, (float) (this.padding + left_height * 1.8), redStroke);
            canvas.drawRect(pre, (float) (this.padding + left_height * 4.0), next, (float) (this.padding + left_height * 4.8), greenStroke);
            canvas.drawRect(pre, (float) (this.padding + left_height * 7.0), next, (float) (this.padding + left_height * 7.8), blueStroke);
            canvas.drawRect(pre + 2 , (float) (this.padding + left_height * 10.0) +2 , next - 2, (float) (this.padding + left_height * 10.8) - 2 , blackStroke);

            pre = (float) (pre + length * 4);
            next = (float) (next + length * 4);

        }


        //小方块显示
        show_pre = (float) (start + length * 1);
        show_next = (float) (start + length * 4);

        if (pre_rnum == 1 && rnum ==1 ){
            rnum = 0;
        }
        if (pre_gnum == 1 && gnum ==1 ){
            gnum = 0;
        }
        if (pre_bnum == 1 && bnum ==1 ){
            bnum = 0;
        }
        if (pre_wnum == 1 && wnum ==1 ){
            wnum = 0;
        }


        for (int i = 0; i < 5; i++) {

            if (rnum-i > 0) {
                canvas.drawRect(show_pre, this.padding, show_next, (float) (this.padding + left_height * 0.8), redFill);
            }
            if (gnum-i>0){
                canvas.drawRect(show_pre, (float) (this.padding + left_height * 3.0),  show_next, (float) (this.padding + left_height * 3.8), greenFill);
            }
            if (bnum-i>0){
                canvas.drawRect(show_pre, (float) (this.padding + left_height * 6.0),  show_next, (float) (this.padding + left_height * 6.8), blueFill);
            }
            if (wnum-i>0){
                canvas.drawRect(show_pre, (float) (this.padding + left_height * 9.0),  show_next, (float) (this.padding + left_height * 9.8), whiteFill);
            }


            if (rnum-5-i > 0){
                canvas.drawRect(show_pre, (float) (this.padding + left_height * 1.0),  show_next, (float) (this.padding + left_height * 1.8), redFill);
            }
            if (gnum-5-i > 0){
                canvas.drawRect(show_pre, (float) (this.padding + left_height * 4.0),  show_next, (float) (this.padding + left_height * 4.8), greenFill);
            }
            if (bnum-5-i > 0){
                canvas.drawRect(show_pre, (float) (this.padding + left_height * 7.0),  show_next, (float) (this.padding + left_height * 7.8), blueFill);
            }
            if (wnum-5-i > 0){
                canvas.drawRect(show_pre, (float) (this.padding + left_height * 10.0), show_next, (float) (this.padding + left_height * 10.8), whiteFill);
            }

            show_pre  = (float) (show_pre + length * 4);
            show_next = (float) (show_next + length * 4);

        }

        // keep pre num of color!
        pre_rnum = rnum;
        pre_gnum = gnum;
        pre_bnum = bnum;
        pre_wnum = wnum;

    }

    @Override
    public void drawCurve(Canvas canvas) {

    }

    @Override
    protected void scale(Canvas canvas) {
        //间距
        gap = dpToPx(getResources().getDimension(R.dimen.maelookdimension4));
        //单位长
        perLength = (float) (canvas.getWidth() < canvas.getHeight() ? ((canvas.getWidth() - this.padding * 2 - gap * 6.0) / 5.0) : ((canvas.getHeight() - this.padding * 2 - gap * 6.0) / 5.0));

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        scale(canvas);
        drawBackground(canvas);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                changeStatus(event);
                break;
            case MotionEvent.ACTION_MOVE:
//                changeStatus(event);
                break;
        }

        return true;
    }

    private void changeStatus(MotionEvent event) {
        //选择响应区域
        if (event.getX() < this.getWidth()-this.padding && event.getX() > start + length * 1 && event.getY() < this.padding + this.left_height*2 && event.getY() > this.padding  + this.left_height*0){
            rnum = 0 ;
            int num = (int) ((event.getX() - (start + length ))/length);
            if (num>1 && num < 4){
                setRnum(1);
            }
            if (num>5 && num < 8){
                setRnum(2);
            }
            if (num>9 && num < 12){
                setRnum(3);
            }
            if (num>12 && num < 16){
                setRnum(4);
            }
            if ( num>16.5 && num < 21){
                setRnum(5);
            }
            if ( rnum>0 && event.getY() > this.padding + left_height*1){
                rnum += 5;
                setRnum(rnum);
            }
        }

        if (event.getX() < this.getWidth()-this.padding && event.getX() > start + length * 1 && event.getY() < this.padding + this.left_height*5 && event.getY() > this.padding + this.left_height*3){
            gnum = 0 ;
            int num = (int) ((event.getX() - (start + length ))/length);
            if (num>1 && num < 4){
                setGnum(1);
            }
            if (num>5 && num < 8){
                gnum = 2;
                setGnum(2);
            }
            if (num>9 && num < 12){
                setGnum(3);
            }
            if (num>12 && num < 16){
                setGnum(4);
            }
            if (num>16 && num < 20){
                setGnum(5);
            }
            if ( gnum > 0 && event.getY() > this.padding + left_height*4){
                gnum += 5;
                setGnum(gnum);
            }
        }
        if (event.getX() < this.getWidth()-this.padding && event.getX() > start + length * 1 && event.getY() < this.padding + this.left_height*8 && event.getY() > this.padding + this.left_height*6){
            bnum = 0 ;
            int num = (int) ((event.getX() - (start + length ))/length);
            if (num>1 && num < 4){
                setBnum(1);
            }
            if (num>5 && num < 8){
                setBnum(2);
            }
            if (num>9 && num < 12){
                setBnum(3);
            }
            if (num>13 && num < 16){
                setBnum(4);
            }
            if (num>16 && num < 20){
                setBnum(5);
            }
            if (bnum >0 && event.getY() > this.padding + left_height*7){
                bnum += 5;
                setBnum(bnum);
            }
        }
        if (event.getX() < this.getWidth()-this.padding && event.getX() > start + length * 1 && event.getY() < this.padding + this.left_height*11 && event.getY() > this.padding + this.left_height*9){
            wnum = 0 ;
            int num = (int) ((event.getX() - (start + length ))/length);
            if (num>1 && num < 4){
                setWnum(1);
            }
            if (num>5 && num < 8){
                setWnum(2);
            }
            if (num>9 && num < 12){
                setWnum(3);
            }
            if (num>13 && num < 16){
                setWnum(4);
            }
            if (num>16 && num < 20){
                setWnum(5);
            }
            if ( wnum > 0 && event.getY() > this.padding + left_height*10){
                wnum += 5;
                setWnum(wnum);
            }
        }
    }

    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }

    public int getG() {
        return g;
    }

    public void setG(int g) {
        this.g = g;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public void setRnum(int rnum) {
        this.rnum = rnum;
        RefreshColor();
        invalidate();
    }

    private void RefreshColor() {
        r = rnum*25;
        g = gnum*25;
        b = bnum*25;
        a = wnum*20+50;
    }

    public void setGnum(int gnum) {
        this.gnum = gnum;
        RefreshColor();
        invalidate();
    }

    public void setBnum(int bnum) {
        this.bnum = bnum;
        RefreshColor();
        invalidate();
    }

    public void setWnum(int wnum) {
        this.wnum = wnum;
        RefreshColor();
        invalidate();
    }


}
