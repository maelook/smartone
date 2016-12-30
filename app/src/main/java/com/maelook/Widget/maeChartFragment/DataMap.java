package com.maelook.Widget.maeChartFragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.maelook.Bean.point;
import com.maelook.R;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Andrew on 2016/12/14.
 */

public class DataMap extends View {
    private Canvas canvas;
    private Bitmap bitmap;
    private Bitmap bg_bitmap;
    private Path aera;
    private ArrayList<point> data;
    private Paint dataPaint;
    private Paint aeraPaint;
    private Paint defaultDataPaint;
    private Paint defaultAeraPaint;
    private Region region;

    public DataMap(Context context) {
        super(context);
        this.defaultDataPaint = new Paint();
        this.defaultAeraPaint = new Paint();
        this.defaultDataPaint.setTextSize(20*getResources().getDisplayMetrics().density);
        this.defaultDataPaint.setStyle(Paint.Style.STROKE);
        this.defaultDataPaint.setColor(Color.BLACK);
        this.defaultDataPaint.setUnderlineText(true);
        this.defaultAeraPaint.setStrokeWidth(20*getResources().getDisplayMetrics().density);
        this.defaultAeraPaint.setStrokeWidth(3*getResources().getDisplayMetrics().density);
        this.defaultAeraPaint.setStyle(Paint.Style.STROKE);
        this.defaultAeraPaint.setColor(getResources().getColor(R.color.colorAccent));
        this.region = new Region();
    }

    public DataMap(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.defaultDataPaint = new Paint();
        this.defaultAeraPaint = new Paint();
        this.defaultDataPaint.setTextSize(20*getResources().getDisplayMetrics().density);
        this.defaultDataPaint.setStyle(Paint.Style.STROKE);
        this.defaultDataPaint.setUnderlineText(true);
        this.defaultDataPaint.setColor(Color.BLACK);
        this.defaultAeraPaint.setStrokeWidth(12*getResources().getDisplayMetrics().density);
        this.defaultAeraPaint.setStrokeWidth(3*getResources().getDisplayMetrics().density);
        this.defaultAeraPaint.setStyle(Paint.Style.STROKE);
        this.defaultAeraPaint.setColor(getResources().getColor(R.color.colorAccent));
        this.region = new Region();
    }

    @Override
    protected void onDraw(Canvas canvas) {

        //画背景图
        if (this.bg_bitmap == null){
            return;
        }
        Drawable drawable = new BitmapDrawable(this.bg_bitmap);
        drawable.setBounds(0,0,canvas.getWidth(),getHeight());
        drawable.draw(canvas);
        //画选中的区域
        if (this.aera == null){
            return;
        }
        RectF r = new RectF();
        this.aera.computeBounds(r,true);
        region.setPath(this.aera,new Region((int) r.left,(int)r.top, (int) r.right, (int) r.bottom));
        if (this.aeraPaint != null) {
            canvas.drawPath(this.aera,this.aeraPaint);
        } else {
            canvas.drawPath(this.aera,this.defaultAeraPaint);
        }

        //画数据点和数据点的内容
        if (this.data == null){
            return;
        }
        for (int i=0; i < this.data.size(); i++){

            if (!region.contains((int) this.data.get(i).getX_pixs(),(int) this.data.get(i).getY_pixs())){
                continue;
            }
            //没有设置画笔则默认使用随机画笔  Paint.setStrokeWidth(X);
            if (this.dataPaint != null) {
                canvas.drawText(this.data.get(i).getDeclare(),this.data.get(i).getX_pixs(),this.data.get(i).getY_pixs(),this.dataPaint);
            } else {
                canvas.drawText(this.data.get(i).getDeclare(),this.data.get(i).getX_pixs(),this.data.get(i).getY_pixs(),this.defaultDataPaint);
            }
        }

    }

    public void setBg_bitmap(Bitmap bitmap) {
        this.bg_bitmap = bitmap;
        this.bitmap = Bitmap.createBitmap(2000,2000, Bitmap.Config.ARGB_8888);
        this.canvas = new Canvas(this.bitmap);
        this.draw(this.canvas);
    }

    public void setAera(Path aera) {
        this.aera = aera;
        this.draw(this.canvas);
        invalidate();
    }

    public void setAeraPaint(Paint aeraPaint) {
        this.aeraPaint = aeraPaint;
    }

    public void setDataPaint(Paint dataPaint) {
        this.dataPaint = dataPaint;
    }

    public void setData(ArrayList<point> data) {
        this.data = data;
        this.draw(this.canvas);
        invalidate();
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public Bitmap getBg_bitmap() {
        return bg_bitmap;
    }

    public Paint getAeraPaint() {
        return aeraPaint;
    }

    public Paint getDataPaint() {
        return dataPaint;
    }

    public ArrayList<point> getData() {
        return data;
    }


}
