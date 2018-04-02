package com.ashchuk.popularmoviesone.ui.DetailPage;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;

import com.ashchuk.popularmoviesone.R;
import com.ashchuk.popularmoviesone.data.persistance.MoviesDbContract;
import com.ashchuk.popularmoviesone.data.persistance.MoviesDbHelper;
import com.ashchuk.popularmoviesone.data.pojo.MovieDetailed;
import com.ashchuk.popularmoviesone.data.pojo.ReviewResult;
import com.ashchuk.popularmoviesone.data.pojo.TrailerResult;
import com.ashchuk.popularmoviesone.databinding.ActivityDetailBinding;
import com.ashchuk.popularmoviesone.ui.DetailPage.adapters.ReviewItemAdapter;
import com.ashchuk.popularmoviesone.ui.DetailPage.adapters.TrailerItemAdapter;
import com.ashchuk.popularmoviesone.utils.Constants;
import com.squareup.picasso.Picasso;


import java.util.Objects;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class DetailPageActivity extends DaggerAppCompatActivity implements IDetailPageView {

    private ProgressDialog progressDialog;
    private AlertDialog.Builder alertDialog;

    private Observer<MovieDetailed> observer;
    private MovieDetailed movie;
    private String movieId;

    @Inject
    DetailPagePresenter detailPagePresenter;

    @Inject
    MoviesDbHelper moviesDbHelper;

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
                movie = movieDetailed;
                Picasso.get().load(Constants.BACKDROP_END_POINT + movieDetailed.getBackdropPath()).into(binding.moviePoster);
                Picasso.get().load(Constants.POSTER_END_POINT + movieDetailed.getPosterPath()).into(binding.includeDetail.moviePoster);

                LinearLayoutManager reviewsLayoutManager = new LinearLayoutManager(DetailPageActivity.this);
                ReviewItemAdapter reviewItemAdapter =
                        new ReviewItemAdapter(movieDetailed.getReviews().getResults()
                                .toArray(new ReviewResult[movieDetailed.getReviews().getResults().size()]));
                binding.includeDetail.reviews.setLayoutManager(reviewsLayoutManager);
                binding.includeDetail.reviews.setAdapter(reviewItemAdapter);

                LinearLayoutManager trailersLayoutManager = new LinearLayoutManager(
                        DetailPageActivity.this,
                        LinearLayoutManager.HORIZONTAL, false);
                TrailerItemAdapter trailerItemAdapter =
                        new TrailerItemAdapter(movieDetailed.getVideos().getResults()
                                .toArray(new TrailerResult[movieDetailed.getVideos().getResults().size()]), (key) -> {
                            Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + key));
                            if (appIntent.resolveActivity(getPackageManager()) != null) {
                                startActivity(appIntent);
                            } else {
                                Intent intent = new Intent(Intent.ACTION_VIEW,
                                        Uri.parse("http://www.youtube.com/watch?v=" + key));
                                startActivity(intent);
                            }
                        });
                binding.includeDetail.trailers.setLayoutManager(trailersLayoutManager);
                binding.includeDetail.trailers.setAdapter(trailerItemAdapter);
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
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            if (moviesDbHelper.getMovieById(this, movieId) != null){
                moviesDbHelper.removeMovieById(this, movieId);
                Snackbar.make(view, R.string.remove_from_favorites_message, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
            else{
                moviesDbHelper.addMovieToDB(this, movie);
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
