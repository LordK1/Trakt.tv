package com.k1.trakttv.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.k1.trakttv.MainApplication;
import com.k1.trakttv.R;
import com.k1.trakttv.adapter.ResultRecyclerAdapter;
import com.k1.trakttv.api.ApiService;
import com.k1.trakttv.callback.OnLoadMoreScrollListener;
import com.k1.trakttv.callback.ResultViewHolderCallback;
import com.k1.trakttv.model.Result;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by K1 on 7/19/16.
 */
public class SearchResultFragment extends Fragment implements ResultViewHolderCallback,
        OnLoadMoreScrollListener.OnLoadMoreCallback {

    public static final String FRAGMENT_NAME = SearchResultFragment.class.getName();

    private static final String MOVIE_TYPE = "movie";
    private static final int PAGE_NO = 0;
    private static final int LIMIT = 10;
    private static final String TAG = SearchResultFragment.class.getSimpleName();
    private static String QUERY_KEY = "Query";

    @Inject
    ApiService apiService;
    private View root;
    private RecyclerView mRecyclerView;
    private ResultRecyclerAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;
    private ArrayList<Result> mResults;
    private String mQuery;

    public SearchResultFragment() {
        mResults = new ArrayList<>();
        mAdapter = new ResultRecyclerAdapter(this, mResults, this);
    }

    /**
     * @param query
     * @return
     */
    public static SearchResultFragment newInstance(String query) {
        Bundle args = new Bundle();
        args.putString(QUERY_KEY, query);
        SearchResultFragment fragment = new SearchResultFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreate() called with: " + "savedInstanceState = [" + savedInstanceState + "]");
        super.onCreate(savedInstanceState);
        ((MainApplication) getActivity().getApplication()).getAppComponent().inject(this);


        // Query process
        if (getArguments() != null && getArguments().containsKey(QUERY_KEY)) {
            mQuery = getArguments().getString(QUERY_KEY);
            apiService.search(MOVIE_TYPE, mQuery, PAGE_NO, LIMIT).enqueue(new GetResultListCallback(true));
        }
    }

    @Override
    public void onAttach(Context context) {
        Log.d(TAG, "onAttach() called with: " + "context = [" + context + "]");
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView() called with: " + "inflater = [" + inflater + "], container = [" + container + "], savedInstanceState = [" + savedInstanceState + "]");
        root = inflater.inflate(R.layout.fragment_search_result, container, false);
        mRecyclerView = (RecyclerView) root.findViewById(R.id.search_result_recycler_view);
        mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.addOnScrollListener(new OnLoadMoreScrollListener(this));
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mAdapter);


        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onActivityCreated() called with: " + "savedInstanceState = [" + savedInstanceState + "]");
        super.onActivityCreated(savedInstanceState);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Search Results");
    }

    /**
     * When user updated query refresh {@link #mRecyclerView} lists
     *
     * @param query
     */
    public void updateQuery(String query) {
        mQuery = query;

        apiService.search(MOVIE_TYPE, mQuery, PAGE_NO, LIMIT).enqueue(new GetResultListCallback(true));
    }

    @Override
    public void onLoadMore(int pageNumber) {
        apiService.search(MOVIE_TYPE, mQuery, pageNumber, LIMIT).enqueue(new GetResultListCallback(false));
        Snackbar.make(root, R.string.loading_more, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public LinearLayoutManager getLayoutManager() {
        return mLayoutManager;
    }

    @Override
    public boolean onResultLongClick(Result result) {
        Toast.makeText(getContext(), result.getMovie().getTitle() + " Long clicked !!", Toast.LENGTH_SHORT).show();
        return true;
    }

    @Override
    public void onResultClick(Result result) {
        Toast.makeText(getContext(), result.getMovie().getTitle() + " clicked !!!", Toast.LENGTH_SHORT).show();

    }

    /**
     * To make handle received {@link Response<List<com.k1.trakttv.model.Movie>} of {@link List<Result>}
     */
    private class GetResultListCallback implements Callback<List<Result>> {

        public GetResultListCallback(boolean isNeedToRefresh) {

            if (isNeedToRefresh) {
                mResults.clear();
            }
        }

        @Override
        public void onResponse(Call<List<Result>> call, Response<List<Result>> response) {
//            Log.d(TAG, "onResponse() called with: " + "call = [" + call + "], response = [" + response + "]");
            if (response.isSuccessful()) {
                mResults.addAll(response.body());
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
        public void onFailure(Call<List<Result>> call, Throwable t) {
            t.printStackTrace();
        }
    }
}
