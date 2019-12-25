package com.albertolopez.unsplashclient.di

import com.albertolopez.unsplashclient.UnsplashApplication
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton


/**
 * Dagger component.
 * List of the Dagger modules used in this application.
 */
@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ApplicationModule::class,
        ActivityModule::class]
)
interface ApplicationComponent : AndroidInjector<UnsplashApplication> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<UnsplashApplication>()

}
