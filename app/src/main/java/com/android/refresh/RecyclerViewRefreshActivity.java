package com.android.refresh;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import my.android.extra.PullToRefreshBase;
import my.android.extra.PullToRefreshRecyclerView;

/**
 * Created by 14110105 on 2015/5/25.
 */
public class RecyclerViewRefreshActivity extends ActionBarActivity {
    public static final int MESSAGE_DONE = 1;
    private PullToRefreshRecyclerView mRefreshView;
    private SimpleRecyclerViewAdapter mAdapter;
    private ArrayList<String> mData;
    private MyHandler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_recycler_view);
        mData = new ArrayList<>();

        for(int i = 1; i < 10; i++) {
            mData.add("Test data " + i + " ...." );
        }

        mHandler = new MyHandler();
        mAdapter = new SimpleRecyclerViewAdapter(this);

        mAdapter.setmData(mData);

        mRefreshView = (PullToRefreshRecyclerView) findViewById(R.id.list);
        mRefreshView.getRefreshableView().setAdapter(mAdapter);
        mRefreshView.setPullRefreshEnabled(true);
        mRefreshView.setScrollLoadEnabled(true);
//        mRefreshView.setPullLoadEnabled(false);
        mRefreshView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<RecyclerView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<RecyclerView> refreshView) {
                mHandler.sendEmptyMessageDelayed(MESSAGE_DONE, 2000);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<RecyclerView> refreshView) {
                mHandler.sendEmptyMessageDelayed(MESSAGE_DONE, 2000);
            }
        });

    }



    public final class MyHandler extends Handler {

        public MyHandler() {
            super();
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MESSAGE_DONE:
                    mRefreshView.onPullDownRefreshComplete();
                    mRefreshView.onPullUpRefreshComplete();


                    if(mData.size() >= 50) {
                        mRefreshView.setScrollLoadEnabled(false);
                        return;
                    }

                    for(int i = 1; i < 10; i++) {
                        mData.add("Test data " + i + " ...." );
                    }

                    mAdapter.notifyDataSetChanged();

            }
        }
    }
}
