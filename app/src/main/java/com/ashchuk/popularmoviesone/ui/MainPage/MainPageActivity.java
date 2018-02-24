package com.ashchuk.popularmoviesone.ui.MainPage;

import android.os.Bundle;
import android.util.Log;

import com.ashchuk.popularmoviesone.R;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class MainPageActivity extends DaggerAppCompatActivity implements IMainPageView {

    @Inject
    MainPagePresenter mainPagePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainPagePresenter.loadMain();
    }

    @Override
    public void onMainLoaded() {
        Log.v("TEST", "Main page is loaded.");
    }
}
