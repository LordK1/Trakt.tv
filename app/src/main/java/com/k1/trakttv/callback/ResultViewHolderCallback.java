package com.k1.trakttv.callback;

import com.k1.trakttv.model.Result;

/**
 * this interface used to make communication between {@link com.k1.trakttv.fragment.SearchResultFragment}
 * and {@link com.k1.trakttv.fragment.SearchResultFragment.ResultRecyclerAdapter}
 * to handle all interactions between {@link com.k1.trakttv.fragment.SearchResultFragment.ResultRecyclerAdapter.ResultViewHolder}
 * and its fragment/activity
 * <p/>
 * Created by K1 on 7/19/16.
 */
public interface ResultViewHolderCallback {

    /**
     * According to scenarios it might be different
     * @param result
     * @return
     */
    boolean onResultLongClick(Result result);

    void onResultClick(Result result);
}
