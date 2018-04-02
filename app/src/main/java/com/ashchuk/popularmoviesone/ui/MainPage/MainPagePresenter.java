package com.ashchuk.popularmoviesone.ui.MainPage;

import android.content.Context;

import com.ashchuk.popularmoviesone.api.IMovieDBApi;
import com.ashchuk.popularmoviesone.data.persistance.MoviesDbHelper;
import com.ashchuk.popularmoviesone.data.pojo.Movie;
import com.ashchuk.popularmoviesone.data.pojo.MoviesQueryResult;
import java.util.List;

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
    public MoviesDbHelper moviesDbHelper;

    @Inject
    Retrofit retrofit;

    @Override
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

    @Override
    public List<Movie> subscribeOnFavorite(Context context) {
        return moviesDbHelper.getFavoriteMovies(context);
    }
}
