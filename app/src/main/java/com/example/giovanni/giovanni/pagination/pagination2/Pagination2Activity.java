package com.example.giovanni.giovanni.pagination.pagination2;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.giovanni.giovanni.R;
import com.example.giovanni.giovanni.bean.Movie;
import com.example.giovanni.giovanni.pagination.PaginationAdapter;
import com.example.giovanni.giovanni.pagination.PaginationListener;
import com.example.giovanni.giovanni.pagination.pagination2.api.ClientApi;
import com.example.giovanni.giovanni.pagination.pagination2.api.IMovieService;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Pagination2Activity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    private static final int PAGE_START = 1;

    private IMovieService service;

    private ProgressBar progressBar;
    private SwipeRefreshLayout swipeRefresh;
    private PaginationAdapter adapter;
    private boolean isLastPage = false;
    private boolean isLoading = false;
    private int totalPages = 5;
    private int currentPage = PAGE_START;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pagination_activity);

        service = ClientApi.getClient().create(IMovieService.class);

        progressBar = findViewById(R.id.progressBar);
        swipeRefresh = findViewById(R.id.swipeRefresh);
        swipeRefresh.setOnRefreshListener(this);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        adapter = new PaginationAdapter(this);
        recyclerView.setAdapter(adapter);

        loadFirstPage();

        recyclerView.addOnScrollListener(new PaginationListener(layoutManager) {
            @Override
            protected void loadMoreItems() {
                isLoading = true;
                currentPage += 1;
                loadNextPages();
            }

            @Override
            public boolean isLastPage() {
                return isLastPage;
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }
        });
    }

    private void loadFirstPage() {

        service.getMovies().enqueue(new Callback<ArrayList<Movie>>() {
            @Override
            public void onResponse(@NotNull Call<ArrayList<Movie>> call, @NotNull Response<ArrayList<Movie>> response) {

                progressBar.setVisibility(View.GONE);
                isLoading = false;

                if (currentPage != PAGE_START)
                    adapter.removeLoading();

                ArrayList<Movie> movies = response.body();
                if (movies != null)
                    adapter.setList(movies);

                runOnUiThread(() -> {
                    adapter.notifyDataSetChanged();
                    swipeRefresh.setRefreshing(false);

                    if (currentPage <= totalPages)
                        adapter.addLoading();
                    else
                        isLastPage = true;
                });
            }

            @Override
            public void onFailure(@NotNull Call<ArrayList<Movie>> call, @NotNull Throwable t) {
            }
        });
    }

    private void loadNextPages() {

        service.getMovies().enqueue(new Callback<ArrayList<Movie>>() {
            @Override
            public void onResponse(@NotNull Call<ArrayList<Movie>> call, @NotNull Response<ArrayList<Movie>> response) {

                isLoading = false;

                if (currentPage != PAGE_START)
                    adapter.removeLoading();

                ArrayList<Movie> movies = response.body();
                if (movies != null)
                    adapter.addAll(movies);

                runOnUiThread(() -> {
                    adapter.notifyDataSetChanged();
                    swipeRefresh.setRefreshing(false);

                    if (currentPage != totalPages)
                        adapter.addLoading();
                    else
                        isLastPage = true;
                });
            }

            @Override
            public void onFailure(@NotNull Call<ArrayList<Movie>> call, @NotNull Throwable t) {
                t.printStackTrace();
            }
        });
    }

    @Override
    public void onRefresh() {
        currentPage = PAGE_START;
        isLastPage = false;
        adapter.clear();
        adapter.notifyDataSetChanged();
        loadFirstPage();
    }
}