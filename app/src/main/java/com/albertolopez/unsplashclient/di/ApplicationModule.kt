package com.albertolopez.unsplashclient.di

import android.app.Application
import com.albertolopez.unsplashclient.UnsplashApplication
import com.albertolopez.unsplashclient.remotedatasource.RetrofitClient
import dagger.Binds
import dagger.Module

/**
 * This is a Dagger module.
 * Useful to bind the Application class as a Context in the ApplicationComponent
 */

@Module(includes = [
    ViewModelModule::class,
    RetrofitClient::class])

abstract class ApplicationModule {

    @Binds
    abstract fun bindContext(application: UnsplashApplication): Application

}