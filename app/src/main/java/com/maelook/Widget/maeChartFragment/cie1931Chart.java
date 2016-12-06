package com.maelook.Widget.maeChartFragment;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;

/**
 * Created by Andrew on 2016/11/15.
 */

public class cie1931Chart extends BaseChart {


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
}
