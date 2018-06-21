package com.omar.mvvm.ui.MoviesListActivity;

import android.app.Application;

import com.omar.mvvm.data.DataManager;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;

@Module
public class MovieListModule {

    @Provides
    MoviesViewModel provideMoviesViewModel(Application application , DataManager dataManager) {
        return new MoviesViewModel(application , dataManager);
    }


    @Provides
    MovieListAdapter provideMovieListAdapter(){
        return new MovieListAdapter(new ArrayList<>());
    }
}
