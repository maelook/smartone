package com.maelook.Widget.maeChartFragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.maelook.Bean.point;

import java.util.ArrayList;

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

    public DataMap(Context context) {
        super(context);
    }

    public DataMap(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        if (this.bg_bitmap == null){
            return;
        }
        Drawable drawable = new BitmapDrawable(this.bg_bitmap);
        drawable.setBounds(0,0,canvas.getWidth(),getHeight());
        drawable.draw(canvas);

        if (this.aera == null || this.aeraPaint == null){
            return;
        }
        canvas.drawPath(this.aera,this.aeraPaint);
        Log.e("path","aera");

        if (this.data == null || this.dataPaint == null){
            return;
        }
        Log.e("path","data");
        for (int i=0; i < this.data.size(); i++){
            canvas.drawText(this.data.get(i).getDeclare(),this.data.get(i).getX_pixs(),this.data.get(i).getY_pixs(),this.dataPaint);
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
