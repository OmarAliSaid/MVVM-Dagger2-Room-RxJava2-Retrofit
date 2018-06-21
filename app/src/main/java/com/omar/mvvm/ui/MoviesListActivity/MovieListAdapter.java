package com.omar.mvvm.ui.MoviesListActivity;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.omar.mvvm.R;
import com.omar.mvvm.data.model.Movie;
import com.omar.mvvm.databinding.CardMovieBinding;

import java.util.ArrayList;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.ViewHolder> {

    LayoutInflater layoutInflater;
    public ArrayList<Movie> movies;

    public MovieListAdapter(ArrayList<Movie> movies) {
        this.movies = movies;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        CardMovieBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.card_movie, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
        viewHolder.binding.setMovie(movies.get(position));
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private CardMovieBinding binding;

        public ViewHolder(CardMovieBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public void addItems(ArrayList<Movie>movies){
        this.movies.clear();
        this.movies.addAll(movies);
        notifyDataSetChanged();
    }
}
