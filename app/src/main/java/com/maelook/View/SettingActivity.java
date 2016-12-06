package com.maelook.View;
import android.app.Activity;
import android.os.Bundle;
import com.maelook.R;

/**
 * 设置
 */
public class SettingActivity extends Activity {


    public SettingActivity() {

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.MyAppCompat);
        setContentView(R.layout.activity_setting);

    }



}
