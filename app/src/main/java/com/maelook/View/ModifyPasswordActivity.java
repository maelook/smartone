package com.maelook.View;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.maelook.R;

public class ModifyPasswordActivity extends Activity implements TextWatcher, View.OnClickListener {
    private EditText old_password;
    private EditText new_password;
    private Button connect_bt;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_password);
      initView();

  }
    public void initView(){
        old_password= (EditText) findViewById(R.id.old_password);
        new_password= (EditText) findViewById(R.id.new_password);
        connect_bt= (Button) findViewById(R.id.connect_bt);
        connect_bt.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.old_password:
                checkNull();
                String oldpassword=old_password.getText().toString().trim();
                String newpassword=new_password.getText().toString().trim();
                if (TextUtils.isEmpty(oldpassword)){
                    Toast.makeText(this, "旧密码不能为空", Toast.LENGTH_SHORT).show();
                }else if (TextUtils.isEmpty(newpassword)){
                    Toast.makeText(this, "新密码不能为空", Toast.LENGTH_SHORT).show();
                }if (old_password.getText().toString().equals(new_password.getText().toString())){}
                     new_password.setText("");
                     requstFocus(new_password, "两次密码不一致", Color.RED,true);
                return;
        }dialog=ProgressDialog.show(this,"","修改中,请稍后...",true);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(!"1234".equals(old_password.getText().toString())){
                    old_password.setText("");
                    requstFocus(old_password, "原密码错误", Color.RED, true);
                }else{
                    Toast.makeText(ModifyPasswordActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                }
                dialog.dismiss();
            }
        },3000);
    }
    private boolean checkNull() {
        if (TextUtils.isEmpty(old_password.getText().toString())) {
            requstFocus(old_password, null, Color.GRAY,true);
            return true;

        }
        if(old_password.getText().toString().length()==4){
            old_password.setText("");
            requstFocus(old_password, "密码长度必须小于5位", Color.RED,true);
            return true;
        }
        if(TextUtils.isEmpty(new_password.getText().toString())){
            requstFocus(new_password, null, Color.GRAY,true);
            return true;
        }
        if(new_password.getText().toString().length()==4){
            new_password.setText("");
            requstFocus(new_password, "密码长度必须小于5位", Color.RED,true);
            return true;
        }
        if(TextUtils.isEmpty(new_password.getText().toString())){
            requstFocus(new_password,null, Color.GRAY,true);
            return true;
        }
        return false;
    }


    public void requstFocus(EditText et,String hint,int hintColor,boolean needFocus){
        if(hint==null){
            hint="四位密码";
        }
        et.setHint(hint);
        et.setHintTextColor(hintColor);
        if(needFocus){
            et.requestFocus();
        }
    }
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }
    private ProgressDialog dialog;
    @Override
    public void afterTextChanged(Editable s) {

    }
    public void ResetPassword(View view){
        Toast.makeText(ModifyPasswordActivity.this,"重置密码已成功，请重新连接蓝牙",Toast.LENGTH_SHORT).show();
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
