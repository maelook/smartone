package com.maelook.View.db;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.maelook.R;

/**
 * Created by Daiwilliam on 2016-12-28.
 */

public class SingleFragement extends Fragment {

    public SingleFragement(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.single_layout,container,false);
    }
}
