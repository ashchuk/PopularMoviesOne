package com.ashchuk.popularmoviesone.ui.MainPage;

import com.ashchuk.popularmoviesone.utils.MovieDBApiService;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Artyom Koshko (@ashchuk) on 24.02.2018.
 */
@Module
public abstract class MainPageModule {

    @Provides
    static MainPagePresenter provideMainPagePresenter(IMainPageView mainView, MovieDBApiService movieDBApiService) {
        return new MainPagePresenter(mainView, movieDBApiService);
    }

    @Binds
    abstract IMainPageView provideMainPageView(MainPageActivity mainPageActivity);

}
