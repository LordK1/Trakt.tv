package com.k1.trakttv.adapter;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.View;
import android.widget.TextView;

import com.k1.trakttv.MainActivity;
import com.k1.trakttv.R;

/**
 * To list of {@link com.k1.trakttv.model.Movie} as {@link MainActivity#mSearchView}
 * suggestions with in the list of {@link com.k1.trakttv.model.Result} and show just title of {@link com.k1.trakttv.model.Movie}
 * <p>
 * Created by K1 on 7/19/16.
 */
public class SuggestionsAdapter extends SimpleCursorAdapter {

    private final Context context;

    public SuggestionsAdapter(Context context, int layoutId, Cursor cursor, String[] columns, int[] ids, int flag) {
        super(context, layoutId, cursor, columns, ids, flag);
        this.context = context;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        final TextView titleTextView = (TextView) view.findViewById(R.id.suggestion_text_view);
        titleTextView.setText(cursor.getString(cursor.getColumnIndex(MainActivity.TITLE_COLUMN_NAME)));
    }
}
