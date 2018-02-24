package com.ashchuk.popularmoviesone.di;

import com.ashchuk.popularmoviesone.ui.DetailPage.DetailPageActivity;
import com.ashchuk.popularmoviesone.ui.DetailPage.DetailPageModule;
import com.ashchuk.popularmoviesone.ui.MainPage.MainPageActivity;
import com.ashchuk.popularmoviesone.ui.MainPage.MainPageModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by Artyom Koshko (@ashchuk) on 24.02.2018.
 */

@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = MainPageModule.class)
    abstract MainPageActivity bindMainPageActivity();

    @ContributesAndroidInjector(modules = {DetailPageModule.class})
    abstract DetailPageActivity bindDetailPageActivity();

}
