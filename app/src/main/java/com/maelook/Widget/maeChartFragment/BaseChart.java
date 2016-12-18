package com.maelook.Widget.maeChartFragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.maelook.R;

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

    public Bitmap save(){
        Bitmap bitmap = Bitmap.createBitmap(this.getWidth(),this.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        canvas.drawColor(getResources().getColor(R.color.white));
        this.draw(canvas);
        invalidate();
        return bitmap;
    }

}
