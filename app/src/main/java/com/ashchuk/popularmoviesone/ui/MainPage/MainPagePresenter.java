package com.ashchuk.popularmoviesone.ui.MainPage;

import com.ashchuk.popularmoviesone.api.IMovieDBApi;
import com.ashchuk.popularmoviesone.data.pojo.MoviesQueryResult;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

/**
 * Created by Artyom Koshko (@ashchuk) on 24.02.2018.
 */

public class MainPagePresenter implements IMainPagePresenter {

    @Inject
    public MainPagePresenter(IMainPageView mainPageView) {
        mainPageView.onMainLoaded();
    }

    @Inject
    Retrofit retrofit;

    public void subscribeOnTopRated(Observer<MoviesQueryResult> observer) {
        IMovieDBApi movieDBApi = retrofit.create(IMovieDBApi.class);
        movieDBApi.getTopRated()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    @Override
    public void subscribeOnPopular(Observer<MoviesQueryResult> observer) {
        IMovieDBApi movieDBApi = retrofit.create(IMovieDBApi.class);
        movieDBApi.getPopular()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
