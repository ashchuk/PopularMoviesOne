package com.ashchuk.popularmoviesone;

import com.ashchuk.popularmoviesone.di.DaggerIMoviesAppComponent;
import com.ashchuk.popularmoviesone.di.IMoviesAppComponent;
import com.facebook.stetho.Stetho;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;

/**
 * Created by Artyom Koshko (@ashchuk) on 24.02.2018.
 */

public class App extends DaggerApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
    }

    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        IMoviesAppComponent moviesAppComponent = DaggerIMoviesAppComponent.builder().application(this).build();
        moviesAppComponent.inject(this);
        return moviesAppComponent;
    }
}
