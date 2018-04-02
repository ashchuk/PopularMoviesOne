package com.ashchuk.popularmoviesone.ui.MainPage;

import android.content.Context;

import com.ashchuk.popularmoviesone.data.pojo.Movie;
import com.ashchuk.popularmoviesone.data.pojo.MoviesQueryResult;
import java.util.List;

import io.reactivex.Observer;

/**
 * Created by Artyom Koshko (@ashchuk) on 24.02.2018.
 */

public interface IMainPagePresenter {
    void subscribeOnTopRated(Observer<MoviesQueryResult> observer);
    void subscribeOnPopular(Observer<MoviesQueryResult> observer);
    List<Movie> subscribeOnFavorite(Context context);
}
