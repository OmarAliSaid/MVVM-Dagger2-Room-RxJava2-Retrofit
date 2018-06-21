package com.omar.mvvm.data.local.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.omar.mvvm.data.model.Movie;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

@Dao
public interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Movie> movies);

    @Query("SELECT * FROM movies")
    List<Movie> loadAll();
}
