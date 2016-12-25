package com.maelook.View;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Path;
import android.media.AudioManager;
import android.media.SoundPool;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.maelook.R;
import com.maelook.Widget.maeChartFragment.LightSceneView;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Date;
import java.text.SimpleDateFormat;

import static com.maelook.app.maelookApp.appDocument;

public class LightScenceActivity extends Activity {
    private Path path = new Path();
    private boolean already = false;
    private LightSceneView fog;
    private SoundPool sp;//声明一个SoundPool
    private int music;//定义一个整型用load（）；来设置suondID
    private Button lightscene_back,btn_home_lightscene;
    private Uri imageUri; //图片路径
    private String filename; //图片名称


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.MyAppCompat);
        setContentView(R.layout.activity_light_scence);
        init();


        fog = (LightSceneView) findViewById(R.id.fog);

        fog.setBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.bg_rainbow));

        double[] data = new double[401];
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM+ File.separator+"data.txt"))));
            int i=0;
            String line ="";
            while((line = reader.readLine()) != null){
                data[i++] = Double.parseDouble(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        fog.setLightData(data);
        Button save = (Button) findViewById(R.id.save_lightscene);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Vibrator vibrator=(Vibrator)getSystemService(Service.VIBRATOR_SERVICE);
                vibrator.vibrate(new long[]{0,1000}, -1);

                sp.play(music, 1, 1, 0, 0, 1);
                Bitmap bitmap = fog.getBitmap();
                try {
                    //图片名称时间命名
                    SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd_HHmmss");
                    Date date = new Date(System.currentTimeMillis());
                    filename = format.format(date);
                    //存储至appDocument文件夹
                    FileOutputStream fos = new FileOutputStream(new File(appDocument+File.separator+"IMG_"+filename+".jpg"));
                    bitmap.compress(Bitmap.CompressFormat.JPEG,100,fos);
                    fos.flush();

                    fos.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });



    }
    public void init(){
        lightscene_back= (Button) findViewById(R.id.lightscene_back);
        btn_home_lightscene= (Button) findViewById(R.id.btn_home_lightscene);
        sp= new SoundPool(10, AudioManager.STREAM_SYSTEM, 5);//第一个参数为同时播放数据流的最大个数，第二数据流类型，第三为声音质量
        music = sp.load(this, R.raw.music, 1); //把你的声音素材放到res/raw里，第2个参数即为资源文件，第3个为音乐的优先级

    }



    public int blendColor(int fg, int bg) {
        int scr = Color.red(fg);
        int scg = Color.green(fg);
        int scb = Color.blue(fg);
        int sa = fg >>> 24;
        int dcr = Color.red(bg);
        int dcg = Color.green(bg);
        int dcb = Color.blue(bg);
        int color_r = dcr * (0xff - sa) / 0xff + scr * sa / 0xff;
        int color_g = dcg * (0xff - sa) / 0xff + scg * sa / 0xff;
        int color_b = dcb * (0xff - sa) / 0xff + scb * sa / 0xff;
        return ((color_r << 16) + (color_g << 8) + color_b) | (0xff000000);
    }

    public void setBitmap(Bitmap b) {



    }
    public void lightscene_back(View view){
        finish();
    }
    public void btn_home_lightscene(View view){
        Intent intent=new Intent(LightScenceActivity.this,FirstActivity.class);
        intent.putExtra("id",7);
        startActivity(intent);

    }
    public void share(View view){
        File f = new File(appDocument+"/1.jpg");
        Log.e("f","aaa"+f);
        System.out.print(f);
        Uri uri = Uri.fromFile(f);
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
        shareIntent.putExtra(Intent.EXTRA_SUBJECT,"分享");
        shareIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        shareIntent.setType("image/*");
        startActivity(Intent.createChooser(shareIntent,getTitle()));
    }

}
