package com.maelook.View.db;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;

import com.maelook.R;
import com.maelook.View.ParameterSingleActivity;

public class OfflineDataActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offline_data);
    }
    public void Offline(View view){
        dialog();
    }
    protected void dialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setMessage("你正在执行获取离线数据的操作");
        builder.setTitle("提示");
        builder.setPositiveButton("确认",
                new android.content.DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        Intent intent=new Intent(OfflineDataActivity.this,SingDataActivity.class);
                        startActivity(intent);

                    }
                });
        builder.setNegativeButton("取消",
                new android.content.DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        builder.create().show();
    }
}
