package com.example.mylittlelibrary.service

import com.example.mylittlelibrary.RetrofitClientInstance
import com.example.mylittlelibrary.dao.IMovieDAO
import com.example.mylittlelibrary.data.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import retrofit2.awaitResponse

class MovieService {

    suspend fun fetchMovies(): List<Movie>? {
        return withContext(Dispatchers.IO) {
            val service = RetrofitClientInstance.retrofitInstance?.create(IMovieDAO::class.java)
            val movies = async { service?.getAllMovies() }
            var result = movies.await()?.awaitResponse()?.body()
            return@withContext result
        }

    }
}