package com.omar.mvvm.data.remote;

import com.omar.mvvm.data.remote.api.MultiBaseResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("3/discover/movie")
    Observable<MultiBaseResponse> fetchMoviesSortedBy(
              @Query("api_key")String api_key
            , @Query("sorted_by")String sorted_by);


}
