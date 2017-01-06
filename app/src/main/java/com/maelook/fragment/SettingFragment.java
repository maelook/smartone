package com.maelook.fragment;
import android.bluetooth.BluetoothAdapter;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.maelook.R;
import com.maelook.View.AppHelpActivity;
import com.maelook.View.ModifyPasswordActivity;


/**
 * 设置
 */
public class SettingFragment extends Fragment{
    private View Bluetooth_click;
    private TextView ExitSystem;
    private  TextView userfeedback;
    private  TextView modify_password;
    private  TextView Apphelp;
    private ModifyPasswordActivity modifyPasswordActivity;


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
        modify_password= (TextView) setting_layout.findViewById(R.id.modify_password);
        Apphelp= (TextView) setting_layout.findViewById(R.id.Apphelp);
        Apphelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity().getApplicationContext(), AppHelpActivity.class);
                startActivity(intent);
            }
        });
        /*
        * 修改密码
        * */
        modify_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent (getActivity().getApplicationContext(),ModifyPasswordActivity.class);
                startActivity(intent);


            }
        });

        //退出系统
        ExitSystem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog();
            }
        });

        Bluetooth_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_BLUETOOTH_SETTINGS);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                try {
                    startActivity(intent);
                } catch (ActivityNotFoundException ex) {
                    ex.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        userfeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Uri uri = Uri.parse("mailto:dgc_william@163.com");
              /*  String[] email = {"dgc_william@163.com"};*/
                Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
               /* intent.putExtra(Intent.EXTRA_CC, email); // 抄送人*/
                intent.putExtra(Intent.EXTRA_SUBJECT, "  "); // 主题
                intent.putExtra(Intent.EXTRA_TEXT, "   "); // 正文
                startActivity(intent);

            }
        });
        return setting_layout;
    }
    public static boolean isBluetoothEnable() {
        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        return bluetoothAdapter.isEnabled();
    }


    protected void dialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
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
