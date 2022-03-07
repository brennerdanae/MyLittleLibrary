package com.example.mylittlelibrary.service

import com.example.mylittlelibrary.RetrofitClientInstance
import com.example.mylittlelibrary.data.Book
import com.example.mylittlelibrary.service.api.LibraryApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import retrofit2.awaitResponse
import retrofit2.create

class BookService {

    suspend fun fetchBooks(): List<Book>? {
        return withContext(Dispatchers.IO){
            val service = RetrofitClientInstance.retrofitInstance?.create(LibraryApi::class.java)
            val books = async {
                service?.getAllBooks()
            }
            val result = books.await()?.awaitResponse()?.body()
            return@withContext result
        }
    }
}