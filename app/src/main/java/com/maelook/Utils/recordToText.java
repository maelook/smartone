package com.maelook.Utils;

import android.util.Log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.maelook.maelookApp.appDocument;

/**
 * Created by Andrew on 2016/10/25.
 */

/*
*   说明：
*   1.这是专门用来给数据库写文件信息到内存卡，保存为txt格式
*   2.由于数据库中固定以String形式存储到文件，所以仅仅给出String的操作方法，
*   若是以后的开发中，需要字节流相关的操作，可以直接通过String。getBytes()方法自己封装，或增加相关方法
*  3.写文件为一个独立的线程对象，后台独立运行
* */

public class recordToText {

    private static final String recordDocument = appDocument+File.separator+"record";
    private static final String TAG = "recordToText";

    public recordToText() {
    }

    //写字符串
    public void write(String s){
        writeThread writeThread = new writeThread(getAimFilePath(),s);

    }

    //新建当前时间的文件夹和存放的文件
    private File getAimFilePath() {
        String time = new SimpleDateFormat("HH:mm:ss").format(new Date());
        String day = new SimpleDateFormat("yyyy年MM月dd日").format(new Date());
        File parentPath = new File(recordDocument+File.separator+day);
        File aim = new File(parentPath+File.separator+time+"_record.txt");
        if (!parentPath.exists()){
            parentPath.mkdirs();
        }
        if (!aim.exists()){
            try {
                aim.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                Log.e(TAG,e.toString());
                return null;
            }
        }
        return aim;
    }

    /*
    * 写文件线程
    *
    * */
    private class writeThread extends Thread{
        private File aim;
        private String record;
        private byte[] recordBytes;

        private writeThread(File aim, String record) {
            this.aim = aim;
            this.record = record;
            this.recordBytes = new byte[1];
            this.run();                                     //创建后自动调用run方法开始执行书写操作
        }

        private writeThread(File aim, byte[] recordBytes) {
            this.aim = aim;
            this.recordBytes = recordBytes;
            this.record = "1";
            this.run();
        }

        @Override
        public void run() {
            super.run();

            if (this.recordBytes.length > 0 && this.record==null){
                this.record = new String(this.recordBytes,0,this.recordBytes.length);
            }

            try {
                FileOutputStream os = new FileOutputStream(aim);
                BufferedWriter br = new BufferedWriter(new OutputStreamWriter(os));
                br.write(record);

                br.close();
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
                Log.d(TAG,e.toString());
            }
        }

    }

}
