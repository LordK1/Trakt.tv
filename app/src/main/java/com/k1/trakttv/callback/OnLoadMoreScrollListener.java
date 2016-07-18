package com.k1.trakttv.callback;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.k1.trakttv.MainActivityFragment;


/**
 * To make interaction between {@link RecyclerView} and {@link RecyclerView.OnScrollListener}
 * and send callback whe list scrolled down into end of the list
 * Created by K1 on 7/18/16.
 */
public class OnLoadMoreScrollListener extends RecyclerView.OnScrollListener {

    private static final String TAG = MainActivityFragment.class.getSimpleName();

    private final LinearLayoutManager layoutManager;
    private final OnLoadMoreCallback callback;
    private int visibleCount;
    private int firstVisiblePosition;
    private boolean isLoading = true;
    private int previousTotal = 0;
    private int threshold = 0;
    private int mPageCounter = 0;
    private int totalCount = 0;

    public OnLoadMoreScrollListener(OnLoadMoreCallback callback) {
        this.callback = callback;
        this.layoutManager = callback.getLayoutManager();
    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
//        Log.d(TAG, "onScrollStateChanged() called with: " + "recyclerView = [" + recyclerView + "], newState = [" + newState + "]");
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        /*Log.d(TAG, "onScrolled() called with: " + "recyclerView = [" + recyclerView + "], dx = [" + dx + "], dy = [" + dy + "]");*/
        visibleCount = recyclerView.getChildCount();
        totalCount = layoutManager.getItemCount();
        firstVisiblePosition = layoutManager.findFirstVisibleItemPosition();

        if (isLoading) {

            if (totalCount > previousTotal) {
                isLoading = false;
                previousTotal = totalCount;
            }
        }
        if (!isLoading && (totalCount - visibleCount) <= (firstVisiblePosition + threshold)) {
            // We reached at END of List
            /*Log.i(TAG, " END -------->> isLoading : " + isLoading + " totalCount : " + totalCount + " visibleCount : "
                    + visibleCount + " firstVisiblePosition : " + firstVisiblePosition + " threshold : " + threshold
            );*/

            mPageCounter++;
            callback.onLoadMore(mPageCounter);
            isLoading = true;
        }
    }

    /**
     * To handle all callback from {@link OnLoadMoreScrollListener} into {@link RecyclerView}
     */
    public interface OnLoadMoreCallback {

        void onLoadMore(int pageNumber);

        LinearLayoutManager getLayoutManager();
    }

}

