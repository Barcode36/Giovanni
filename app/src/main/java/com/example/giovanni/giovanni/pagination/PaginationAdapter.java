package com.example.giovanni.giovanni.pagination;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.giovanni.giovanni.R;
import com.example.giovanni.giovanni.bean.Movie;

import java.util.ArrayList;

public class PaginationAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int PROGRESS = 0;
    private static final int ITEM = 1;

    private final Context context;
    private final ArrayList<Movie> movies;
    private boolean isLoaderVisible = false;

    public PaginationAdapter(Context context) {
        this.context = context;
        this.movies = new ArrayList<>();
    }

    public void setList(ArrayList<Movie> list) {
        this.movies.addAll(list);
    }

    public void addAll(ArrayList<Movie> mMovies) {
        for (Movie movie : mMovies) {
            movies.add(movie);
            notifyItemInserted(movies.size() - 1);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == PROGRESS) {
            return new ProgressViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.pagination_progress, parent, false));
        }
        return new ItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.pagination_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        Movie movie = movies.get(position);
        switch (getItemViewType(position)) {
            case ITEM:
                ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
                itemViewHolder.title.setText(movie.getTitle());
                itemViewHolder.description.setText(movie.getImageUrl());
                // Glide.with(context).load(movie.getImageUrl()).apply(RequestOptions.centerCropTransform()).into(itemViewHolder.thumbnail);
                Glide.with(context).load("https://static.windtrebusiness.it/mosaicow3b/static/configuration/ico_gctre.png").apply(RequestOptions.centerCropTransform()).into(itemViewHolder.thumbnail);
                break;

            case PROGRESS:
                // ProgressViewHolder progressViewHolder = (ProgressViewHolder) holder;
                // progressViewHolder.itemView.setVisibility(View.VISIBLE);
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        return (position == movies.size() - 1 && isLoaderVisible) ? PROGRESS : ITEM;
    }

    @Override
    public int getItemCount() {
        return movies == null ? 0 : movies.size();
    }

    public void addLoading() {
        isLoaderVisible = true;
        movies.add(new Movie());
        notifyItemInserted(movies.size() - 1);
    }

    public void removeLoading() {
        isLoaderVisible = false;
        int position = movies.size() - 1;
        Movie result = movies.get(position);
        if (result != null) {
            movies.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void clear() {
        movies.clear();
    }

    private static class ItemViewHolder extends RecyclerView.ViewHolder {

        private final TextView title;
        private final TextView description;
        private final ImageView thumbnail;

        private ItemViewHolder(View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.label_title);
            description = itemView.findViewById(R.id.label_description);
            thumbnail = itemView.findViewById(R.id.thumbnail);
        }
    }

    private static class ProgressViewHolder extends RecyclerView.ViewHolder {

        private ProgressViewHolder(View itemView) {
            super(itemView);
        }
    }
}