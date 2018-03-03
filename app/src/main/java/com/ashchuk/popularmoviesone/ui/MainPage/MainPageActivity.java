package com.ashchuk.popularmoviesone.ui.MainPage;

import android.os.Bundle;
import android.util.Log;

import com.ashchuk.popularmoviesone.R;
import com.ashchuk.popularmoviesone.api.IMovieDBApi;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;
import io.reactivex.Flowable;
import okhttp3.ResponseBody;
import retrofit2.Call;
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

        Call<ResponseBody> test = movieDBApi.getTopRated();
        Call<ResponseBody> test2 = movieDBApi.getPopular();
        Call<ResponseBody> test3 = movieDBApi.getMovieInfo("500");
    }

    @Override
    public void onMainLoaded() {
        Log.v("TEST", "Main page is loaded.");
    }
}
