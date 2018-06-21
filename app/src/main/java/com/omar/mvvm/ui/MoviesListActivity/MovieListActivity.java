package com.omar.mvvm.ui.MoviesListActivity;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.omar.mvvm.R;
import com.omar.mvvm.ui.Base.BaseActivity;
import java.util.ArrayList;
import javax.inject.Inject;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieListActivity extends BaseActivity<MoviesViewModel>{

    @BindView(R.id.activity_main_rv_movies) RecyclerView movies_recycler_view;

    @Inject
    MoviesViewModel moviesViewModel;

    @Inject
    MovieListAdapter movieListAdapter;

    @Override
    public MoviesViewModel getViewModel() {
        return moviesViewModel;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        // initialize movies recycler view
        setupMoviesRecyclerView();

        // start fetching movies based on sort type
        moviesViewModel.fetchMovieList("popularity.desc");

        // subscribe to movies live data changes
        moviesViewModel.getMoviesLiveData().observe(this, movies -> {
            if(movies!=null)
               movieListAdapter.addItems(new ArrayList<>(movies));
        });
    }


    private void setupMoviesRecyclerView() {
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this,2);
        movies_recycler_view.setLayoutManager(mLayoutManager);
        movies_recycler_view.setAdapter(movieListAdapter);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        movieListAdapter = null;
    }
}
