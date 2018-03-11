package com.ashchuk.popularmoviesone.ui.DetailPage;

import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.ashchuk.popularmoviesone.R;
import com.ashchuk.popularmoviesone.data.pojo.Movie;
import com.ashchuk.popularmoviesone.databinding.ActivityDetailBinding;
import com.ashchuk.popularmoviesone.utils.Constants;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import dagger.android.support.DaggerAppCompatActivity;

public class DetailPageActivity extends DaggerAppCompatActivity implements IDetailPageView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final ActivityDetailBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_detail);
        Movie movie = (Movie) getIntent().getSerializableExtra("movie");
        binding.setMovie(movie);

        Picasso.get().load(Constants.POSTER_END_POINT + movie.getPosterPath()).into(new Target() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                binding.moviePoster.setImageBitmap(bitmap);
                binding.includeDetail.moviePoster.setImageBitmap(bitmap);
            }

            @Override
            public void onBitmapFailed(Exception e, Drawable errorDrawable) {

            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        });

        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show(); 
            }
        });
    }

    @Override
    public void onDetailLoaded() {

    }
}
