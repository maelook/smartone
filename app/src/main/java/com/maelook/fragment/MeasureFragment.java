package com.maelook.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.maelook.R;

/**
 * 测量
 */
public class MeasureFragment extends Fragment {


    public MeasureFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View measure_layout=inflater.inflate(R.layout.activity_measure_fragment,container,false);
        return measure_layout;
    }

}
