package com.example.mylittlelibrary.service

import com.example.mylittlelibrary.RetrofitClientInstance
import com.example.mylittlelibrary.data.Book
import com.example.mylittlelibrary.dao.LibraryApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import retrofit2.awaitResponse

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