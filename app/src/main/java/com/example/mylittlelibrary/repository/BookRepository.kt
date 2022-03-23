package com.example.mylittlelibrary.repository

import androidx.annotation.WorkerThread
import com.example.mylittlelibrary.data.Book
import com.example.mylittlelibrary.room.dao.BookDao
import com.example.mylittlelibrary.service.BookService
import kotlinx.coroutines.flow.Flow

class BookRepository(private val bookDao: BookDao, private val service: BookService) {

    val allBooks: Flow<List<Book>> = bookDao.getAllBooks()

    suspend fun addBook(book: Book): Boolean = service.addBook(book)

    suspend fun fetchBooks(){
        val books = service.fetchBooks()
        books?.let { bookDao.insertAll(it) }
    }
}