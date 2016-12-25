package com.maelook.fragment;


import android.content.Intent;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.maelook.R;
import com.maelook.View.DataMapActivity;
import com.maelook.View.LightScenceActivity;

/**
 * 场景
 */
public class SceneFragment extends Fragment {
    private ImageView scene2,scene3,scene4;


    public SceneFragment() {
        //必要无参构造器
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View scene_layout=inflater.inflate(R.layout.activity_scene,container,false);
        scene2= (ImageView) scene_layout.findViewById(R.id.scene2);
        scene3=(ImageView) scene_layout.findViewById(R.id.scene3);
        scene4=(ImageView) scene_layout.findViewById(R.id.scene4);

        scene3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (getActivity().getApplicationContext(),DataMapActivity.class);
                startActivity(intent);
            }
        });
        scene4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (getActivity().getApplicationContext(),LightScenceActivity.class);
                startActivity(intent);
            }
        });


        return scene_layout;
    }


}
