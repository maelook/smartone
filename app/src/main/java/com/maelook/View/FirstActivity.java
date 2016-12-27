package com.maelook.View;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.maelook.R;
import com.maelook.Utils.GuideUtil;
import com.maelook.Utils.GuideViewUtil;
import com.maelook.fragment.DataFragment;
import com.maelook.fragment.MeasureFragment;
import com.maelook.fragment.SceneFragment;
import com.maelook.fragment.SettingFragment;
import java.util.Timer;
import java.util.TimerTask;
/*
*
* 应用程序的主activity，所有的Fragment都嵌入在这里
*
*
* */
public class FirstActivity extends FragmentActivity implements View.OnClickListener {
    private static final String TAG = "MainActivity";
    /*
    * 加载fragment
    * */
    public MeasureFragment measureFragment;

    public DataFragment dataFragment;
    public SceneFragment sceneFragment;


    public SettingFragment settingFragment;
    public FragmentTransaction transaction;
    private Button singleback,manyback,Continuousback,flashback;
    private Button mydata,databack,lightscene_back;
    /*
    * 加载layout
    *
     * */
    private View measure_layout,data_layout,scene_layout,setting_layout;
    /*
    * 加载ImageView
    *
    * */
    private ImageView measure_image,data_image,scene_image,setting_image;
    /*
    *
    * 加载TextView
    * */

    private TextView  measure_text,data_text,scene_text,setting_text;

    private GuideUtil guideUtil = null;
    private GuideViewUtil guideViewUtil;

