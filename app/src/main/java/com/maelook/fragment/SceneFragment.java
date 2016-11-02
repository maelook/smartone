package com.maelook.fragment;


import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.maelook.R;

/**
 * 场景
 */
public class SceneFragment extends Fragment {


    public SceneFragment() {
        //必要无参构造器
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View scene_layout=inflater.inflate(R.layout.activity_scene_fragment,container,false);
        return scene_layout;
    }


}
