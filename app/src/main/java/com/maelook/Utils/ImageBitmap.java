package com.maelook.Utils;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.util.Log;

import com.maelook.R;
import com.maelook.Widget.maeChartFragment.DataMap;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import static com.maelook.app.maelookApp.appDocument;

/**
 * Created by Daiwilliam on 2017-1-15.
 */

public class ImageBitmap extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_map);

        DataMap dataMap = (DataMap) findViewById(R.id.show);
        String path = appDocument + File.separator+"img.jpg";
        dataMap.setBg_bitmap(transImage(path,400,400,50));
        Log.e("datamap","img size after:"+transImage(path,400,400,50).getByteCount());
        byte[] _16 = {10,11,12,13,14,15,16};
        for (byte b:_16To10(_16)){
            Log.e("datamap","data:"+b);
        }

    }




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

    public byte[] _16To10(byte[] array){
        byte[] res = new byte[array.length];
        for (int i=0;i<array.length;i++){
            res[i] = (byte) Integer.parseInt(array[i]+"",16);
        }
        return res;
    }

}
