package com.albertolopez.unsplashclient.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.albertolopez.unsplashclient.detail.DetailViewModel
import com.albertolopez.unsplashclient.main.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Dagger module.
 * Declaration of the view models to be injected.
 */
@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: UnsplashViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(mainViewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailViewModel::class)
    abstract fun bindDetailViewModel(detailViewModel: DetailViewModel): ViewModel

}
