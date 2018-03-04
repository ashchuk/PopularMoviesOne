package com.ashchuk.popularmoviesone.ui.MainPage;

import android.os.Bundle;
import android.util.Log;

import com.ashchuk.popularmoviesone.R;
import com.ashchuk.popularmoviesone.api.IMovieDBApi;

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

        Call<ResponseBody> test = movieDBApi.getTopRated();
        test.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()) {
                    try {
                        String values = response.body().string();
                        Log.v("TEST", "getTopRated query successfull");
                    } catch (IOException e) {
                        e.printStackTrace();
                        Log.v("TEST", "getTopRated query failure");
                    }
                } else {
                    System.out.println(response.errorBody());
                    Log.v("TEST", "getTopRated response is not successfull");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.v("TEST", "getTopRated onFailure");
                t.printStackTrace();
            }
        });
        Call<ResponseBody> test2 = movieDBApi.getPopular();
        Call<ResponseBody> test3 = movieDBApi.getMovieInfo("500");
    }

    @Override
    public void onMainLoaded() {
        Log.v("TEST", "Main page is loaded.");
    }
}
