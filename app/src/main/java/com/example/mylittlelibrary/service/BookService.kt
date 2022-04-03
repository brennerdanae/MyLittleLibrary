package com.example.mylittlelibrary.service

import com.example.mylittlelibrary.RetrofitClientInstance
import com.example.mylittlelibrary.data.Book
import com.example.mylittlelibrary.service.api.LibraryApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import retrofit2.Response
import retrofit2.awaitResponse
import retrofit2.create

class BookService {
    private val service = RetrofitClientInstance.retrofitInstance?.create(LibraryApi::class.java)
    suspend fun fetchBooks(): List<Book>? {
        return withContext(Dispatchers.IO){
            val call = service?.getAllBooks()?.execute()
            call?.let { callInstance ->
                if(callInstance.isSuccessful) {
                    callInstance.body()
                } else {
                    emptyList()
                }
            }
        }
    }

    suspend fun addBook(book: Book): Boolean {
        return withContext(Dispatchers.IO){
            val call = service?.addBook(book)?.execute()
            call.let {
                it?.isSuccessful ?: false
            }
        }
    }

    suspend fun deleteBook(id: Int): Boolean {
        return withContext(Dispatchers.IO){
            val call = service?.deleteBook(id)?.execute()
            call.let {
                it?.isSuccessful ?: false
            }
        }
    }
}