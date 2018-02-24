package com.ashchuk.popularmoviesone.ui.DetailPage;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;

import dagger.android.support.DaggerAppCompatActivity;

/**
 * Created by Artyom Koshko (@ashchuk) on 24.02.2018.
 */

public class DetailPageActivity extends DaggerAppCompatActivity implements IDetailPageView {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    @Override
    public void onDetailLoaded() {

    }
}
