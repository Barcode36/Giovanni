package com.example.giovanni.giovanni.pagination.pagination2.api;

import com.example.giovanni.giovanni.bean.Movie;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface IMovieService {

    @GET("volley_array.json")
    Call<ArrayList<Movie>> getMovies();
}