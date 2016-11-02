package com.maelook.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.maelook.R;


/**
 * 数据
 */
public class DataFragment extends Fragment {


    public DataFragment() {
        //必要无参构造器

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
      View data_layout=inflater.inflate(R.layout.activity_data_fragment,container,false);
        return data_layout;
    }


}
