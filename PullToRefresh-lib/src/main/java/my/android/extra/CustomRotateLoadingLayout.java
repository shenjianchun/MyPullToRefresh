package my.android.extra;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;


/**
 * 这个类封装了下拉刷新的布局
 * 
 * @author Li Hong
 * @since 2013-7-30
 */
public class CustomRotateLoadingLayout extends LoadingLayout {

    /**Header的容器*/
    private RelativeLayout mHeaderContainer;
    /**箭头图片*/
    private ImageView mArrowImageView;
    /** 进度条 */
    private ProgressBar mProgressBar;

    /**
     * 构造方法
     *
     * @param context context
     */
    public CustomRotateLoadingLayout(Context context) {
        super(context);
        init(context);
    }

    /**
     * 构造方法
     *
     * @param context context
     * @param attrs attrs
     */
    public CustomRotateLoadingLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    /**
     * 初始化
     * 
     * @param context context
     */
    private void init(Context context) {
        mHeaderContainer = (RelativeLayout) findViewById(R.id.pull_to_refresh_header_content);
        mArrowImageView = (ImageView) findViewById(R.id.pull_to_refresh_header_arrow);
        mProgressBar = (ProgressBar) findViewById(R.id.pull_to_refresh_header_progressbar);

        mArrowImageView.setScaleType(ScaleType.CENTER);
    }
    
    @Override
    protected View createLoadingView(Context context, AttributeSet attrs) {
        View container = LayoutInflater.from(context).inflate(R.layout.custom_pull_to_refresh_header, null);
        return container;
    }


    @Override
    public int getContentSize() {
        if (null != mHeaderContainer) {
            return mHeaderContainer.getHeight();
        }
        
        return (int) (getResources().getDisplayMetrics().density * 60);
    }


    @Override
    protected void onStateChanged(State curState, State oldState) {
        mArrowImageView.setVisibility(View.VISIBLE);
        mProgressBar.setVisibility(View.INVISIBLE);

        super.onStateChanged(curState, oldState);
    }


    @Override
    protected void onRefreshing() {
        mArrowImageView.clearAnimation();
        mArrowImageView.setVisibility(View.INVISIBLE);
        mProgressBar.setVisibility(View.VISIBLE);
    }



}
