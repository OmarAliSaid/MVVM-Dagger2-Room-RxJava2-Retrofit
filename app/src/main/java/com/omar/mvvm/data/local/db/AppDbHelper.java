package com.omar.mvvm.data.local.db;

import android.util.Log;

import com.omar.mvvm.data.model.Movie;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

@Singleton
public class AppDbHelper implements DbHelper{

    private static final String TAG = AppDbHelper.class.getSimpleName();

    private final AppDatabase mAppDatabase;

    @Inject
    public AppDbHelper(AppDatabase appDatabase) {
        this.mAppDatabase = appDatabase;
    }


    @Override
    public Observable<Boolean> saveMovies(List<Movie> movies) {
        return Observable.fromCallable(() -> {
            try{
                mAppDatabase.movieDao().insertAll(movies);
                return true;
            }catch (Exception e){
                Log.d(TAG , e.getMessage());
            }

            return false;
        });
    }



    @Override
    public Observable<List<Movie>> loadAllMovies() {
        return Observable.fromCallable(() -> mAppDatabase.movieDao().loadAll());
    }
}
