package com.omar.mvvm.ui.MoviesListActivity;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.omar.mvvm.data.DataManager;
import com.omar.mvvm.data.model.Movie;
import com.omar.mvvm.ui.Base.BaseViewModel;
import com.omar.mvvm.utils.AppConstants;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MoviesViewModel extends BaseViewModel {

    private static final String TAG = MoviesViewModel.class.getSimpleName();

    MutableLiveData<List<Movie>> moviesLiveData;

    @Inject
    public MoviesViewModel(Application context, DataManager dataManager) {
        super(context, dataManager);
    }

    /**
     * fetch movies from server OR local database based on sort type
     *
     * if internet available then load movies from server and update database
     * else fetch movies from database
     *
     * @param sort_by
     */
    void fetchMovieList(String sort_by){
        if(isNetworkConnected()){
            fetchMoviesFromServer(sort_by);
        }else{
            fetchMoviesFromDB(sort_by);
        }
    }


    /**
     * fetch movies from local database
     *
     * @param sort_by
     */
    private void fetchMoviesFromDB(String sort_by){
        getCompositeDisposable().add(getDataManager().loadAllMovies()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(moviesList->{
                    // notify subscribers about the loaded data
                    moviesLiveData.setValue(new ArrayList<>(moviesList));
                } , throwable -> {
                    // notify subscribers about the error msg
                    getErrorMsg().setValue(throwable.getMessage());
                }));
    }



    /**
     * fetch movies from remote server
     *
     * @param sort_by
     */
    private void fetchMoviesFromServer(String sort_by){
        // show loading
        showLoading.call();

        getCompositeDisposable().add(getDataManager().fetchMoviesSortedBy(AppConstants.API_KEY , sort_by)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response->{
                    // hide loading dialog
                    hideLoading.call();

                    // update movies in db
                    insertMovies(response.getResult());

                    // notify subscribers about the new loaded data
                    moviesLiveData.setValue(response.getResult());

                } , throwable -> {
                    // hide loading dialog
                    hideLoading.call();

                    // notify subscribers about the error msg
                    getErrorMsg().setValue(throwable.getMessage());
                }));
    }


    /**
     * insert movies in database
     *
     * @param movies
     */
    private void insertMovies(List<Movie>movies){
        getCompositeDisposable().add(getDataManager().saveMovies(movies)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response->{
                    Log.d(TAG , movies.size()+" movies inserted in db");
                } , throwable -> {
                    Log.d(TAG , "error inserting movies : " +throwable.getMessage());
                }));
    }



    public MutableLiveData<List<Movie>> getMoviesLiveData() {
        if(moviesLiveData ==null)
            moviesLiveData = new MutableLiveData<>();
        return moviesLiveData;
    }
}
