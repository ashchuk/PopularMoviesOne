package com.ashchuk.popularmoviesone.di.module;

import com.ashchuk.popularmoviesone.ui.MainPage.IMainPagePresenter;
import com.ashchuk.popularmoviesone.ui.MainPage.IMainPageView;
import com.ashchuk.popularmoviesone.ui.MainPage.MainPageActivity;
import com.ashchuk.popularmoviesone.ui.MainPage.MainPagePresenter;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Artyom Koshko (@ashchuk) on 24.02.2018.
 */
@Module
public abstract class MainPageModule {

    @Provides
    static IMainPagePresenter provideMainPagePresenter(IMainPageView mainPageView) {
        return new MainPagePresenter(mainPageView);
    }

    @Binds
    abstract IMainPageView provideMainPageView(MainPageActivity mainPageActivity);

}
