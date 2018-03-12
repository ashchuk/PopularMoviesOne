package com.ashchuk.popularmoviesone.ui.DetailPage;

import com.ashchuk.popularmoviesone.data.pojo.MovieDetailed;

import io.reactivex.Observer;

/**
 * Created by Artyom Koshko (@ashchuk) on 24.02.2018.
 */

public interface IDetailPagePresenter {
    void subscribeOnMovie(Observer<MovieDetailed> observer, String movieId);
}
