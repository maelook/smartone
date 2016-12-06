package com.maelook.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import com.maelook.R;

import java.util.ArrayList;

/**
 * Created by Andrew on 2016/11/20.
 */

public class dataComparedAdapter extends BaseAdapter implements View.OnClickListener {

    private ArrayList<String> document;
    private Context context;

    public dataComparedAdapter(Context context) {
        this.context = context;
        document = new ArrayList<>();
        document.add("colorBar");
        document.add("cqs");
        document.add("spactral");
        document.add("summuryPage");
    }

    @Override
    public int getCount() {
        return document.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
//        View v = convertView.inflate(this.context, R.layout.summury_page_attr,null);
        Button b = new Button(this.context);
        b.setText(this.document.get(position));
        b.setId(position);
        b.setOnClickListener(this);
        return b;

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case 1:
                Activity a1 = new Activity();
                a1.setContentView(R.layout.colorrenderinglayout);

                break;
            case 2:

                break;
            case 3:

                break;

            case 4:

                break;


        }
    }
}
