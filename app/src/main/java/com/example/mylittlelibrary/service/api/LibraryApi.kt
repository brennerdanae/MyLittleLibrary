package com.example.mylittlelibrary.service.api

import androidx.room.Delete
import com.example.mylittlelibrary.data.Book
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface LibraryApi {

    @GET("/books")
    fun getAllBooks() : Call<List<Book>>

    @POST("/books")
    fun addBook(@Body book : Book) : Call<Void>

    @POST("/books")
    abstract fun deleteBook(id: Int): Call<Void>
}