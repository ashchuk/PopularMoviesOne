package com.ashchuk.popularmoviesone.ui.MainPage;

import com.ashchuk.popularmoviesone.utils.MovieDBApiService;

import javax.inject.Inject;

/**
 * Created by Artyom Koshko (@ashchuk) on 24.02.2018.
 */

public class MainPagePresenter implements IMainPagePresenter {

    IMainPageView mainPageView;
    MovieDBApiService movieDBApiService;

    @Inject
    public MainPagePresenter(IMainPageView mainView, MovieDBApiService apiService) {
        this.mainPageView = mainView;
        this.movieDBApiService = apiService;
    }

    public void loadMain(){
        movieDBApiService.doWork();
        mainPageView.onMainLoaded();
    }
}
