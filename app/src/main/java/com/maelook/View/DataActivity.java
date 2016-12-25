package com.maelook.View;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import com.maelook.R;
import com.maelook.View.TestData.MainActivity;


/**
 * 数据
 */
public class DataActivity extends Activity {
    private ImageView data2;


    public DataActivity() {
        //必要无参构造器

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.MyAppCompat);
        setContentView(R.layout.activity_data);
        data2= (ImageView) findViewById(R.id.data2);
        data2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(DataActivity.this, MainActivity.class);
                DataActivity.this.startActivity(intent);
                DataActivity.this.finish();
            }
        });

    }





}
