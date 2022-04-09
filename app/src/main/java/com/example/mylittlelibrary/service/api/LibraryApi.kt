package com.example.mylittlelibrary.service.api

import com.example.mylittlelibrary.data.Book
import com.example.mylittlelibrary.data.Dvd
import com.example.mylittlelibrary.data.Movie
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface LibraryApi {

    @GET("/books")
    fun getAllBooks() : Call<List<Book>>

    @POST("/books")
    fun addBook(@Body book : Book) : Call<Void>

    @GET("/movies")
    fun getAllMovie() : Call<List<Movie>>

    @POST("/movies")
    fun addMovie(@Body movie: Movie) : Call<Void>

    @GET("/dvds")
    fun getAllDvds() : Call<List<Dvd>>

    @POST("/dvds")
    fun addDvd(@Body dvd: Dvd) : Call<Void>
}