package com.ashchuk.popularmoviesone.ui.MainPage;

import android.os.Bundle;
import android.util.Log;
import android.widget.GridView;

import com.ashchuk.popularmoviesone.R;
import com.ashchuk.popularmoviesone.api.IMovieDBApi;
import com.ashchuk.popularmoviesone.data.pojo.Movie;
import com.ashchuk.popularmoviesone.data.pojo.MoviesQueryResult;

import org.reactivestreams.Subscription;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;
import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
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

        final GridView gridView = findViewById(R.id.gridview);

        mainPagePresenter.loadMain();
        IMovieDBApi movieDBApi = retrofit.create(IMovieDBApi.class);

        movieDBApi.getTopRated()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MoviesQueryResult>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.v("TEST", "onSubscribe invoked");
                    }

                    @Override
                    public void onNext(MoviesQueryResult moviesQueryResult) {
                        Log.v("TEST", "onNext invoked");
                        gridView.setAdapter(new MovieItemAdapter(getApplicationContext(), moviesQueryResult.getResults().toArray(new Movie[moviesQueryResult.getResults().size()])));
                        gridView.refreshDrawableState();
                    }

                    @Override
                    public void onError(Throwable t) {
                        Log.v("TEST", "onError invoked" + t.getStackTrace());
                    }

                    @Override
                    public void onComplete() {
                        Log.v("TEST", "onComplete invoked");
                    }
                });
    }

    @Override
    public void onMainLoaded() {
        Log.v("TEST", "Main page is loaded.");
    }
}
