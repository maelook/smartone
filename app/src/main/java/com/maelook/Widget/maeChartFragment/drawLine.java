package com.maelook.Widget.maeChartFragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.maelook.Bean.point;
import com.maelook.R;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import static com.maelook.app.maelookApp.appDocument;

/**
 * Created by Andrew on 2016/12/12.
 */

public class drawLine extends View {

    private Path path;
    private Bitmap bitmap;
    private Canvas canvas;
    private Drawable drawable;
    private float[] data;
    private ArrayList<point> pointData = new ArrayList<>();
    private Paint fingerPaint;
    private Paint dataPaint;

    public drawLine(Context context) {
        super(context);
        bitmap = Bitmap.createBitmap(1980,1080, Bitmap.Config.ARGB_8888);
        this.fingerPaint = new Paint();
        fingerPaint.setStyle(Paint.Style.STROKE);
        fingerPaint.setColor(getResources().getColor(R.color.pink));
        fingerPaint.setStrokeWidth(40);
    }

    public drawLine(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.fingerPaint = new Paint();
        bitmap = Bitmap.createBitmap(1980,1080, Bitmap.Config.ARGB_8888);
        fingerPaint.setStyle(Paint.Style.STROKE);
        fingerPaint.setColor(getResources().getColor(R.color.pink));
        fingerPaint.setStrokeWidth(40);
    }


    public void drawFog(){
        this.canvas = new Canvas();
        this.canvas.setBitmap(this.bitmap);

        this.draw(this.canvas);

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        try {
            this.canvas = canvas;
            if (this.drawable == null) {
                return;
            }
            this.drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            this.drawable.draw(canvas);
            if (this.path == null) {
                return;
            }
            canvas.drawPath(this.path, this.fingerPaint);
            if (this.data == null) {
                return;
            }
            //光谱路径
            Path pathaa = new Path();
            float perWidth = (float) (canvas.getWidth() / 401.0);
            pathaa.moveTo(0, canvas.getHeight());
            this.dataPaint = new Paint();
            this.dataPaint.setStrokeWidth(6);
            this.dataPaint.setColor(getResources().getColor(R.color.black));
            this.dataPaint.setStyle(Paint.Style.STROKE);
            for (int i = 0; i < this.data.length; i++) {
                pathaa.lineTo(perWidth * i, canvas.getHeight() * (1 - this.data[i]));
            }
            canvas.drawPath(pathaa, this.dataPaint);
            if (this.pointData.size() <= 0){
                return;
            }
            Paint point = new Paint();
            point.setColor(getResources().getColor(R.color.black));
            point.setStyle(Paint.Style.STROKE);
            point.setTextSize(30);
            for (int i=0; i < this.pointData.size();i++) {
                canvas.drawText(this.pointData.get(i).getDeclare(),this.pointData.get(i).getX_pixs(),this.pointData.get(i).getY_pixs(),point);
            }
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

    public void setDrawable(Drawable drawable) {
        this.drawable = drawable;
        invalidate();
    }

    public void initData() {
        try {
            this.data = new float[401];
            FileInputStream in = new FileInputStream(appDocument + File.separator + "data.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            int i = 0;
            while ((line = reader.readLine()) != null) {
                this.data[i++] = Float.parseFloat(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.e("line", "before");
        invalidate();
        Log.e("line", "after");
    }

    public Bitmap getDrawable() {
        Bitmap res = BitmapFactory.decodeResource(getResources(), R.drawable.fog);
        return res;
    }

    public void addPoint(point point) {
        this.pointData.add(point);
        invalidate();
    }

    public Bitmap save(){
        return this.bitmap;
    }

}
