package com.maelook.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.maelook.R;

import java.util.List;

/**
 *listview适配器
 */
public class MyListViewAdapter extends BaseAdapter {
    private List<String> mData;
    private Context mContext;
    private LayoutInflater mInflater;

    public MyListViewAdapter(Context context, List<String> data) {
        this.mContext = context;
        this.mData = data;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.list_item, null);
            holder.mImageView = (ImageView) convertView.findViewById(R.id.button1);
            holder.left = (TextView) convertView.findViewById(R.id.summury_page_left);
            holder.cb= (CheckBox) convertView.findViewById(R.id.item_cb);
            holder.right= (TextView) convertView.findViewById(R.id.summury_page_right);

            convertView.setTag(holder);

        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.left.setText("2016-12-8:" + mData.get(position));
        holder.right.setText("data1:" + mData.get(position));
        return convertView;
    }

    class ViewHolder {
        ImageView mImageView;
        TextView left;
        TextView right;
        CheckBox cb;
    }
}
