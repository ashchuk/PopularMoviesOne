package com.ashchuk.popularmoviesone.di.modules;

import android.app.Application;

import com.ashchuk.popularmoviesone.data.persistance.MoviesDbHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Artyom Koshko (@ashchuk) on 25.03.2018.
 */
@Module
public class DatabaseModule {
    @Provides
    @Singleton
    MoviesDbHelper provideMoviesDbHelper(Application application) {
        return new MoviesDbHelper(application.getApplicationContext());
    }
}
