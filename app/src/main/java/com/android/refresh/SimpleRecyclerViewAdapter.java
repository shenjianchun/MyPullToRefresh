package com.android.refresh;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import my.android.extra.RecyclerViewAdapter;

/**
 * Created by 14110105 on 2015/5/25.
 */
public class SimpleRecyclerViewAdapter extends RecyclerViewAdapter {

    public static final String TAG = SimpleRecyclerViewAdapter.class.getSimpleName();

    private LayoutInflater mInflater;

    public  ArrayList<String> mData = new ArrayList<>();

    public SimpleRecyclerViewAdapter(Context context) {

        mInflater = LayoutInflater.from(context);

        for(int i = 1; i < 16; i++) {
            mData.add("Test data " + i + " ...." );
        }
    }

    public void setmData(ArrayList<String> data ) {
        mData = data;
    }

    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent) {
        View view = mInflater.inflate(android.R.layout.simple_list_item_1, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapter.ViewHolder holder, int position) {
        if (position < getItemCount() - 1) {/*position从0开始，ItemCount从1开始，所以要-1*/
            Log.d(TAG, "onBindViewHolder(), position=" + position);
            ((ViewHolder) holder).textView.setText(mData.get(position));
        }
    }

    @Override
    public int getAdapterItemCount() {
        return mData != null ? mData.size() : 0;
    }


    public static class ViewHolder extends RecyclerViewAdapter.ViewHolder{
        public TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(android.R.id.text1);
        }
    }
}
