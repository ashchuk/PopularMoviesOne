package com.ashchuk.popularmoviesone.di.modules;

import android.app.Application;
import android.content.Context;

import com.ashchuk.popularmoviesone.data.persistance.MoviesDbHelper;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Artyom Koshko (@ashchuk) on 24.02.2018.
 */

@Module
public abstract class MoviesAppModule {

    @Binds
    abstract Context provideContext(Application application);
}