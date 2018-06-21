package com.omar.mvvm.di.builder;

import com.omar.mvvm.ui.MoviesListActivity.MovieListActivity;
import com.omar.mvvm.ui.MoviesListActivity.MovieListModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = MovieListModule.class)
    abstract MovieListActivity bindMovieListActivity();
}
