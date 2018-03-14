package com.ashchuk.popularmoviesone.ui.DetailPage;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;

import com.ashchuk.popularmoviesone.R;
import com.ashchuk.popularmoviesone.data.pojo.MovieDetailed;
import com.ashchuk.popularmoviesone.databinding.ActivityDetailBinding;
import com.ashchuk.popularmoviesone.utils.Constants;
import com.squareup.picasso.Picasso;


import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class DetailPageActivity extends DaggerAppCompatActivity implements IDetailPageView {

    private ProgressDialog progressDialog;
    private AlertDialog.Builder alertDialog;

    private Observer<MovieDetailed> observer;
    private String movieId;

    @Inject
    DetailPagePresenter detailPagePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final ActivityDetailBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_detail);
        movieId = getIntent().getStringExtra("movie_id");

        observer = new Observer<MovieDetailed>() {
            @Override
            public void onSubscribe(Disposable d) {
                progressDialog.show();
            }

            @Override
            public void onNext(MovieDetailed movieDetailed) {
                binding.setMovie(movieDetailed);

                Picasso.get().load(Constants.POSTER_END_POINT + movieDetailed.getPosterPath()).into(binding.moviePoster);
                Picasso.get().load(Constants.POSTER_END_POINT + movieDetailed.getPosterPath()).into(binding.includeDetail.moviePoster);
            }

            @Override
            public void onError(Throwable e) {
                progressDialog.dismiss();
                alertDialog.show();
            }

            @Override
            public void onComplete() {
                progressDialog.dismiss();
            }
        };

        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, R.string.add_to_favorites_message, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show(); 
            }
        });

        detailPagePresenter.subscribeOnMovie(observer, movieId);
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
    public void onDetailLoaded() {
        initDialogs();
    }
}
