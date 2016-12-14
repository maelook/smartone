package com.maelook.Bean;

/**
 * Created by Andrew on 2016/10/26.
 */

/*
* 用来表示坐标系的点的位置
* @parama: X_pixs   X轴坐标的位置
* @parama: X_pixs:  Y轴坐标的位置
*
* */


public class point {

    private float X_pixs;
    private float Y_pixs;

    private String declare;

    public point(float x_pixs, float y_pixs) {
        X_pixs = x_pixs;
        Y_pixs = y_pixs;
    }

    public point() {
    }

    public float getX_pixs() {
        return X_pixs;
    }

    public point setX_pixs(float x_pixs) {
        X_pixs = x_pixs;
        return this;
    }

    public float getY_pixs() {
        return Y_pixs;
    }

    public point setY_pixs(float y_pixs) {
        Y_pixs = y_pixs;
        return this;
    }

    public String getDeclare() {
        return declare;
    }

    public point setDeclare(String declare) {
        this.declare = declare;
        return this;
    }
}
