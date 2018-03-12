package com.ashchuk.popularmoviesone.ui.MainPage;

import com.ashchuk.popularmoviesone.data.pojo.MoviesQueryResult;

import io.reactivex.Observer;

/**
 * Created by Artyom Koshko (@ashchuk) on 24.02.2018.
 */

public interface IMainPagePresenter {
    void subscribeOnTopRated(Observer<MoviesQueryResult> observer);
    void subscribeOnPopular(Observer<MoviesQueryResult> observer);
}
