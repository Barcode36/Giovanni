package com.example.giovanni.giovanni.pagination.pagination1;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.giovanni.giovanni.R;
import com.example.giovanni.giovanni.bean.Movie;
import com.example.giovanni.giovanni.pagination.PaginationListener;
import com.example.giovanni.giovanni.pagination.PaginationAdapter;

import java.util.ArrayList;

public class Pagination1Activity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    private static final int PAGE_START = 1;

    private ProgressBar progressBar;
    private SwipeRefreshLayout swipeRefresh;
    private PaginationAdapter adapter;
    private boolean isLastPage = false;
    private boolean isLoading = false;
    private int totalPages = 5; // TODO: da sostituire con la size desiderata della lista.
    private int currentPage = PAGE_START;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pagination_activity);

        progressBar = findViewById(R.id.progressBar);
        swipeRefresh = findViewById(R.id.swipeRefresh);
        swipeRefresh.setOnRefreshListener(this);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        adapter = new PaginationAdapter(this);
        recyclerView.setAdapter(adapter);

        loadFirstPage();

        recyclerView.addOnScrollListener(new PaginationListener(layoutManager) {
            @Override
            protected void loadMoreItems() {
                isLoading = true;
                currentPage++;
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

        new Handler().postDelayed(() -> {

            progressBar.setVisibility(View.GONE);
            isLoading = false;

            if (currentPage != PAGE_START)
                adapter.removeLoading();

            ArrayList<Movie> movies = init(currentPage);
            if (movies != null)
                adapter.setList(movies);

            runOnUiThread(() -> {
                adapter.notifyDataSetChanged();
                swipeRefresh.setRefreshing(false);

                if (currentPage < totalPages)
                    adapter.addLoading();
                else
                    isLastPage = true;
            });
        }, 1500);
    }

    private void loadNextPages() {

        new Handler().postDelayed(() -> {

            isLoading = false;

            if (currentPage != PAGE_START)
                adapter.removeLoading();

            ArrayList<Movie> movies = init(currentPage);
            if (movies != null)
                adapter.addAll(movies);

            runOnUiThread(() -> {
                adapter.notifyDataSetChanged();
                swipeRefresh.setRefreshing(false);

                if (currentPage < totalPages)
                    adapter.addLoading();
                else
                    isLastPage = true;
            });
        }, 1500);
    }

    @Override
    public void onRefresh() {
        currentPage = PAGE_START;
        isLastPage = false;
        adapter.clear();
        adapter.notifyDataSetChanged();
        loadFirstPage();
    }

    public ArrayList<Movie> init(int currentPage) {

        ArrayList<Movie> list = new ArrayList<>();
        list.add(new Movie("Movie title 1", "Current page: " + currentPage));
        list.add(new Movie("Movie title 2", "Current page: " + currentPage));
        list.add(new Movie("Movie title 3", "Current page: " + currentPage));
        list.add(new Movie("Movie title 4", "Current page: " + currentPage));
        list.add(new Movie("Movie title 5", "Current page: " + currentPage));
        list.add(new Movie("Movie title 6", "Current page: " + currentPage));
        list.add(new Movie("Movie title 7", "Current page: " + currentPage));
        list.add(new Movie("Movie title 8", "Current page: " + currentPage));
        list.add(new Movie("Movie title 9", "Current page: " + currentPage));
        list.add(new Movie("Movie title 10", "Current page: " + currentPage));

        return list;
    }
}