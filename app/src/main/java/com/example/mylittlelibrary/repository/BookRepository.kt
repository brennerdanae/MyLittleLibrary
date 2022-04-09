package com.example.mylittlelibrary.repository

import android.util.Log
import com.example.mylittlelibrary.data.Book
import com.example.mylittlelibrary.data.Dvd
import com.example.mylittlelibrary.data.Movie
import com.example.mylittlelibrary.room.dao.BookDao
import com.example.mylittlelibrary.room.dao.DvdDao
import com.example.mylittlelibrary.room.dao.MovieDao
import com.example.mylittlelibrary.service.BookService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BookRepository @Inject constructor(
    private val bookDao: BookDao,
    private val movieDao: MovieDao,
    private val dvdDao: DvdDao,
    private val service: BookService
) {

    val allBooks: Flow<List<Book>> = bookDao.getAllBooks()
    val allMovie: Flow<List<Movie>> = movieDao.getAllMovies()
    val allDvd : Flow<List<Dvd>> = dvdDao.getAllDvd()

    suspend fun addBook(book: Book): Boolean = service.addBook(book)
    suspend fun addMovie(movie: Movie): Boolean = service.addMovie(movie)
    suspend fun addDvd(dvd: Dvd): Boolean = service.addDvd(dvd)

    suspend fun fetchBooks() {
        Result.runCatching {
            service.fetchBooks()
        }.onSuccess { books ->
            books?.let {
                bookDao.insertAll(it)
            }
        }.onFailure { throwable ->
            Log.d("Service Error", throwable.message.toString())
        }
    }

    suspend fun fetchMovies(){
        Result.runCatching {
            service.fetchMovies()
        }.onSuccess { movies ->
            movies?.let {
                movieDao.insertAll(it)
            }
        }.onFailure { throwable ->
            Log.d("Service Error", throwable.message.toString())
        }
    }

    suspend fun fetchDvd(){
        Result.runCatching {
            service.fetchDvd()
        }.onSuccess { dvds ->
            dvds?.let {
                dvdDao.insertAll(dvds)
            }
        }.onFailure { throwable ->
            Log.d("Service Error", throwable.message.toString())
        }
    }
}