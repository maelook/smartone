package com.maelook.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.maelook.Bean.singleResult;
import com.maelook.R;

import java.util.ArrayList;

/**
 * Created by Andrew on 2016/11/5.
 */

public class summuryPageListViewAdapter extends BaseAdapter {

    private ArrayList<singleResult> data;
    private Context context;

    public summuryPageListViewAdapter(ArrayList<singleResult> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public int getCount() {
        return data.size();
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
        TextView left = null;
        TextView right = null;
        if (convertView == null) {
            convertView = convertView.inflate(this.context,R.layout.summury_page_attr,null);
            left = (TextView) convertView.findViewById(R.id.summury_page_left);
            right = (TextView) convertView.findViewById(R.id.summury_page_right);
            convertView.setTag(new ViewHolder(left,right));
        }else{
            ViewHolder v = (ViewHolder) convertView.getTag();
            left = v.getLeft();
            right = v.getRight();
        }
        left.setText(this.data.get(position).getName());
        right.setText(this.data.get(position).getResult());

        return convertView;
    }


    class ViewHolder{
        private TextView left;
        private TextView right;

        public ViewHolder(TextView left, TextView right) {
            this.left = left;
            this.right = right;
        }

        public TextView getLeft() {
            return left;
        }

        public ViewHolder setLeft(TextView left) {
            this.left = left;
            return this;
        }

        public TextView getRight() {
            return right;
        }

        public ViewHolder setRight(TextView right) {
            this.right = right;
            return this;
        }
    }
}
