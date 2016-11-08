package com.maelook.Bean;

import android.graphics.Bitmap;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Transient;

/**
 * Created by Andrew on 2016/10/26.
 */

/*
* 特别说明：
* @parama : Qa      //TLCI            21个数值                        每个范围，0.00-100.00
* @parama : cqs     //CQS             共计16个数值                    每个0-100
* @parama : cie     //由于是由计算得到的，并且数据两十分巨大，设计初衷是选择可保存，故不将其固定添加进入数据库  //TODO  这里问一下老板
*  以上两个参数原本应该是int[]或者float[]的形式，但是Android数据库不支持此类型数据的存储
*  原本直接存二进制数据，取出来后再转换也可以，但是这样存在数据变化的可能，并且数据库框架为了更加安全和规范化，已经不支持此种类型的添加
* （不支持仅此数据框架，如果完全基于原生操作，应该仍然可以，但考虑到自己去封装搭建数据库，工作量巨大，还牵涉到线程摆渡的问题）
*
*  处理方式：选择将他们以字符串的形式保存，每个数位之间用某个符号隔开，设置解析方法并重写toString()方法来解决无法存储的问题
*  具体的解析和处理方式由使用的人自行设置，对于数据库而言，每次交流都是String即可
*
*  ps:请不要随意改动这里的代码和Package——>com.maelook.daoBean内的代码，二者是相互映射关系，互相关联，并直接对应数据库内的数据内容
*  ps:请不要随意改动这里的代码和Package——>com.maelook.daoBean内的代码，二者是相互映射关系，互相关联，并直接对应数据库内的数据内容
*  ps:请不要随意改动这里的代码和Package——>com.maelook.daoBean内的代码，二者是相互映射关系，互相关联，并直接对应数据库内的数据内容
* */

@Entity
public class singleRecord {

    private int s;                 //光谱             440个强度数值                  每个数值0-65535以内
    private int Lux;               //照度            单点整数值，最大数值200000      0-200000
    private int cct;               //色温            单点整数，最大为100000          0-100000
    private int cri;               //显色性          共计16个数值                    每个0-100
    private float x;              //Xy数值          两个数值                        小数点后五位，即0.00000-1.00000
    private float y;              //Xy数值          两个数值                        小数点后五位，即0.00000-1.00000
    @Transient
    public Bitmap CIE;             //CIE图
    private float uv_u;               //Uv              两个数值                        小数点后五位，即0.00000-1.00000
    private float uv_v;               //Uv              两个数值                        小数点后五位，即0.00000-1.00000
    private float Duv;             //Uuv             一个数值                        小数点后五位，即-1.00000-1.00000
    private int Dwave;             //主波长          一个数值                        340-780
    private int Pwave;             //峰波长          一个数值                        340-780
    private float colorRatio;     //颜色比          比例数值                        0．00-1．00
    private int Rf;                 //颜色逼真度      一个数值                        0-100
    private int Rg;                 //颜色饱和度      一个数值 0-200                  0-200
    private float V;                //颜色维度        数值范围-1~1                    -1~+1
    private String Qa;             //TLCI            21个数值                        每个范围，0.00-100.00
    private int gai;                //GAI             一个数值                        0-200
    private String cqs;             //CQS             共计16个数值                    每个0-100
    private int rColor;            //24色颜色        RGB颜色值                       0-255
    private int gColor;            //24色颜色        RGB颜色值                       0-255
    private int bColor;            //24色颜色        RGB颜色值                       0-255
    @Id
    private String date;            //此条记录的保存时间         yyyymmddhhmmss

    //后续开发可能会添加的内容
//    public String date;
//    public String location;
//    public Bitmap LocationImage;
//    public String fileName;

    @Generated(hash = 998539342)
    public singleRecord(int s, int Lux, int cct, int cri, float x, float y, float uv_u, float uv_v, float Duv,
            int Dwave, int Pwave, float colorRatio, int Rf, int Rg, float V, String Qa, int gai, String cqs,
            int rColor, int gColor, int bColor, String date) {
        this.s = s;
        this.Lux = Lux;
        this.cct = cct;
        this.cri = cri;
        this.x = x;
        this.y = y;
        this.uv_u = uv_u;
        this.uv_v = uv_v;
        this.Duv = Duv;
        this.Dwave = Dwave;
        this.Pwave = Pwave;
        this.colorRatio = colorRatio;
        this.Rf = Rf;
        this.Rg = Rg;
        this.V = V;
        this.Qa = Qa;
        this.gai = gai;
        this.cqs = cqs;
        this.rColor = rColor;
        this.gColor = gColor;
        this.bColor = bColor;
        this.date = date;
    }
    @Generated(hash = 1723140213)
    public singleRecord() {
    }
    public int getS() {
        return this.s;
    }
    public void setS(int s) {
        this.s = s;
    }
    public int getLux() {
        return this.Lux;
    }
    public void setLux(int Lux) {
        this.Lux = Lux;
    }
    public int getCct() {
        return this.cct;
    }
    public void setCct(int cct) {
        this.cct = cct;
    }
    public int getCri() {
        return this.cri;
    }
    public void setCri(int cri) {
        this.cri = cri;
    }
    public float getX() {
        return this.x;
    }
    public void setX(float x) {
        this.x = x;
    }
    public float getY() {
        return this.y;
    }
    public void setY(float y) {
        this.y = y;
    }
    public float getUv_u() {
        return this.uv_u;
    }
    public void setUv_u(float uv_u) {
        this.uv_u = uv_u;
    }
    public float getUv_v() {
        return this.uv_v;
    }
    public void setUv_v(float uv_v) {
        this.uv_v = uv_v;
    }
    public float getDuv() {
        return this.Duv;
    }
    public void setDuv(float Duv) {
        this.Duv = Duv;
    }
    public int getDwave() {
        return this.Dwave;
    }
    public void setDwave(int Dwave) {
        this.Dwave = Dwave;
    }
    public int getPwave() {
        return this.Pwave;
    }
    public void setPwave(int Pwave) {
        this.Pwave = Pwave;
    }
    public float getColorRatio() {
        return this.colorRatio;
    }
    public void setColorRatio(float colorRatio) {
        this.colorRatio = colorRatio;
    }
    public int getRf() {
        return this.Rf;
    }
    public void setRf(int Rf) {
        this.Rf = Rf;
    }
    public int getRg() {
        return this.Rg;
    }
    public void setRg(int Rg) {
        this.Rg = Rg;
    }
    public float getV() {
        return this.V;
    }
    public void setV(float V) {
        this.V = V;
    }
    public String getQa() {
        return this.Qa;
    }
    public void setQa(String Qa) {
        this.Qa = Qa;
    }
    public int getGai() {
        return this.gai;
    }
    public void setGai(int gai) {
        this.gai = gai;
    }
    public String getCqs() {
        return this.cqs;
    }
    public void setCqs(String cqs) {
        this.cqs = cqs;
    }
    public int getRColor() {
        return this.rColor;
    }
    public void setRColor(int rColor) {
        this.rColor = rColor;
    }
    public int getGColor() {
        return this.gColor;
    }
    public void setGColor(int gColor) {
        this.gColor = gColor;
    }
    public int getBColor() {
        return this.bColor;
    }
    public void setBColor(int bColor) {
        this.bColor = bColor;
    }
    public String getDate() {
        return this.date;
    }
    public void setDate(String date) {
        this.date = date;
    }

}
