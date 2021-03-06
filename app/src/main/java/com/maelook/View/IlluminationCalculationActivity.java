package com.maelook.View;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

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
    private Button startcalculate;
    private double result;
    private int A;
    private int B;
    private int C;
    private int E;
    private int F;
    private int G;

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
        startcalculate=(Button) findViewById(R.id.startcalculate);

    }
    private ProgressDialog dialog;
    String str="输入有误";
    public  void StartCalculate(View view){
        try{
            A=Integer.parseInt(Area.getText().toString());
            B=Integer.parseInt(usecoefficient.getText().toString());
            C=Integer.parseInt(maintaincoefficient.getText().toString());
            E=Integer.parseInt(efficiency.getText().toString());
            F=Integer.parseInt(singlelight.getText().toString());
            G=Integer.parseInt(lightnum.getText().toString());

        }catch (Exception e){
            e.printStackTrace();
        }

        if ("".equals(Area.getText().toString().trim())){
            new AlertDialog.Builder(this)

                    .setMessage("照度面积不能为空")

                    .setPositiveButton("Ok", null)

                    .show();
            return;
        }else if ("".equals(usecoefficient.getText().toString().trim())){
            new AlertDialog.Builder(this)

                    .setMessage("灯具利用系数不能为空")

                    .setPositiveButton("Ok", null)

                    .show();
            return;
        }else if ("".equals(maintaincoefficient.getText().toString().trim())){
            new AlertDialog.Builder(this)

                    .setMessage("灯具维护系数不能为空")

                    .setPositiveButton("Ok", null)

                    .show();
            return;
        }else if ("".equals(efficiency.getText().toString().trim())){
            new AlertDialog.Builder(this)

                    .setMessage("灯具效率不能为空")

                    .setPositiveButton("Ok", null)

                    .show();
            return;
        }else if ("".equals(singlelight.getText().toString().trim())){
            new AlertDialog.Builder(this)

                    .setMessage("单灯功率不能为空")

                    .setPositiveButton("Ok", null)

                    .show();
            return;
        }else if ("".equals(lightnum.getText().toString().trim())){
            new AlertDialog.Builder(this)

                    .setMessage("灯具数量不能为空")

                    .setPositiveButton("Ok", null)

                    .show();
            return;
        }

        Intent intent=new Intent();
        Bundle data=new Bundle();
        result=(G*F*E*B*C)/A;

        Log.e("lalala","java:"+result);
        data.putDouble("key",result);
        intent.putExtras(data);
        intent.setClass(IlluminationCalculationActivity.this,IlluminationActivity.class);
        startActivity(intent);


        dialog= ProgressDialog.show(this,"","正在计算,请稍后...",true);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                    Toast.makeText(IlluminationCalculationActivity.this, "计算成功", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        },300);

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
