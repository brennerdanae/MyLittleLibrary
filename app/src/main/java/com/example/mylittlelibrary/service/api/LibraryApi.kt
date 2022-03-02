package com.example.mylittlelibrary.service.api

import com.example.mylittlelibrary.data.Book
import retrofit2.Call
import retrofit2.http.GET

interface LibraryApi {

    @GET("/books")
    fun getAllBooks() : Call<List<Book>>
}