package com.maelook.View;
import android.app.Activity;
import android.os.Bundle;
import com.maelook.R;

/**
 * 场景
 */
public class SceneActivity extends Activity {


    public SceneActivity() {
        //必要无参构造器
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.MyAppCompat);
        setContentView(R.layout.activity_scene);

    }


}
