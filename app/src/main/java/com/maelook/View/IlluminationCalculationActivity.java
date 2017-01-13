package com.maelook.View;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Spinner;

import com.maelook.R;

/*
*
* 照度计算
* 计算公式（lux）=（G*W*E*B*C）/A
*
*
* */

public class IlluminationCalculationActivity extends Activity {
    private EditText Area;
    private EditText usecoefficient;
    private EditText maintaincoefficient;
    private Spinner typelight;
    private EditText efficiency;
    private EditText singlelight;
    private EditText lightnum;
    private View startcalculate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_illumination_calculation);
        initView();
    }
    /*
    *
    * 初始化组件
    *
    * */
    public void initView(){
        Area= (EditText) findViewById(R.id.Area);
        usecoefficient= (EditText) findViewById(R.id.usecoefficient);
        maintaincoefficient= (EditText) findViewById(R.id.usecoefficient);
        typelight= (Spinner) findViewById(R.id.typelight);
        efficiency= (EditText) findViewById(R.id.efficiency);
        singlelight= (EditText) findViewById(R.id.singlelight);
        lightnum= (EditText) findViewById(R.id.lightnum);
        startcalculate=findViewById(R.id.startcalculate);

    }
    public  void StartCalculate(View view){
        String A=Area.getText().toString().trim();
        String B=usecoefficient.getText().toString().trim();
        String C=maintaincoefficient.getText().toString().trim();
        String E=efficiency.getText().toString().trim();
        String F=singlelight.getText().toString().trim();
        String G=lightnum.getText().toString().trim();
        Intent intent=new Intent();
        intent.putExtra("NUM",A);
        intent.setClass(IlluminationCalculationActivity.this,IlluminationActivity.class);
        startActivity(intent);

    }
    public void illcaback(View view){
        finish();
    }
    public void illca_home(View view){
        Intent intent=new Intent(IlluminationCalculationActivity.this,FirstActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (isShouldHideInput(v, ev)) {

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
            return super.dispatchTouchEvent(ev);
        }
        // 必不可少，否则所有的组件都不会有TouchEvent了
        if (getWindow().superDispatchTouchEvent(ev)) {
            return true;
        }
        return onTouchEvent(ev);
    }
    public  boolean isShouldHideInput(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] leftTop = { 0, 0 };
            //获取输入框当前的location位置
            v.getLocationInWindow(leftTop);
            int left = leftTop[0];
            int top = leftTop[1];
            int bottom = top + v.getHeight();
            int right = left + v.getWidth();
            if (event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom) {
                // 点击的是输入框区域，保留点击EditText的事件
                return false;
            } else {
                return true;
            }
        }
        return false;
    }

}
