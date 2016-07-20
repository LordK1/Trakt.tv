package com.k1.trakttv;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.k1.trakttv.adapter.MoviesRecyclerAdapter;
import com.k1.trakttv.api.ApiService;
import com.k1.trakttv.callback.MovieViewHolderCallback;
import com.k1.trakttv.callback.OnLoadMoreScrollListener;
import com.k1.trakttv.callback.OnMainCallback;
import com.k1.trakttv.model.Movie;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Load List of {@link Movie} and bind into {@link MainActivityFragment#mRecyclerView}
 * via {@link MoviesRecyclerAdapter}, handle loadMore process with in {@link com.k1.trakttv.callback.OnLoadMoreScrollListener.OnLoadMoreCallback}
 * <p>
 * Created by K1 on 7/17/16.
 */
public class MainActivityFragment extends Fragment implements OnLoadMoreScrollListener.OnLoadMoreCallback, MovieViewHolderCallback {

    public static final int LIMIT = 10;
    public static final int DEFAULT_PAGE_NO = 1;
    public static final String FRAGMENT_NAME = MainActivityFragment.class.getSimpleName();
    private static final String TAG = MainActivityFragment.class.getSimpleName();

    @Inject
    ApiService apiService;
    private View root;
    private TextView mTitle;
    private RecyclerView mRecyclerView;
    private ArrayList<Movie> list;
    private MoviesRecyclerAdapter mAdapter;
    private OnMainCallback callback;
    private LinearLayoutManager mLayoutManager;


    //0
    public MainActivityFragment() {
//        Log.d(TAG, "MainActivityFragment() called with: " + "");
        list = new ArrayList<>();
        mAdapter = new MoviesRecyclerAdapter(list, this);

    }

    //2
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        Log.d(TAG, "onCreate() called with: " + "savedInstanceState = [" + savedInstanceState + "]");
        ((MainApplication) getActivity().getApplication()).getAppComponent().inject(this);
    }

    //3
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView() called with: " + "inflater = [" + inflater + "], container = [" + container + "], savedInstanceState = [" + savedInstanceState + "]");
        root = inflater.inflate(R.layout.fragment_main, container, false);
        mRecyclerView = (RecyclerView) root.findViewById(R.id.main_fragment_recycler_view);
        mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addOnScrollListener(new OnLoadMoreScrollListener(this));
        apiService.getPopularMovies(DEFAULT_PAGE_NO, LIMIT).enqueue(new GetPopularMoviesCallback());
        return root;
    }

    //1
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        Log.d(TAG, "onAttach() called with: " + "context = [" + context + "]");
        if (context instanceof OnMainCallback) {
            callback = (OnMainCallback) context;
        } else {
            throw new ClassCastException(context.toString() + " must be implemented OnMainCallback !!! ");
        }

    }

    // 4
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        Log.d(TAG, "onActivityCreated() called with: " + "savedInstanceState = [" + savedInstanceState + "]");
    }

    @Override
    public void onLoadMore(int pageNumber) {
//        Log.d(TAG, "onLoadMore() called with: " + "pageNumber = [" + pageNumber + "]");
        Snackbar.make(root, R.string.loading_more, Snackbar.LENGTH_SHORT).show();
        apiService.getPopularMovies(pageNumber, LIMIT).enqueue(new GetPopularMoviesCallback());
    }

    @Override
    public LinearLayoutManager getLayoutManager() {
        return mLayoutManager;
    }

    @Override
    public Movie onUpVoteClick(Movie movie) {
        Toast.makeText(getContext(), movie.getTitle() + " onUpVoteClick !!!", Toast.LENGTH_SHORT).show();
        return movie;
    }

    @Override
    public void onMovieClick(Movie movie) {
        Toast.makeText(getContext(), movie.getTitle() + " onMovieClick !!!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onMovieLongClick(Movie movie) {
        Toast.makeText(getContext(), movie.getTitle() + " onMovieLongClick !!!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public Movie onMovieExtraClick(Movie movie) {
        Toast.makeText(getContext(), movie.getTitle() + " onMovieExtraClick !!!", Toast.LENGTH_SHORT).show();
        return null;
    }


    /**
     * To handle response of {@link ApiService#getPopularMovies(Integer, Integer)}
     */
    private class GetPopularMoviesCallback implements Callback<List<Movie>> {
        @Override
        public void onResponse(Call<List<Movie>> call, Response<List<Movie>> response) {
            if (response.isSuccessful()) {
                list.addAll(response.body());
                mAdapter.notifyDataSetChanged();
            } else {
                try {
                    Toast.makeText(getContext(), response.errorBody().string(), Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        public void onFailure(Call<List<Movie>> call, Throwable t) {
            Log.d(TAG, "onFailure() called with: " + "call = [" + call + "], t = [" + t + "]");
            t.printStackTrace();
        }
    }
}
