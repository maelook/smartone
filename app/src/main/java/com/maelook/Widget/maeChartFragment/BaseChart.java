package com.maelook.Widget.maeChartFragment;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Andrew on 2016/10/26.
 */

public abstract class BaseChart extends View {

    public BaseChart(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BaseChart(Context context) {
        super(context);
    }

    public abstract void drawableShape(Canvas canvas);

    public abstract void drawBackground(Canvas canvas);

    public abstract void drawCurve(Canvas canvas);

    protected abstract void scale(Canvas canvas);

    //dp to px
    public float dpToPx(float dp){
        float scale =getResources().getDisplayMetrics().density;
        return dp*scale + 0.5f;
    }

}
