package com.maelook.Widget.maeChartFragment;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Shader;
import android.util.AttributeSet;

import com.maelook.Bean.point;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Andrew on 2016/10/26.
 */

/*
* 符合光谱色彩的曲线图
*
* */

public class spectralCurveChart extends BaseChartView {

    private ArrayList data;
    private Path shapePath;
    private ArrayList<point> coordinates;


    public spectralCurveChart(Context context) {
        super(context);
    }

    public spectralCurveChart(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void setData(ArrayList<point> list) {
        this.data = list;
        this.shapePath = new Path();
    }

    @Override
    public void drawableShape(Canvas canvas) {
        //TODO 原来所有的渐变操作，不能直接由资源文件得到，都需要使用graphics的相关方法操作
        LinearGradient shape = new LinearGradient(50,0,canvas.getWidth()-50,0,new int[]{Color.RED,Color.parseColor("#f7a901"),Color.parseColor("#fceb00"),Color.GREEN,Color.CYAN,Color.BLUE,Color.parseColor("#7603fa")},null, Shader.TileMode.REPEAT);
        canvas.save();
        Shader s = new Shader();
        Paint p = new Paint();
        p.setShader(shape);

        shapePath.lineTo(canvas.getWidth()-50,canvas.getHeight()-50);
        shapePath.lineTo(50,canvas.getHeight()-50);
        shapePath.close();

        canvas.drawPath(shapePath,p);
    }

    @Override
    public void drawCurve(Canvas canvas) {
        point point = new point();
        Path Coordinate = new Path();
        this.data = new ArrayList();
        float yLength = canvas.getHeight();
        float xLength = canvas.getWidth();

        Refresh();

        this.data.add(new point(canvas.getWidth() - 60,canvas.getHeight() - 60));

        Paint p = new Paint();
        p.setStyle(Paint.Style.STROKE);
        p.setAntiAlias(true);
        p.setStrokeWidth(3);
        float x = 0;
        float y = 0;
        //移动到原点
        Coordinate.moveTo(0,canvas.getHeight()-50);
        for (int i=0; i < this.data.size() ; i++) {
            if (i == 0){
                x = 50;
                y = canvas.getHeight()-50;
                Coordinate.moveTo(x,y);
            }

            point = (com.maelook.Bean.point) this.data.get(i);
//            if (point.getY_pixs() - y > 0) {
//                x = point.getX_pixs() + 50;
//                y = point.getY_pixs() + 50;
//            } else {
//                x = point.getX_pixs() - 50;
//                y = point.getY_pixs() - 50;
//            }
//            Log.e("TAG",x+"::"+y);
//            Coordinate.quadTo( x  , y ,point.getX_pixs(),point.getY_pixs());
            Coordinate.lineTo(point.getX_pixs(),point.getY_pixs());
        }
        canvas.drawPath(Coordinate,p);
        this.shapePath = Coordinate;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        drawCurve(canvas);
        drawableShape(canvas);

        //TODO 不好做，先做数据库的

    }

    public void Refresh(){
        Random random = new Random();
        this.data.clear();
        for (int i=40; i < 440; i++) {
            this.data.add(new point((float) (i*2),500*random.nextFloat()));
        }
        invalidate();
    }


}
