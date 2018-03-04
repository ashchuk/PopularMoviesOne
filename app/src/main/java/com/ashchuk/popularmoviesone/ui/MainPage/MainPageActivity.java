package com.ashchuk.popularmoviesone.ui.MainPage;

import android.os.Bundle;
import android.util.Log;

import com.ashchuk.popularmoviesone.R;
import com.ashchuk.popularmoviesone.api.IMovieDBApi;
import com.ashchuk.popularmoviesone.data.pojo.Movie;
import com.ashchuk.popularmoviesone.data.pojo.MoviesQueryResult;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;
import io.reactivex.Flowable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainPageActivity extends DaggerAppCompatActivity implements IMainPageView {

    @Inject
    MainPagePresenter mainPagePresenter;

    @Inject
    Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainPagePresenter.loadMain();
        IMovieDBApi movieDBApi = retrofit.create(IMovieDBApi.class);

        Flowable<MoviesQueryResult> test = movieDBApi.getTopRated();
        Flowable<MoviesQueryResult> test2 = movieDBApi.getPopular();
        Flowable<Movie> test3 = movieDBApi.getMovieInfo("500");
    }

    @Override
    public void onMainLoaded() {
        Log.v("TEST", "Main page is loaded.");
    }
}
