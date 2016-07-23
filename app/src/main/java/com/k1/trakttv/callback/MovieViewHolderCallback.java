package com.k1.trakttv.callback;

import android.view.View;

import com.k1.trakttv.model.Movie;

/**
 * An interface to send callbacks from {@link com.k1.trakttv.adapter.MoviesRecyclerAdapter.MovieViewHolder}
 * into the parent activity or fragments
 * <p/>
 * // TODO: 7/23/16 after change architecture of project into MVP this interface could be more useful to handle some scenarios
 * // Notice: 7/23/16 Some functionalism's are fake and hadn't any firm logic but in product scale project can be more efficiency
 * <p/>
 * Created by K1 on 7/18/16.
 */
public interface MovieViewHolderCallback {

    /**
     * @param movie
     * @param view
     * @return
     */
    View onUpVoteClick(Movie movie, View view);

    /**
     * @param movie
     */
    void onMovieClick(Movie movie);

    /**
     * Just for show and test some capabilities of
     *
     * @param title
     * @param hasTitle
     */
    boolean onMovieLongClick(String title, boolean hasTitle);

    /**
     * @param movie
     * @return
     */
    Movie onMovieExtraClick(Movie movie);

}
