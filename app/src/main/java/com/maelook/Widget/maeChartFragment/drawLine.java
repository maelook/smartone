package com.maelook.Widget.maeChartFragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

import com.maelook.R;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import static com.maelook.app.maelookApp.appDocument;

/**
 * Created by Andrew on 2016/12/12.
 */

public class drawLine extends View {

    private Path path;
    private Canvas canvas;
    private Drawable bitmap;
    private float[] data = new float[401];
    private Paint fingerPaint;
    private Paint dataPaint;

    public drawLine(Context context) {
        super(context);
        this.fingerPaint = new Paint();
        fingerPaint.setStyle(Paint.Style.STROKE);
        fingerPaint.setColor(getResources().getColor(R.color.pink));
        fingerPaint.setStrokeWidth(40);
    }

    public drawLine(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.fingerPaint = new Paint();
        fingerPaint.setStyle(Paint.Style.STROKE);
        fingerPaint.setColor(getResources().getColor(R.color.pink));
        fingerPaint.setStrokeWidth(40);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        this.canvas = canvas;
        try {
            if (this.bitmap != null) {
                this.bitmap.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
                this.bitmap.draw(canvas);
                canvas.drawPath(this.path, this.fingerPaint);
            }
            if (this.bitmap!= null && this.data[200] != -1){
                this.bitmap.setBounds(0,0,canvas.getWidth(),canvas.getHeight());
                this.bitmap.draw(canvas);

                //
                Path path = new Path();
                float perWidth = (float) (canvas.getWidth()/401.0);
                path.moveTo(0,canvas.getHeight());
                this.dataPaint = new Paint();
                this.dataPaint.setStrokeWidth(6);
                this.dataPaint.setColor(getResources().getColor(R.color.black));
                this.dataPaint.setStyle(Paint.Style.STROKE);
                for(int i=0; i < this.data.length; i++){
                    path.lineTo(perWidth*i,canvas.getHeight()*(1-this.data[i]));
                }
                canvas.drawPath(path,this.dataPaint);
                //
                canvas.drawPath(this.path,this.fingerPaint);
            }
            canvas.drawPath(this.path, this.fingerPaint);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void drawPath() {
        invalidate();
    }


    public void setPath(Path path) {
        this.path = path;
    }

    public void setBitmap(Drawable bitmap) {
        this.bitmap = bitmap;
    }

    public void initData(){
        try {
            FileInputStream in = new FileInputStream(appDocument+ File.separator+"data.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            int i = 0;
            while((line = reader.readLine()) != null){
                this.data[i++] = Float.parseFloat(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        invalidate();
    }

    public Bitmap getBitmap() {
        Bitmap res = BitmapFactory.decodeResource(getResources(),R.drawable.fog);
        return res;
    }

    @Override
    public void setForeground(Drawable foreground) {
        this.dataPaint = null;
        this.data = null;
        this.path = null;
        invalidate();

        setBackground(foreground);
    }
}
