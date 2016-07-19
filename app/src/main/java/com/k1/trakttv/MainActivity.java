package com.k1.trakttv;

import android.annotation.TargetApi;
import android.app.SearchManager;
import android.content.Context;
import android.database.MatrixCursor;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;

import com.k1.trakttv.adapter.SuggestionsAdapter;
import com.k1.trakttv.api.ApiService;
import com.k1.trakttv.callback.OnMainCallback;
import com.k1.trakttv.fragment.SearchResultFragment;
import com.k1.trakttv.model.Movie;
import com.k1.trakttv.model.Result;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Main Activity of application to show some fragments and handle navigation between {@link MainActivityFragment}
 * <p/>
 * Created by K1 on 7/17/16.
 */
public class MainActivity extends AppCompatActivity implements OnMainCallback {

    public static final String TITLE_COLUMN_NAME = "title";
    public static final String MOVIE_TYPE = "movie";
    private static final String ID_COLUMN_NAME = "_id";
    private static final String URL_COLUMN_NAME = "url";
    private static final String TAG = MainActivity.class.getSimpleName();
    private static final String[] columns = new String[]{ID_COLUMN_NAME, URL_COLUMN_NAME, TITLE_COLUMN_NAME};
    @Inject
    ApiService service;
    private Toolbar toolbar;
    private SearchView mSearchView;
    private SuggestionsAdapter mSuggestionsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);

        ((MainApplication) getApplication()).getAppComponent().inject(this);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new MainActivityFragment(), MainActivityFragment.FRAGMENT_NAME)
                .addToBackStack(MainActivityFragment.FRAGMENT_NAME)
                .commit();
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        final MenuItem item = menu.findItem(R.id.action_search);
        mSearchView = (SearchView) MenuItemCompat.getActionView(item);
        mSearchView.setIconified(true);
        mSearchView.setIconifiedByDefault(true);
        mSearchView.setQueryRefinementEnabled(true);
        mSearchView.setQueryHint("Search in Movies ...");
        mSearchView.setSubmitButtonEnabled(true);
        mSearchView.setBackground(ContextCompat.getDrawable(MainActivity.this, R.drawable.green_rounded_border));
        mSearchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        mSearchView.setImeOptions(EditorInfo.IME_ACTION_SEARCH);
        mSuggestionsAdapter = new SuggestionsAdapter(this, R.layout.suggestion_item, null, columns, null, 0);

        mSearchView.setSuggestionsAdapter(mSuggestionsAdapter);
        // listeners
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.d(TAG, "onQueryTextSubmit() called with: " + "query = [" + query + "]");
                if (query != null && !TextUtils.isEmpty(query)) {
                    mSearchView.clearFocus();
                    updateSearchResults(query);
                    return true;
                }
                return false;
            }

            // 1
            @Override
            public boolean onQueryTextChange(String newText) {
                Log.d(TAG, "onQueryTextChange() called with: " + "newText = [" + newText + "]");
                if (newText.length() > 1) {
                    fetchSuggestion(newText);
                    return true;
                }
                return false;
            }
        });
        mSearchView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                Log.d(TAG, "onKey() called with: " + "view = [" + view + "], i = [" + i + "], keyEvent = [" + keyEvent + "]");
                return false;
            }
        });
        // 2
        /*mSearchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.d(TAG, "onClick() called with: " + "view = [" + view + "]");

            }
        });*/

        mSearchView.setOnSuggestionListener(new OnSuggestionListener());

        return true;
    }

    /**
     * To update results {@link SearchResultFragment} with defined query
     *
     * @param query
     */
    private void updateSearchResults(@NonNull String query) {
        ((SearchResultFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container)).updateQuery(query);
    }

    /**
     * To fetch list of Suggestions
     *
     * @param query
     */
    private void fetchSuggestion(String query) {
        Log.d(TAG, "fetchSuggestion() called with: " + "query = [" + query + "]");
        // show some real time suggestions
        service.search(MOVIE_TYPE, query, 0, 3).enqueue(new GetSuggestionListCallback());
        // after
        updateSearchResults(query);
    }

    /**
     * To convert {@link List<Movie>} in to {@link MatrixCursor}
     *
     * @param results
     * @return
     */
    private MatrixCursor convertToCursor(List<Result> results) {
        MatrixCursor matrixCursor = new MatrixCursor(columns);
        for (Result result : results) {
            String[] tmp = new String[columns.length];
            tmp[0] = String.valueOf(result.getMovie().getIds().getTrakt());
            tmp[1] = String.valueOf(result.getMovie().getIds().getSlug());
            tmp[2] = result.getMovie().getTitle();
            matrixCursor.addRow(tmp);

        }
        return matrixCursor;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                if (!(getSupportFragmentManager().findFragmentById(R.id.fragment_container) instanceof SearchResultFragment)) {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, new SearchResultFragment(), SearchResultFragment.FRAGMENT_NAME)
                            .addToBackStack(SearchResultFragment.FRAGMENT_NAME)
                            .commit();
                    return true;
                }
        }

        return super.onOptionsItemSelected(item);
    }


    /**
     * When {@link Response} of {@link ApiService#search(String, String, Integer, Integer)}
     * received
     * // Notice: 7/19/16 it might cause overwhelming or user confusing, But I want to handle something more than simple search
     */
    private class GetSuggestionListCallback implements Callback<List<Result>> {
        @Override
        public void onResponse(Call<List<Result>> call, Response<List<Result>> response) {
            /*Log.d(TAG, "onResponse() called with: " + "call = [" + call + "], response = [" + response + "]"
                    + " raw : " + response.raw());*/
            if (response.isSuccessful()) {
                mSuggestionsAdapter.changeCursor(convertToCursor(response.body()));
            } else {
                try {
                    Log.i(TAG, " ERROR : " + response.errorBody().string());
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

    /**
     * To handle {@link SuggestionsAdapter} callbacks with two interactions
     * Selected or Clicked might not have different in this scenario
     */
    private class OnSuggestionListener implements SearchView.OnSuggestionListener {
        @Override
        public boolean onSuggestionSelect(int position) {
            /*Log.d(TAG, " ------------ onSuggestionSelect() called with: " + "position = [" + position + "]" +
                    " ˚¬˚ ITEM : " + mSuggestionsAdapter.getItem(position)
            );*/
            doSuggestion(position);
            return true;
        }

        /**
         * When suggestion item clicked or selected, just set query of {@link #mSearchView}
         * and submit it.
         *
         * @param position
         */
        private void doSuggestion(int position) {
            final MatrixCursor matrixCursor = (MatrixCursor) mSuggestionsAdapter.getItem(position);
            final String title = matrixCursor.getString(matrixCursor.getColumnIndex(TITLE_COLUMN_NAME));
            mSearchView.setQuery(title, true);
            mSearchView.clearFocus();
        }

        /**
         * When Suggestion list clicked
         *
         * @param position
         * @return
         */
        @Override
        public boolean onSuggestionClick(int position) {
            /*Log.d(TAG, "onSuggestionClick() called with: " + "position = [" + position + "]" +
                    " Item : " + mSuggestionsAdapter.getItem(position) +
                    " ->>>->>>>->>>");*/
            doSuggestion(position);

            return true;
        }
    }
}
