package com.omar.mvvm.data.local.db;

import com.omar.mvvm.data.model.Movie;

import java.util.ArrayList;
import java.util.List;
import io.reactivex.Observable;

public interface DbHelper {

    Observable<Boolean> saveMovies(List<Movie> movies);

    Observable<List<Movie>> loadAllMovies();

}
