package com.ashchuk.popularmoviesone.ui.MainPage;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.ashchuk.popularmoviesone.R;
import com.ashchuk.popularmoviesone.data.pojo.Movie;
import com.ashchuk.popularmoviesone.data.pojo.MoviesQueryResult;
import com.ashchuk.popularmoviesone.ui.DetailPage.DetailPageActivity;

import dagger.android.support.DaggerAppCompatActivity;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import javax.inject.Inject;

public class MainPageActivity extends DaggerAppCompatActivity implements IMainPageView {

    private Observer<MoviesQueryResult> observer;

    private ProgressDialog progressDialog;
    private AlertDialog.Builder alertDialog;

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
                Movie movie = (Movie) parent.getItemAtPosition(position);
                Intent intent = new Intent(MainPageActivity.this, DetailPageActivity.class);
                intent.putExtra("movie_id", Integer.toString(movie.getId()));
                startActivity(intent);
            }
        });

        observer = new Observer<MoviesQueryResult>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.v("TEST", "onSubscribe invoked");
                        progressDialog.show();
                    }

                    @Override
                    public void onNext(MoviesQueryResult moviesQueryResult) {
                        Log.v("TEST", "onNext invoked");
                        ((MovieItemAdapter) gridView.getAdapter()).setMovies(moviesQueryResult.getResults().toArray(new Movie[moviesQueryResult.getResults().size()]));
                        gridView.refreshDrawableState();
                    }

                    @Override
                    public void onError(Throwable t) {
                        progressDialog.dismiss();
                        alertDialog.show();
                        Log.v("TEST", "onError invoked" + t.getStackTrace());
                    }

                    @Override
                    public void onComplete() {
                        progressDialog.dismiss();
                        Log.v("TEST", "onComplete invoked");
                    }
                };
        mainPagePresenter.subscribeOnTopRated(observer);
    }

    private void initDialogs() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getString(R.string.loading_message));
        progressDialog.setIndeterminate(true);
        progressDialog.setCanceledOnTouchOutside(false);

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
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onMainLoaded() {
        initDialogs();
    }
}
