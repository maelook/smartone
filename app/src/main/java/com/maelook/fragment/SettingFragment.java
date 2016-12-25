package com.maelook.fragment;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.maelook.R;
import com.maelook.View.UserFeedbackActivity;

/**
 * 设置
 */
public class SettingFragment extends Fragment {
    private View Bluetooth_click;
    private TextView ExitSystem;
    private  TextView userfeedback;


    public SettingFragment() {

    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
      View setting_layout=inflater.inflate(R.layout.activity_setting,container,false);
        Bluetooth_click=setting_layout.findViewById(R.id.Bluetooth_click);
        ExitSystem= (TextView) setting_layout.findViewById(R.id.ExitSystem);
        userfeedback= (TextView) setting_layout.findViewById(R.id.userfeedback);

        ExitSystem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog();
            }
        });

       /* Bluetooth_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(getActivity().getApplicationContext(), DeviceScanActivity.class);
                getActivity().startActivity(intent);
            }
        });*/
        userfeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (getActivity().getApplicationContext(),UserFeedbackActivity.class);
                startActivity(intent);
            }
        });
        return setting_layout;
    }

    protected void dialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity().getApplicationContext());
        builder.setMessage("确定要退出LightBox吗?");
        builder.setTitle("提示");
        builder.setPositiveButton("确认",
                new android.content.DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        getActivity().finish();
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
