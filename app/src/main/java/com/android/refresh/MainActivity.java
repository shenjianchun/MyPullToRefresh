package com.android.refresh;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import my.android.extra.PullToRefreshBase;
import my.android.extra.PullToRefreshListView;


public class MainActivity extends ActionBarActivity {

    public static final int MESSAGE_DONE = 1;

    private PullToRefreshListView mRefreshView;
    private MainAdapter mAdapter;
    private MyHandler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mHandler = new MyHandler();
        mAdapter = new MainAdapter(this);

        mRefreshView = (PullToRefreshListView) findViewById(R.id.list);
        mRefreshView.getRefreshableView().setAdapter(mAdapter);
        mRefreshView.setPullRefreshEnabled(true);
        mRefreshView.setPullLoadEnabled(true);
        mRefreshView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                mHandler.sendEmptyMessageDelayed(MESSAGE_DONE, 2000);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                mHandler.sendEmptyMessageDelayed(MESSAGE_DONE, 2000);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
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
            }
        }
    }

}
