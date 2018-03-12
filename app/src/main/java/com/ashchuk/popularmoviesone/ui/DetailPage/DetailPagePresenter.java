package com.ashchuk.popularmoviesone.ui.DetailPage;

import com.ashchuk.popularmoviesone.api.IMovieDBApi;
import com.ashchuk.popularmoviesone.data.pojo.MovieDetailed;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

/**
 * Created by Artyom Koshko (@ashchuk) on 24.02.2018.
 */

public class DetailPagePresenter implements IDetailPagePresenter {

    @Inject
    Retrofit retrofit;

    @Inject
    public DetailPagePresenter(IDetailPageView detailPageView){
        detailPageView.onDetailLoaded();
    }

    @Override
    public void subscribeOnMovie(Observer<MovieDetailed> observer, String movieId) {
        IMovieDBApi movieDBApi = retrofit.create(IMovieDBApi.class);
        movieDBApi.getMovieInfo(movieId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
