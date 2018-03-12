package com.ashchuk.popularmoviesone.ui.MainPage;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.ashchuk.popularmoviesone.R;
import com.ashchuk.popularmoviesone.api.IMovieDBApi;
import com.ashchuk.popularmoviesone.data.pojo.Movie;
import com.ashchuk.popularmoviesone.data.pojo.MoviesQueryResult;
import com.ashchuk.popularmoviesone.ui.DetailPage.DetailPageActivity;

import dagger.android.support.DaggerAppCompatActivity;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

import javax.inject.Inject;

public class MainPageActivity extends DaggerAppCompatActivity implements IMainPageView {

    private Observer<MoviesQueryResult> observer;

    @Inject
    MainPagePresenter mainPagePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final GridView gridView = findViewById(R.id.gridview);
        gridView.setAdapter(new MovieItemAdapter(getApplicationContext()));

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                mainPagePresenter.subscribeOnPopular(observer);
//                Movie movie = (Movie) parent.getItemAtPosition(position);
//
//                Intent intent = new Intent(MainPageActivity.this, DetailPageActivity.class);
//                intent.putExtra("movie", movie);
//                startActivity(intent);
            }
        });

        observer = new Observer<MoviesQueryResult>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.v("TEST", "onSubscribe invoked");
                    }

                    @Override
                    public void onNext(MoviesQueryResult moviesQueryResult) {
                        Log.v("TEST", "onNext invoked");
                        ((MovieItemAdapter) gridView.getAdapter()).setMovies(moviesQueryResult.getResults().toArray(new Movie[moviesQueryResult.getResults().size()]));
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
                };


        mainPagePresenter.subscribeOnTopRated(observer);
    }

    @Override
    public void onMainLoaded() {
        Log.v("TEST", "Main page is loaded.");
    }
}
