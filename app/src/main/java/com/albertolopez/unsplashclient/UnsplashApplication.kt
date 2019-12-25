package com.albertolopez.unsplashclient

import com.albertolopez.unsplashclient.di.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication


/**
 * Custom Application class that extends DaggerApplication.
 */
class UnsplashApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out UnsplashApplication> {
        return DaggerApplicationComponent.builder().create(this)
    }

}