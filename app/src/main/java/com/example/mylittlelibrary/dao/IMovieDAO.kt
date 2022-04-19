package com.example.mylittlelibrary.dao

import com.example.mylittlelibrary.data.Movie
import retrofit2.Call
import retrofit2.http.GET

interface IMovieDAO {

    @GET("/hjorturlarsen/IMDB-top-100/blob/master/data/movies.json")
    fun getAllMovies() : Call<ArrayList<Movie>>
}