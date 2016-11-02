package com.maelook.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.maelook.R;

/**
 * 设置
 */
public class SettingFragment extends Fragment {


    public SettingFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
      View setting_layout=inflater.inflate(R.layout.activity_setting_fragment,container,false);
        return setting_layout;
    }


}
