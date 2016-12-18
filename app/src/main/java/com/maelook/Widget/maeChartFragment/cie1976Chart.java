package com.maelook.Widget.maeChartFragment;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

import com.maelook.Bean.point;
import com.maelook.R;

/**
 * Created by Andrew on 2016/11/15.
 */

public class cie1976Chart extends BaseChart {

    private point dataPoint;

    public cie1976Chart(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public cie1976Chart(Context context) {
        super(context);
    }

    @Override
    public void drawableShape(Canvas canvas) {
    }

    @Override
    public void drawBackground(Canvas canvas) {
        Drawable drawable = getResources().getDrawable(R.drawable.cie_1976);
        drawable.setBounds(0,0,canvas.getWidth(),canvas.getHeight());
        drawable.draw(canvas);

    }

    @Override
    public void drawCurve(Canvas canvas) {
        if (this.dataPoint == null) {
            return;
        }
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(getResources().getColor(R.color.black));
        canvas.drawCircle(transToPoint(canvas,this.dataPoint).getX_pixs(),transToPoint(canvas,this.dataPoint).getY_pixs(), 5*getResources().getDisplayMetrics().density,paint);

    }

    @Override
    protected void scale(Canvas canvas) {
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        setDataPoint(new point((float) 0,(float) 0.3));
        drawBackground(canvas);
        drawCurve(canvas);

    }



    public point transToPoint(Canvas canvas, point dataPoint){
        float width = canvas.getWidth();
        float height = canvas.getHeight();
        float x = (float) (width/100*(91.5-5.0));
        float y = (float) (height/100*(95.0-7.8));
        float perW = (float) (x /6.0);
        float perH = (float) (y /6.0);
        float finalx = (float) (width*5.0/100.0 + perW*dataPoint.getX_pixs()*10.0);
        float finaly = (float) (height*7.8/100.0 + perH*(6.0-dataPoint.getY_pixs()*10.0));
        return new point(finalx,finaly);
    }


    public void setDataPoint(point dataPoint) {
        this.dataPoint = dataPoint;
    }

    public point getDataPoint() {
        return dataPoint;
    }
}
