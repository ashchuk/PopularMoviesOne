package com.ashchuk.popularmoviesone.di.modules;

import com.ashchuk.popularmoviesone.ui.DetailPage.DetailPageActivity;
import com.ashchuk.popularmoviesone.ui.DetailPage.DetailPagePresenter;
import com.ashchuk.popularmoviesone.ui.DetailPage.IDetailPagePresenter;
import com.ashchuk.popularmoviesone.ui.DetailPage.IDetailPageView;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Artyom Koshko (@ashchuk) on 24.02.2018.
 */
@Module
public abstract class DetailPageModule {
    @Provides
    static IDetailPagePresenter provideDetailPagePresenter(IDetailPageView detailPageView) {
        return new DetailPagePresenter(detailPageView);
    }

    @Binds
    abstract IDetailPageView provideMainPageView(DetailPageActivity detailPageActivity);
}
