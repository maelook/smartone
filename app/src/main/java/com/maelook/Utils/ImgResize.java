package com.maelook.Utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.util.Log;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Created by Daiwilliam on 2017-1-15.
 */

public class ImgResize {
    public Bitmap transImage(String fromFile, int width, int height, int quality) {
        try {
            FileInputStream inputStream = new FileInputStream(fromFile);
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            Log.e("datamap","img size before:"+bitmap.getByteCount());
            int bitmapWidth = bitmap.getWidth();
            int bitmapHeight = bitmap.getHeight();
            // 缩放图片的尺寸
            float scaleWidth = (float) width / bitmapWidth;
            float scaleHeight = (float) height / bitmapHeight;
            Matrix matrix = new Matrix();
            matrix.postScale(scaleWidth, scaleHeight);
            // 产生缩放后的Bitmap对象
            Bitmap resizeBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmapWidth, bitmapHeight, matrix, false);
            if(!bitmap.isRecycled()){
                bitmap.recycle();//记得释放资源，否则会内存溢出
            }
            return resizeBitmap;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}