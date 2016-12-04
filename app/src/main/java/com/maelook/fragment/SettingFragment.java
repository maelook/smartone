package com.maelook.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.maelook.R;
import com.maelook.View.SingleActivity;
import com.maelook.bluetooth.DeviceScanActivity;

/**
 * 设置
 */
public class SettingFragment extends Fragment {
    private View Bluetooth_click;


    public SettingFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
      View setting_layout=inflater.inflate(R.layout.activity_setting_fragment,container,false);
        Bluetooth_click=setting_layout.findViewById(R.id.Bluetooth_click);

        Bluetooth_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(getActivity().getApplicationContext(), DeviceScanActivity.class);
                getActivity().startActivity(intent);
            }
        });
        return setting_layout;
    }


}
