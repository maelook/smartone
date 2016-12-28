package com.maelook.Widget.maeChartFragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.maelook.R;

/**
 * Created by Andrew on 2016/12/14.
 */

public class LightSceneView extends View {

    private Bitmap bitmap;
    private Canvas canvas;
    private double[] LightData;
    private Bitmap backGroundBitmap;
    private Paint CurvePaint;
    private Paint defaultCurvePaint;

    public LightSceneView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.defaultCurvePaint = new Paint();
        this.defaultCurvePaint.setStrokeWidth(2*getResources().getDisplayMetrics().density);
        this.defaultCurvePaint.setStyle(Paint.Style.STROKE);
        this.defaultCurvePaint.setColor(getResources().getColor(R.color.black));
    }

    public LightSceneView(Context context) {
        super(context);
        this.defaultCurvePaint.setStrokeWidth(2*getResources().getDisplayMetrics().density);
        this.defaultCurvePaint.setStyle(Paint.Style.STROKE);
        this.defaultCurvePaint.setColor(getResources().getColor(R.color.black));
    }
    //功能：照一张照片，显示光谱图，然后保存


    @Override
    protected void onDraw(Canvas canvas) {

        if (this.bitmap == null){
            return;
        }
        Drawable drawable = BitmapToDrawable(this.backGroundBitmap);
        drawable.setBounds(0,0,canvas.getWidth(),canvas.getHeight());
        drawable.draw(canvas);

        //一定要同时设置了光谱数据和画笔才能描绘光谱图
        if (this.LightData == null){
            return;
        }
        //光谱路径
        Path path = new Path();
        float perWidth = (float) (canvas.getWidth() / 401.0);
        path.moveTo(0, canvas.getHeight());

        for (int i = 0; i < this.LightData.length; i++) {
            path.lineTo(perWidth * i, (float) ( canvas.getHeight()/2.0 + canvas.getHeight()/2.0 * (1 - this.LightData[i])));
        }
        //没有设置画笔则使用默认画笔
        if (this.CurvePaint != null) {
            canvas.drawPath(path , this.CurvePaint);
        } else {
            canvas.drawPath(path,this.defaultCurvePaint);
        }

    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = Bitmap.createBitmap(2000,1200, Bitmap.Config.ARGB_8888);
        this.backGroundBitmap = bitmap;
        this.canvas = new Canvas(this.bitmap);
        this.draw(this.canvas);
        invalidate();
    }

    public void setLightData(double[] lightData) {
        this.LightData = lightData;
        //一定要调用自己定义的canvas才能保存下来对应的图像
        this.draw(this.canvas);
        invalidate();
    }

    public Bitmap getBitmap() {
        return this.bitmap;
    }

    public Drawable BitmapToDrawable(Bitmap bitmap){
        return new BitmapDrawable(bitmap);
    }

    public void setCurvePaint(Paint paint){
        this.CurvePaint = paint;
    }

    public Paint getCurvePaint() {
        return this.CurvePaint;
    }

}
