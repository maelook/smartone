package com.maelook.Widget.maeChartFragment;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;

import com.maelook.Bean.point;
import com.maelook.R;

/**
 * Created by Andrew on 2016/11/15.
 */

public class cie1931Chart extends BaseChart {

    //说明：由于采用配景图作为图标，位置都是强行适应的，所以在使用的时候，尽量保证背景图片的完整性
    //经过测试，如果采用一屏的画面来显示本图标是没有问题的，各方面表现尚可
    //所以在使用的时候尽可能考虑到只在底部的窄小区域用来表现文字形式的数据
    //图标的情况说明，同CIE1976Chart

    private com.maelook.Bean.point point;

    public cie1931Chart(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public cie1931Chart(Context context) {
        super(context);
    }

    @Override
    public void drawableShape(Canvas canvas) {

    }

    @Override
    public void drawBackground(Canvas canvas) {
        Drawable drawable = getResources().getDrawable(R.drawable.cie_1931);
        drawable.setBounds(0,0,canvas.getWidth(),canvas.getHeight());
        drawable.draw(canvas);

    }

    @Override
    public void drawCurve(Canvas canvas) {
        if (this.point == null){
            return;
        }
        point point = transToPoint(canvas,this.point);
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(getResources().getColor(R.color.black));

        canvas.drawCircle(point.getX_pixs(),point.getY_pixs(),5*getResources().getDisplayMetrics().density,paint);
    }

    @Override
    protected void scale(Canvas canvas) {

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        setPoint(new point((float) 0.7, (float) 0.25));
        drawBackground(canvas);
        drawCurve(canvas);

    }

    public point transToPoint(Canvas canvas,point dataPoint){
        float width = canvas.getWidth();
        float height = canvas.getHeight();
        float x = (float) (width/100*(97.8-5.8));
        float y = (float) (height/100*(95.2-1.5));
        float perW = (float) (x /8.0);
        float perH = (float) (y /9.0);
        float finalx = (float) (width*5.8/100 + perW*dataPoint.getX_pixs()*10);
        float finaly = (float) (height*1.5/100 + perH*(9.0-dataPoint.getY_pixs()*10));
        return new point(finalx,finaly);
    }

    public void setPoint(com.maelook.Bean.point point) {
        this.point = point;
    }

    public com.maelook.Bean.point getPoint() {
        return point;
    }
}
