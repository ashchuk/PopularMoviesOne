package com.ashchuk.popularmoviesone.ui.MainPage;

import com.ashchuk.popularmoviesone.utils.IMovieDBApiService;
import com.ashchuk.popularmoviesone.utils.MovieDBApiService;

import javax.inject.Inject;

/**
 * Created by Artyom Koshko (@ashchuk) on 24.02.2018.
 */

public class MainPagePresenter implements IMainPagePresenter {

    IMovieDBApiService movieDBApiService;

    @Inject
    public MainPagePresenter(IMovieDBApiService apiService) {
        this.movieDBApiService = apiService;
    }

    public void loadMain(){
        movieDBApiService.doWork();
    }
}
