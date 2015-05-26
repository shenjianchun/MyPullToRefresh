package my.android.extra;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by 14110105 on 2015/5/25.
 */
public abstract class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    protected View customLoadMoreView = null;

    public static ArrayList<String> mData = new ArrayList<>();


    public void addFooterView(View footerView) {
        customLoadMoreView = footerView;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == VIEW_TYPES.FOOTER) {
            ViewHolder viewHolder = new ViewHolder(customLoadMoreView);
            if (getAdapterItemCount() == 0) {
                viewHolder.itemView.setVisibility(View.GONE);
            }
            return viewHolder;
        }

        return onCreateViewHolder(parent);
    }

    public abstract ViewHolder onCreateViewHolder(ViewGroup parent);

    @Override
    public int getItemCount() {
        int headerOrFooter = 0;
        if (customLoadMoreView != null) headerOrFooter++;
        return getAdapterItemCount() + headerOrFooter;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == getItemCount() - 1 && customLoadMoreView != null) {
//            if (isLoadMoreChanged) {
                return VIEW_TYPES.FOOTER;
//            }
        } else
            return VIEW_TYPES.NORMAL;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }


    protected class VIEW_TYPES {
        public static final int NORMAL = 0;
        public static final int HEADER = 1;
        public static final int FOOTER = 2;
        public static final int CHANGED_FOOTER = 3;
    }


    /**
     * Returns the number of items in the adapter bound to the parent RecyclerView.
     *
     * @return The number of items in the bound adapter
     */
    public abstract int getAdapterItemCount();

}
