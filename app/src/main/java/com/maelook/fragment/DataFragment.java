package com.maelook.fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.maelook.R;
import com.maelook.View.DataCalculateActivity;
import com.maelook.View.DataComparisonActivity;
import com.maelook.View.IlluminationCalculationActivity;
import com.maelook.View.db.MyDatabaseActivity;

/**
 * 数据
 */
public class DataFragment extends Fragment {
    private ImageView data2,data3,data4,data5;
    public DataFragment() {
        //必要无参构造器

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
      View data_layout=inflater.inflate(R.layout.activity_data,container,false);
        data2= (ImageView) data_layout.findViewById(R.id.data2);
        data3= (ImageView) data_layout.findViewById(R.id.data3);
        data4= (ImageView) data_layout.findViewById(R.id.data4);
        data5= (ImageView) data_layout.findViewById(R.id.data5);
        data2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(getActivity().getApplicationContext(), MyDatabaseActivity.class);
                getActivity().startActivity(intent);
            }
        });

        data3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(getActivity().getApplicationContext(), IlluminationCalculationActivity.class);
                getActivity().startActivity(intent);
            }
        });
        data4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(getActivity().getApplicationContext(), DataComparisonActivity.class);
                getActivity().startActivity(intent);
            }
        });
        data5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(getActivity().getApplicationContext(), DataCalculateActivity.class);
                getActivity().startActivity(intent);
            }
        });
        return data_layout;
    }





}
