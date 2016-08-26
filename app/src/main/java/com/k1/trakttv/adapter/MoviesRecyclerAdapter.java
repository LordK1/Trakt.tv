package com.k1.trakttv.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.k1.trakttv.BR;
import com.k1.trakttv.R;
import com.k1.trakttv.callback.MovieViewHolderCallback;
import com.k1.trakttv.model.Movie;

import java.util.ArrayList;

/**
 * extended {@link android.support.v7.widget.RecyclerView.Adapter} to bind {@link Movie} into
 * {@link com.k1.trakttv.MainActivityFragment#mRecyclerView}
 */
public class MoviesRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = MoviesRecyclerAdapter.class.getSimpleName();
    private final MovieViewHolderCallback callback;
    private ArrayList<Movie> list;

    public MoviesRecyclerAdapter(ArrayList<Movie> movies, MovieViewHolderCallback callback) {
        this.list = movies;
        this.callback = callback;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final ViewDataBinding viewDataBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()), R.layout.movie_view_holder, parent, false);
        return new MovieViewHolder(viewDataBinding);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        final ViewDataBinding binding = ((MovieViewHolder) holder).getBinding();
        /* check this fuckin variable Id is generalisable after code compilation */
        binding.setVariable(BR.movie, getItem(position));
        binding.setVariable(BR.callbacks, this.callback);
    }

    private Movie getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }


    /**
     * extended {@link MovieViewHolder} class to bind it
     */
    private class MovieViewHolder extends RecyclerView.ViewHolder {

        private final TextView titleTextView;
        private final ImageView mImageView;
        private final ImageButton mExtraButton;
        private final View view;
        private final TextView mVotesTextView;
        private final RelativeLayout mVotesContainer;
        private final ViewDataBinding binding;
        private Movie movie;


        public MovieViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            this.view = this.binding.getRoot();
            titleTextView = (TextView) view.findViewById(R.id.movie_title_text_view);
            mExtraButton = (ImageButton) view.findViewById(R.id.movie_extra_menu);
            mVotesContainer = (RelativeLayout) view.findViewById(R.id.movie_votes_container);
            mVotesTextView = (TextView) view.findViewById(R.id.movie_votes_text_view);
            mImageView = (ImageView) view.findViewById(R.id.movie_tumbnail_image_view);
        }


        public ViewDataBinding getBinding() {
            return binding;
        }

    }

}
