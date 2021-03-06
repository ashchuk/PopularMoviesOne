package com.ashchuk.popularmoviesone.ui.MainPage;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.GridView;

import com.ashchuk.popularmoviesone.R;
import com.ashchuk.popularmoviesone.data.pojo.Movie;
import com.ashchuk.popularmoviesone.data.pojo.MoviesQueryResult;
import com.ashchuk.popularmoviesone.ui.DetailPage.DetailPageActivity;
import com.ashchuk.popularmoviesone.ui.MainPage.adapters.MovieItemAdapter;

import java.util.List;

import dagger.android.support.DaggerAppCompatActivity;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import javax.inject.Inject;

public class MainPageActivity extends DaggerAppCompatActivity implements IMainPageView {

    private Observer<MoviesQueryResult> observer;
    private ProgressDialog progressDialog;
    private AlertDialog.Builder alertDialog;
    GridView gridView;

    @Inject
    MainPagePresenter mainPagePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridView = findViewById(R.id.gridview);
        gridView.setAdapter(new MovieItemAdapter(getApplicationContext()));

        gridView.setOnItemClickListener((parent, v, position, id) -> {
            Movie movie = (Movie) parent.getItemAtPosition(position);
            Intent intent = new Intent(MainPageActivity.this, DetailPageActivity.class);
            intent.putExtra("movie_id", Integer.toString(movie.getId()));
            startActivity(intent);
        });

        observer = new Observer<MoviesQueryResult>() {
            @Override
            public void onSubscribe(Disposable d) {
                progressDialog.show();
            }

            @Override
            public void onNext(MoviesQueryResult moviesQueryResult) {
                ((MovieItemAdapter) gridView.getAdapter()).setMovies(moviesQueryResult.getResults().toArray(new Movie[moviesQueryResult.getResults().size()]));
                gridView.refreshDrawableState();
            }

            @Override
            public void onError(Throwable t) {
                progressDialog.dismiss();
                alertDialog.show();
            }

            @Override
            public void onComplete() {
                progressDialog.dismiss();
            }
        };
        mainPagePresenter.subscribeOnPopular(observer);
    }

    @Override
    protected void onResume() {
        mainPagePresenter.subscribeOnPopular(observer);
        super.onResume();
    }

    private void initDialogs() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getString(R.string.loading_message));
        progressDialog.setIndeterminate(true);
        // From Android Nanodegree General Project Guidelines
        // All dialogs are dismissible using the Back button.
        //progressDialog.setCanceledOnTouchOutside(false);

        alertDialog = new AlertDialog.Builder(this);
        alertDialog.setMessage("Error occurred. Try again");
        alertDialog.setPositiveButton("OK", null);
        alertDialog.setCancelable(true);
        alertDialog.create();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_get_popular:
                mainPagePresenter.subscribeOnPopular(observer);
                return true;
            case R.id.action_get_top_ratied:
                mainPagePresenter.subscribeOnTopRated(observer);
                return true;
            case R.id.action_get_favourite:
                List<Movie> list = mainPagePresenter.subscribeOnFavorite(this);
                ((MovieItemAdapter) gridView.getAdapter()).setMovies(list.toArray(new Movie[list.size()]));
                gridView.refreshDrawableState();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onMainLoaded() {
        initDialogs();
    }
}
