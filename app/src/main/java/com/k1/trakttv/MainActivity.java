package com.k1.trakttv;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.k1.trakttv.api.ApiService;
import com.k1.trakttv.callback.OnMainCallback;

import javax.inject.Inject;

/**
 * Main Activity of application to show some fragments and handle navigation between {@link MainActivityFragment}
 * <p/>
 * Created by K1 on 7/17/16.
 */
public class MainActivity extends AppCompatActivity implements OnMainCallback {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Inject
    ApiService service;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ((MainApplication) getApplication()).getAppComponent().inject(this);
        final boolean isInjected = service != null;
        Log.i(TAG, "  -------- isInjected : " + isInjected);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
