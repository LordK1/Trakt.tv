package com.k1.trakttv.dependency;

import android.app.Activity;

import com.k1.trakttv.MainActivity;

import dagger.Component;

/**
 * To make possibly injection {@link javax.inject.Inject} of dependencies within the dependent classes that included in
 * {@link ApplicationScope}
 * Created by K1 on 7/17/16.
 */
@ApplicationScope
@Component(modules = {AppModule.class})
public interface AppComponent {

    /**
     * To {@link javax.inject.Inject} {@link AppModule} into extended {@link Activity} classes
     *
     * @param mainActivity
     */
    void inject(MainActivity mainActivity);
}
