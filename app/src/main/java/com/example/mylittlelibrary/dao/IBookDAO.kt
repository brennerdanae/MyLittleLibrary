package com.example.mylittlelibrary.dao

import com.example.mylittlelibrary.data.Book
import retrofit2.Call
import retrofit2.http.GET

interface IBookDAO {

    @GET("/benoitvallon/100-best-books/blob/master/books.json")
    fun getAllBooks() : Call<ArrayList<Book>>

}