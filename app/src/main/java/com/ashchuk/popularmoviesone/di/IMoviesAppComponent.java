package com.ashchuk.popularmoviesone.di;

import android.app.Application;

import com.ashchuk.popularmoviesone.App;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import dagger.android.support.DaggerApplication;

/**
 * Created by Artyom Koshko (@ashchuk) on 24.02.2018.
 */
@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class,
        MoviesAppModule.class,
        ActivityBuilder.class})
public interface IMoviesAppComponent extends AndroidInjector<DaggerApplication> {

    void inject(App app);

    @Override
    void inject(DaggerApplication instance);

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);
        IMoviesAppComponent build();
    }

}
