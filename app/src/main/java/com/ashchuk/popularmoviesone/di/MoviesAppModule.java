package com.ashchuk.popularmoviesone.di;

import android.app.Application;
import android.content.Context;

import dagger.Binds;
import dagger.Module;

/**
 * Created by Artyom Koshko (@ashchuk) on 24.02.2018.
 */

@Module
public abstract class MoviesAppModule {

    @Binds
    abstract Context provideContext(Application application);

}