package com.maelook.View;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import com.maelook.R;

/**
 * 测量
 */
public class MeasureActivity extends Activity {
    /*
      * 单次测量
      * */
    private ImageView Single_measure;
    /*
    * 多次测量
    * */
    private ImageView Many_measure;
    /*
    * 闪光测量
    * */
    private ImageView Flash_measure;
    /*
    * 连续测量
    * */
    private ImageView Continuous_measure;


    public MeasureActivity() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.MyAppCompat);
        setContentView(R.layout.activity_measure);
        Single_measure= (ImageView) findViewById(R.id.Single_measure);
        Many_measure= (ImageView) findViewById(R.id.Many_measure);
        Continuous_measure= (ImageView) findViewById(R.id.Continuous_measure);
        Flash_measure= (ImageView) findViewById(R.id.Flash_measure);

    }


    public void Single_measure(View view){
        Intent intent = new Intent();
        intent.setClass(MeasureActivity.this, SingleActivity.class);
        MeasureActivity.this.startActivity(intent);
        MeasureActivity.this.finish();
    }
    public void Many_measure(View view){
        Intent intent = new Intent();
        intent.setClass(MeasureActivity.this, ManyActivity.class);
        MeasureActivity.this.startActivity(intent);
        MeasureActivity.this.finish();
    }
    public void Continuous_measure(View view){
        Intent intent = new Intent();
        intent.setClass(MeasureActivity.this, ContinuousActivity.class);
        MeasureActivity.this.startActivity(intent);
        MeasureActivity.this.finish();
    }
    public void Flash_measure(View view){
        Intent intent = new Intent();
        intent.setClass(MeasureActivity.this, FlashActivity.class);
        MeasureActivity.this.startActivity(intent);
        MeasureActivity.this.finish();
    }



}
