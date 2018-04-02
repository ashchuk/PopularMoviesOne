package com.ashchuk.popularmoviesone;

import com.ashchuk.popularmoviesone.di.DaggerIMoviesAppComponent;
import com.ashchuk.popularmoviesone.di.IMoviesAppComponent;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;

/**
 * Created by Artyom Koshko (@ashchuk) on 24.02.2018.
 */

public class App extends DaggerApplication {
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        IMoviesAppComponent moviesAppComponent = DaggerIMoviesAppComponent.builder().application(this).build();
        moviesAppComponent.inject(this);
        return moviesAppComponent;
    }
}
