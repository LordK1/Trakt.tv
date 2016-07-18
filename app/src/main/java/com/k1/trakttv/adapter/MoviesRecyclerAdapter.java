package com.k1.trakttv.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.k1.trakttv.R;
import com.k1.trakttv.model.Movie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * extended {@link android.support.v7.widget.RecyclerView.Adapter} to bind {@link Movie} into
 * {@link com.k1.trakttv.MainActivityFragment#mRecyclerView}
 */
public class MoviesRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = MoviesRecyclerAdapter.class.getSimpleName();
    private ArrayList<Movie> movies;

    public MoviesRecyclerAdapter(ArrayList<Movie> movies) {
        this.movies = movies;
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
        return movies.get(position);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }


    /**
     * extended {@link MovieViewHolder} class to bind it
     */
    private class MovieViewHolder extends RecyclerView.ViewHolder {

        private final TextView titleTextView;
        private final ImageView mImageView;


        public MovieViewHolder(View view) {
            super(view);

            titleTextView = (TextView) view.findViewById(R.id.movie_title_text_view);
            mImageView = (ImageView) view.findViewById(R.id.movie_tumbnail_image_view);
        }

        /**
         * To bind {@link Movie} into {@link MovieViewHolder}
         *
         * @param movie
         */
        public void onBind(Movie movie) {
            titleTextView.setText(movie.getTitle());

            Picasso.with(mImageView.getContext())
                    .load(movie.getPhotos().getThumb().getFull())
                    .fit()
                    .into(mImageView);
        }
    }
}
