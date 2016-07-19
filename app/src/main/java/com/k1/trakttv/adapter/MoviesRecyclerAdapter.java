package com.k1.trakttv.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.k1.trakttv.R;
import com.k1.trakttv.callback.MovieViewHolderCallback;
import com.k1.trakttv.model.Movie;
import com.squareup.picasso.Picasso;

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
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_view_holder, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((MovieViewHolder) holder).onBind(getItem(position));

    }

    private Movie getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    /**
     * extended {@link MovieViewHolder} class to bind it
     */
    private class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        private final TextView titleTextView;
        private final ImageView mImageView;
        private final ImageButton mExtraButton;
        private final View view;
        private final TextView mVotesTextView;
        private final RelativeLayout mVotesContainer;
        private Movie movie;


        public MovieViewHolder(View view) {
            super(view);
            this.view = view;
            titleTextView = (TextView) view.findViewById(R.id.movie_title_text_view);
            mExtraButton = (ImageButton) view.findViewById(R.id.movie_extra_menu);
            mVotesContainer = (RelativeLayout) view.findViewById(R.id.movie_votes_container);
            mVotesTextView = (TextView) view.findViewById(R.id.movie_votes_text_view);
            mImageView = (ImageView) view.findViewById(R.id.movie_tumbnail_image_view);
        }

        /**
         * To bind {@link Movie} into {@link MovieViewHolder}
         *
         * @param movie
         */
        public void onBind(final Movie movie) {
            this.movie = movie;
            view.setOnClickListener(this);
            view.setOnLongClickListener(this);
            titleTextView.setText(movie.getTitle());
            mVotesTextView.setText(movie.getVotesFormatted());

            mVotesContainer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    Toast.makeText(view.getContext(), movie.getTitle() + " Up Voted !!! ", Toast.LENGTH_SHORT).show();
                    callback.onUpVoteClick(movie);
                }
            });
            mExtraButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    Toast.makeText(view.getContext(), movie.getTitle() + "Extra Clicked !!!", Toast.LENGTH_SHORT).show();
                    callback.onMovieExtraClick(movie);
                }
            });
            Picasso.with(mImageView.getContext())
                    .load(movie.getPhotos().getThumb().getFull())
                    .fit()
                    .into(mImageView);
        }

        @Override
        public void onClick(View view) {
//            Toast.makeText(view.getContext(), movie.toString(), Toast.LENGTH_SHORT).show();
            callback.onMovieClick(movie);
        }

        @Override
        public boolean onLongClick(View view) {
//            Toast.makeText(view.getContext(), movie.getIds() + " Long Clicked !!!", Toast.LENGTH_SHORT).show();
            callback.onMovieLongClick(movie);
            return true;
        }


    }

}
