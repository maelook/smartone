package com.maelook.Widget.maeChartFragment.testFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.maelook.R;

/**
 * Created by Andrew on 2016/11/8.
 */

public class cqs extends android.support.v4.app.Fragment {

    public cqs() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.individual_cqs_layout,null);
    }
}
