package com.k1.trakttv.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.k1.trakttv.R;
import com.k1.trakttv.callback.ResultViewHolderCallback;
import com.k1.trakttv.fragment.SearchResultFragment;
import com.k1.trakttv.model.Result;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * To adaptation {@link Result} List, within {@link SearchResultFragment#mRecyclerView}
 * Created by K1 on 7/20/16
 */
public class ResultRecyclerAdapter extends RecyclerView.Adapter {
    private final ArrayList<Result> results;
    private final ResultViewHolderCallback callback;
    private SearchResultFragment fragment;

    public ResultRecyclerAdapter(SearchResultFragment fragment, ArrayList<Result> results, ResultViewHolderCallback callback) {
        this.fragment = fragment;
        this.results = results;
        this.callback = callback;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.result_view_holder, parent, false);
        return new ResultViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((ResultViewHolder) holder).onBind(getItem(position));
    }

    private Result getItem(int position) {
        return results.get(position);
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    /**
     * To bind {@link Result} object into view
     */
    private class ResultViewHolder extends RecyclerView.ViewHolder {

        private final TextView mTitleTextView;
        private final TextView mOverviewTextView;
        private final ImageView mTumbnailImageView;
        private final TextView mYearTextView;
        private final View view;

        public ResultViewHolder(View view) {
            super(view);
            this.view = view;

            mTumbnailImageView = (ImageView) view.findViewById(R.id.result_tumbnail_image_view);
            mTitleTextView = (TextView) view.findViewById(R.id.result_title_text_view);
            mOverviewTextView = (TextView) view.findViewById(R.id.result_overview_text_view);
            mYearTextView = (TextView) view.findViewById(R.id.result_year_text_view);
        }

        /**
         * To bind {@link Result} item into {@link ResultViewHolder}
         * it depends on scenarios to handle all interaction between user and application
         * and I just defined more common interactions like {@link android.view.View.OnClickListener}
         * and {@link android.view.View.OnLongClickListener}
         *
         * @param result
         */
        public void onBind(final Result result) {
            this.view.setOnClickListener(new OnResultClickListener(result));
            this.view.setOnLongClickListener(new OnResultLongClickListener(result));
            mTitleTextView.setText(result.getMovie().getTitle());
            mYearTextView.setText(String.valueOf(result.getMovie().getYear()));
            mOverviewTextView.setText(result.getMovie().getOverview());
            try {
                Picasso.with(fragment.getContext())
                        .load(result.getMovie().getPhotos().getPoster().getThumb())
                        .fit()
                        .into(mTumbnailImageView);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private class OnResultLongClickListener implements View.OnLongClickListener {
            private final Result result;

            public OnResultLongClickListener(Result result) {
                this.result = result;
            }

            @Override
            public boolean onLongClick(View view) {

                return callback.onResultLongClick(result);
            }
        }

        private class OnResultClickListener implements View.OnClickListener {
            private final Result result;

            public OnResultClickListener(Result result) {
                this.result = result;
            }

            @Override
            public void onClick(View view) {
                callback.onResultClick(result);
            }
        }
    }
}
