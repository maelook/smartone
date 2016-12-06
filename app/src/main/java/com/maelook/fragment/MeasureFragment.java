package com.maelook.fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.maelook.R;
import com.maelook.View.ContinuousActivity;
import com.maelook.View.FlashActivity;
import com.maelook.View.ManyActivity;

/**
 * 测量
 */
public class MeasureFragment extends Fragment {
    /*
      * 单次测量
      * */
    private ImageView Single_measure;
    /*
    * 多次测量
    * */
    private ImageView Many_measure;
    /*
    * 闪光测量
    * */
    private ImageView Flash_measure;
    /*
    * 连续测量
    * */
    private ImageView Continuous_measure;


    public MeasureFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view=inflater.inflate(R.layout.activity_measure_fragment2,container,false);
        //初始化控件
        Single_measure= (ImageView) view.findViewById(R.id.Single_measure);
        Many_measure= (ImageView) view.findViewById(R.id.Many_measure);
        Continuous_measure= (ImageView) view.findViewById(R.id.Continuous_measure);
        Flash_measure= (ImageView) view.findViewById(R.id.Flash_measure);

        /*
        * 单次测量
        * */
        Single_measure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(getActivity().getApplicationContext(), SingleActivity.class);
                getActivity().startActivity(intent);
            }
        });
        /*
        * 多次测量
        * */
        Many_measure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(getActivity().getApplicationContext(), ManyActivity.class);
                getActivity().startActivity(intent);
            }
        });
        /*
        * 连续测量
        * */
        Continuous_measure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(getActivity().getApplicationContext(), ContinuousActivity.class);
                getActivity().startActivity(intent);
            }
        });
        /*
        * 闪光测量
        * */
        Flash_measure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(getActivity().getApplicationContext(), FlashActivity.class);
                getActivity().startActivity(intent);
            }
        });
        return view;
    }

}