    /*
    *
    * 对fragment进行管理
    * */
    public FragmentManager fragmentManager;
    public static  String curFragmentTag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.MyAppCompat);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_first);
       /* //加载新手引导页
        guideUtil = GuideUtil.getInstance();
        guideUtil.initGuide(this,R.drawable.newguide1);
        guideUtil.initGuide(this,R.drawable.newguide2);
        guideUtil.initGuide(this,R.drawable.newguide3);
        guideUtil.initGuide(this,R.drawable.newguide4);
        guideUtil.initGuide(this,R.drawable.newguide5);*/

        // 初始化布局元素
        initViews();
        //默认选中首页并且颜色有所改变
        ClickMeasureBtn();

        measure_image.setImageResource(R.mipmap.framenu2);
        measure_text.setTextColor(getResources().getColor(R.color.deep_blue));
        //获取SingleActivity的intent.putExtra("id",1);默认是0.
        int id = getIntent().getIntExtra("id", 0);
        MeasureFragment measurefragment = new MeasureFragment();
        DataFragment    datafragment=new DataFragment();
        SceneFragment   scenefragment=new SceneFragment();
        SettingFragment settingfragment=new SettingFragment();
        fragmentManager =getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
        if (id>1&&id<2) {

            transaction.replace(R.id.singleback,measurefragment);
            transaction.commit();
            measure_image.setImageResource(R.mipmap.framenu2);
            singleback.setVisibility(View.GONE);
        }
        else if (id>2&&id<3){

            transaction.replace(R.id.activity_many,measurefragment);
            transaction.commit();
            measure_image.setImageResource(R.mipmap.framenu2);
            manyback.setVisibility(View.GONE);

        }
        else if (id>3&&id<4){

            transaction.replace(R.id.activity_Continuous,measurefragment);
            transaction.commit();
            measure_image.setImageResource(R.mipmap.framenu2);
            Continuousback.setVisibility(View.GONE);
        }
        else if (id>4&&id<5){
            transaction.replace(R.id.activity_flash,measurefragment);
            transaction.commit();
            measure_image.setImageResource(R.mipmap.framenu2);
            flashback.setVisibility(View.GONE);
        }
        else if (id<6&&id>5){
            transaction.replace(R.id.Mydata,datafragment);
            transaction.commit();
            data_image.setImageResource(R.mipmap.framenu4);
            mydata.setVisibility(View.GONE);
        }
        /*else if (id>6&&id<7){
            transaction.replace(R.id.activity_data_map,scenefragment);
            transaction.commit();
            scene_image.setImageResource(R.mipmap.framenu6);
            databack.setVisibility(View.GONE);
        }*/
        else if (id>7&&id<8){
            transaction.replace(R.id.activity_lightscene,scenefragment);
            transaction.commit();
            scene_image.setImageResource(R.mipmap.framenu6);
            lightscene_back.setVisibility(View.GONE);
        }
        else if (id>8&&id<9){
            transaction.replace(R.id.activity_lightscene,scenefragment);
            transaction.commit();
            scene_image.setImageResource(R.mipmap.framenu8);
            lightscene_back.setVisibility(View.GONE);
        }


    }



    /**
     *
     * 初始化组件
     */
    private void initViews(){
        //  获取layout
        singleback = (Button) findViewById(R.id.singleback);
        manyback= (Button) findViewById(R.id.manyback);
        Continuousback= (Button) findViewById(R.id.Continuousback);
        flashback= (Button) findViewById(R.id.flashback);
        mydata= (Button) findViewById(R.id.mydata);
        databack= (Button) findViewById(R.id.data_back);
        lightscene_back= (Button) findViewById(R.id.lightscene_back);

        measure_layout=findViewById(R.id.measure_layout);
        data_layout=findViewById(R.id.data_layout);
        scene_layout=findViewById(R.id.scene_layout);
        setting_layout=findViewById(R.id.setting_layout);

        //获取ImageView
        measure_image= (ImageView) findViewById(R.id.measure_image);
        data_image= (ImageView) findViewById(R.id.data_image);
        scene_image= (ImageView) findViewById(R.id.scene_image);
        setting_image= (ImageView) findViewById(R.id.setting_image);
        //获取TextView
        measure_text= (TextView) findViewById(R.id.measure_text);
        data_text= (TextView) findViewById(R.id.data_text);
        scene_text= (TextView) findViewById(R.id.scene_text);
        setting_text= (TextView) findViewById(R.id.setting_text);
        //设置点击事件
        measure_layout.setOnClickListener(this);
        data_layout.setOnClickListener(this);
        scene_layout.setOnClickListener(this);
        setting_layout.setOnClickListener(this);


    }
    @Override
    // 当发生点击时，先清除状态，这里的状态指的是布局里面的图片和文字
    public void onClick(View v) {
        clearState();
        switch (v.getId()){
            case R.id.measure_layout:
                measure_image.setImageResource(R.mipmap.framenu2);
                measure_text.setTextColor(getResources().getColor(R.color.deep_blue));
                ClickMeasureBtn();
                break;

            case R.id.data_layout:
                guideViewUtil=new GuideViewUtil(this, R.drawable.newguide3);
               /* guideUtil.initGuide(FirstActivity.this,R.drawable.newguide3);*/
                data_image.setImageResource(R.mipmap.framenu4);
                data_text.setTextColor(getResources().getColor(R.color.deep_blue));
                ClickDataBtn();
                break;

            case R.id.scene_layout:
                guideViewUtil=new GuideViewUtil(this, R.drawable.newguide4);
               /* guideUtil.initGuide(FirstActivity.this,R.drawable.newguide4);*/
                scene_image.setImageResource(R.mipmap.framenu6);
                scene_text.setTextColor(getResources().getColor(R.color.deep_blue));
                ClickSceneBtn();
                break;

            case R.id.setting_layout:
                setting_image.setImageResource(R.mipmap.framenu8);
                setting_text.setTextColor(getResources().getColor(R.color.deep_blue));
                ClickSettingBtn();
                break;
            default:
                break;
        }

    }
    public void clearState(){
        // 未选中时的图片
        measure_image.setImageResource(R.mipmap.framenu1);
        data_image.setImageResource(R.mipmap.framenu3);
        scene_image.setImageResource(R.mipmap.framenu5);
        setting_image.setImageResource(R.mipmap.framenu7);

        // 未选中时字体颜色
        measure_text.setTextColor(getResources().getColor(R.color.black));
        data_text.setTextColor(getResources().getColor(R.color.black));
        scene_text.setTextColor(getResources().getColor(R.color.black));
        setting_text.setTextColor(getResources().getColor(R.color.black));

    }
    /*
    *
    * 点击了“测量”按钮
    * */
    private  void ClickMeasureBtn(){
        // 实例化Fragment页面
        measureFragment = new MeasureFragment();
        // 得到Fragment事务管理器
        android.support.v4.app.FragmentTransaction fragmentTransaction = this
                .getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content, measureFragment);
        // 事务管理提交
        fragmentTransaction.commit();
        measure_layout.setSelected(true);
        measure_image.setSelected(true);

        data_layout.setSelected(false);
        data_image.setSelected(false);

        scene_layout.setSelected(false);
        setting_image.setSelected(false);

        setting_layout.setSelected(false);
        setting_image.setSelected(false);
    }
    /*
    * 点击“数据”按钮
    *
    * */
    private  void ClickDataBtn(){
        // 实例化Fragment页面
        dataFragment = new DataFragment();
        // 得到Fragment事务管理器
        android.support.v4.app.FragmentTransaction fragmentTransaction = this
                .getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content, dataFragment);
        // 事务管理提交
        fragmentTransaction.commit();
        data_layout.setSelected(true);
        data_image.setSelected(true);

        measure_layout.setSelected(false);
        measure_image.setSelected(false);

        scene_layout.setSelected(false);
        setting_image.setSelected(false);

        setting_layout.setSelected(false);
        setting_image.setSelected(false);

    }
    public void ClickSceneBtn(){
        // 实例化Fragment页面
        sceneFragment = new SceneFragment();
        // 得到Fragment事务管理器
        android.support.v4.app.FragmentTransaction fragmentTransaction = this
                .getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content, sceneFragment);
        // 事务管理提交
        fragmentTransaction.commit();

        scene_layout.setSelected(true);
        scene_image.setSelected(true);

        measure_layout.setSelected(false);
        measure_image.setSelected(false);

        data_layout.setSelected(false);
        data_image.setSelected(false);

        setting_layout.setSelected(false);
        setting_image.setSelected(false);

    }
    private  void ClickSettingBtn(){
        // 实例化Fragment页面
        settingFragment = new SettingFragment();
        // 得到Fragment事务管理器
        android.support.v4.app.FragmentTransaction fragmentTransaction = this
                .getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content, settingFragment);
        // 事务管理提交
        fragmentTransaction.commit();
        measure_layout.setSelected(false);
        measure_image.setSelected(false);

        data_layout.setSelected(false);
        data_image.setSelected(false);

        scene_layout.setSelected(false);
        setting_image.setSelected(false);

        setting_layout.setSelected(true);
        setting_image.setSelected(true);
    }
    private static Boolean isExit = false;
    private static Boolean hasTask = false;
    Timer tExit = new Timer();
    TimerTask task = new TimerTask() {
        @Override

        public void run() {
            isExit = true;
            hasTask = true;
        }
    };
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if(isExit == false ) {
                isExit = true;
                Toast.makeText(FirstActivity.this, "再按一次退出LightBox！", Toast.LENGTH_LONG).show();
                if(!hasTask) {
                    tExit.schedule(task, 2000);
                }
            } else {
                finish();
                System.exit(0);
            }
        }
        return false;
    }


}