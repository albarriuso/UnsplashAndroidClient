package com.albertolopez.unsplashclient.di

import com.albertolopez.unsplashclient.detail.DetailActivity
import com.albertolopez.unsplashclient.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun contributeDetailActivity(): DetailActivity
}
