package com.maelook.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.maelook.R;
import com.maelook.View.dataBaseOperationActivity;
import com.maelook.View.dataComparedActivity;
import com.maelook.View.dataCountActivity;


/**
 * 数据
 */
public class DataFragment extends android.support.v4.app.Fragment implements View.OnClickListener {

    private Button data2;
    private Button data3;
    private Button data4;

    public DataFragment() {
        //必要无参构造器
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View data_layout = inflater.inflate(R.layout.activity_data_fragment,container,false);
        data2 = (Button) data_layout.findViewById(R.id.data2);
        data3 = (Button) data_layout.findViewById(R.id.data3);
        data4 = (Button) data_layout.findViewById(R.id.data4);
        data2.setOnClickListener(this);
        data3.setOnClickListener(this);
        data4.setOnClickListener(this);

        return data_layout;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.data2:
                Intent i1 = new Intent(DataFragment.this.getContext(),dataBaseOperationActivity.class);
                startActivity(i1);
                break;
            case R.id.data3:
                Intent i2 = new Intent(DataFragment.this.getContext(),dataComparedActivity.class);
                startActivity(i2);
                break;
            case R.id.data4:
                Intent i3 = new Intent(DataFragment.this.getContext(),dataCountActivity.class);
                startActivity(i3);
                break;

        }
    }
}
