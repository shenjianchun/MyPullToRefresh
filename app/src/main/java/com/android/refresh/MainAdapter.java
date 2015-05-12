package com.android.refresh;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by 14110105 on 2015/5/12.
 */
public class MainAdapter extends BaseAdapter {

    private LayoutInflater mInflater;

    public static ArrayList<String> mData = new ArrayList<>();

    public MainAdapter(Context context) {
        mInflater = LayoutInflater.from(context);

        for(int i = 0; i < 16; i++) {
            mData.add("Test data ....");
        }

    }

    @Override
    public int getCount() {
        return mData != null ? mData.size() : 0;
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

        ViewHolder holder;
        if (convertView == null) {

            convertView = mInflater.inflate(android.R.layout.simple_list_item_1, null, false);
            holder = new ViewHolder();
            holder.textView = (TextView) convertView.findViewById(android.R.id.text1);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.textView.setText(mData.get(position));

        return convertView;
    }

    public static class ViewHolder{
        public TextView textView;
    }

}
