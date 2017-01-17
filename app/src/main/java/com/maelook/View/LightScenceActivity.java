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
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

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
    private String filename; //图片名称
    String ImageURL;
    Bitmap myBitmap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_light_scence);
        init();

        fog = (LightSceneView) findViewById(R.id.fog);
        //图片名称 时间命名
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd_HHmmss");
        Date date = new Date(System.currentTimeMillis());
        filename = format.format(date);
        //存储至appDocument文件夹
        ImageURL=appDocument+File.separator+"IMG_"+filename+".jpg";

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
                    FileOutputStream fos = new FileOutputStream(new File(ImageURL));
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
        sp= new SoundPool(10, AudioManager.STREAM_SYSTEM, 5);//第一个参数为同时播放数据流的最大个数，第二数据流类型，第三为声音质量
        music = sp.load(this, R.raw.music, 1); //把你的声音素材放到res/raw里，第2个参数即为资源文件，第3个为音乐的优先级

    }
    public void TakeAPic(View view){

        //将File对象转换为Uri并启动照相程序
        Intent cameraIntent = new Intent(
                MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT,
                Uri.fromFile(new File(ImageURL)));
        startActivityForResult(cameraIntent, 0);
        //拍完照startActivityForResult() 结果返回onActivityResult()函数
    }

    /**
     * 因为两种方式都用到了startActivityForResult方法
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 0){
            Log.d("requestCode", "Need 0");
            if(resultCode == RESULT_OK){
                Log.d("resultCode", "OK!!!" + ImageURL);
                myBitmap = BitmapFactory.decodeFile(ImageURL);
                Toast.makeText(LightScenceActivity.this, "图片位置---"+ImageURL.toString(), Toast.LENGTH_SHORT).show();
                fog.setBitmap(myBitmap);
                /*fog.setImageBitmap(myBitmap);*/
            }else{
                Log.d("resultCode", "" + resultCode);
            }
        }else{
            Log.d("requestCode", "Not Need");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
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
