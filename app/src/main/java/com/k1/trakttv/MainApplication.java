package com.k1.trakttv;

import android.app.Application;
import android.content.res.Configuration;
import android.util.Log;

import com.k1.trakttv.dependency.AppComponent;
import com.k1.trakttv.dependency.AppModule;
import com.k1.trakttv.dependency.DaggerAppComponent;


/**
 * Created by K1 on 7/16/16.
 */
public class MainApplication extends Application {

    private static final String TAG = MainApplication.class.getSimpleName();
    private AppComponent appComponent;


    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate() called with: " + "");
        appComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.d(TAG, "onConfigurationChanged() called with: " + "newConfig = [" + newConfig + "]");
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        Log.d(TAG, "onLowMemory() called with: " + "");
    }


    public AppComponent getAppComponent() {
        return appComponent;
    }
}
