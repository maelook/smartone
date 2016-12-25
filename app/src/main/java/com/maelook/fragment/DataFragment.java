package com.maelook.fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Vibrator;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.maelook.R;
import com.maelook.View.MyDataActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;

/**
 * 数据
 */
public class DataFragment extends Fragment {
    private ImageView data2,data3,data4;
    private Uri imageUri; //图片路径
    private String filename; //图片名称


    public DataFragment() {
        //必要无参构造器

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
      View data_layout=inflater.inflate(R.layout.activity_data,container,false);
        data2= (ImageView) data_layout.findViewById(R.id.data2);
        data3= (ImageView) data_layout.findViewById(R.id.data3);
        data4= (ImageView) data_layout.findViewById(R.id.data4);
        data2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(getActivity().getApplicationContext(), MyDataActivity.class);
                getActivity().startActivity(intent);
            }
        });
        data3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //图片名称 时间命名
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");
                java.sql.Date date = new java.sql.Date(System.currentTimeMillis());
                filename = format.format(date);
                //创建File对象用于存储拍照的图片 SD卡根目录
                //存储至DCIM文件夹
                File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
                File outputImage = new File(path,filename+".jpg");
                View view = v.getRootView();
                view.setDrawingCacheEnabled(true);
                view.buildDrawingCache();
                Bitmap bitmap = view.getDrawingCache();
                if(bitmap!= null){
                    try{
                        FileOutputStream out = new FileOutputStream(outputImage);
                        bitmap.compress(Bitmap.CompressFormat.PNG,100, out);
                        Toast.makeText(getActivity().getApplicationContext(), "截屏成功", Toast.LENGTH_SHORT).show();
                    }catch(Exception e) {
                        e.printStackTrace();
                    }
                }else{
                    Toast.makeText(getActivity().getApplicationContext(), "截屏失败", Toast.LENGTH_SHORT).show();
                }


            }
        });
        return data_layout;
    }





}
