package com.k1.trakttv.callback;

import com.k1.trakttv.model.Movie;

/**
 * An interface to send callbacks from {@link com.k1.trakttv.adapter.MoviesRecyclerAdapter.MovieViewHolder}
 * into the parent activity or fragments
 * Created by K1 on 7/18/16.
 */
public interface MovieViewHolderCallback {

    Movie onUpVoteClick(Movie movie);

    void onMovieClick(Movie movie);

    void onMovieLongClick(Movie movie);

    Movie onMovieExtraClick(Movie movie);

}
