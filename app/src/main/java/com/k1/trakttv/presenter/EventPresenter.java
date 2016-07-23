package com.k1.trakttv.presenter;

import android.util.Log;

import com.k1.trakttv.model.Movie;

/**
 * Created by K1 on 7/23/16.
 */
public class EventPresenter {

    private static final String TAG = EventPresenter.class.getSimpleName();

    public void onMovieClick(Movie movie) {
        Log.d(TAG, "onMovieClick() called with: " + "movie = [" + movie + "]");
    }
}
